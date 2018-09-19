package hu.bme.mit.ga.metrics.impl.typed;

import hu.bme.mit.ga.adapters.GraphAdapter;
import hu.bme.mit.ga.adapters.GraphIndexer;
import org.ojalgo.function.PrimitiveFunction;
import org.ojalgo.function.aggregator.Aggregator;
import org.ojalgo.matrix.store.ElementsSupplier;
import org.ojalgo.matrix.store.MatrixStore;
import org.ojalgo.matrix.store.PrimitiveDenseStore;
import org.ojalgo.matrix.store.SparseStore;
import org.ujmp.core.Matrix;
import org.ujmp.core.SparseMatrix;
import org.ujmp.core.calculation.Calculation;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.ojalgo.function.PrimitiveFunction.ADD;
import static org.ojalgo.function.PrimitiveFunction.MULTIPLY;
import static org.ojalgo.function.PrimitiveFunction.SUBTRACT;

public class TypedClusteringCoefficientDef1 extends TypedClusteringCoefficient {

    Implementation implementation = Implementation.OJALGO;

    public TypedClusteringCoefficientDef1() {
        super("TypedClusteringCoefficientDef1");
    }

    ;

    protected <N, T> void evaluateAllOjalgo(final GraphAdapter<N, T> adapter) {
        GraphIndexer indexer = adapter.getIndexer();
        MatrixStore productSum = PrimitiveDenseStore.FACTORY.makeZero(indexer.getSize(), 1);
        PrimitiveDenseStore degrees = PrimitiveDenseStore.FACTORY.makeZero(indexer.getSize(), 1);
        PrimitiveDenseStore ones = PrimitiveDenseStore.FACTORY.makeZero(indexer.getSize(), 1);
        ones.operateOnAll(ADD, 1).supplyTo(ones);
        for (T type1 : adapter.getIndexer().getTypes()) {
            SparseStore<Double> A = (SparseStore<Double>) indexer.getAdjacencyMatrix2().get(type1);
            for (T type2 : adapter.getIndexer().getTypes()) {
                if (type1 != type2) {
                    System.out.println(new Timestamp(new Date().getTime()) + String.format(" Calculating clustering for types %s × %s", type1, type2));
                    SparseStore<Double> B = (SparseStore<Double>) indexer.getAdjacencyMatrix2().get(type2);
                    SparseStore<Double> C = SparseStore.PRIMITIVE.make(indexer.getSize(), indexer.getSize());

                    System.out.println(new Timestamp(new Date().getTime()) + " -> AB = A * B");
                    SparseStore<Double> AB = SparseStore.PRIMITIVE.make(indexer.getSize(), indexer.getSize());
                    A.multiply(B).get().supplyTo(AB);

                    System.out.println(new Timestamp(new Date().getTime()) + " -> C = AB .* A");
                    AB.operateOnMatching(MULTIPLY, A).supplyTo(C);
                    productSum = C.reduceRows(Aggregator.SUM).get().add(productSum);
                }
            }
            MatrixStore<Double> degreeVector = A.multiply(ones);
            final ElementsSupplier<Double> tmpIntermediateA = degreeVector.operateOnMatching(SUBTRACT, ones);
            final ElementsSupplier<Double> tmpIntermediateB = tmpIntermediateA.operateOnMatching(PrimitiveFunction.MULTIPLY, degreeVector);
            final ElementsSupplier<Double> tmpIntermediateC = tmpIntermediateB.operateOnMatching(ADD, degrees);
            PrimitiveDenseStore degrees_tmp = PrimitiveDenseStore.FACTORY.makeZero(indexer.getSize(), 1);
            tmpIntermediateC.supplyTo(degrees_tmp);
            degrees = degrees_tmp.copy();
        }
        for (int i = 0; i < indexer.getSize(); i++) {
            double numerator = productSum.doubleValue(i, 0);
            double denominator = degrees.doubleValue(i, 0) * (indexer.getTypes().size() - 1);
            if (denominator == 0) {
                data.add(0.0);
            } else {
                data.add(numerator / denominator);
            }
        }
    }

    @Override
    protected <N, T> void evaluateAll(final GraphAdapter<N, T> adapter) {
        if (implementation == Implementation.UJMP) {
            evaluateAllUjmp(adapter);
        } else if (implementation == Implementation.OJALGO) {
            evaluateAllOjalgo(adapter);
        } else if (implementation == Implementation.UJMP_EW) {
            evaluateAllUjmpElementwise(adapter);
        }
    }

    protected <N, T> void evaluateAllUjmp(final GraphAdapter<N, T> adapter) {
        GraphIndexer indexer = adapter.getIndexer();
        Matrix productSum = SparseMatrix.Factory.zeros(indexer.getSize(), indexer.getSize());
        Matrix degrees = SparseMatrix.Factory.zeros(indexer.getSize(), 1);
        for (T type1 : adapter.getIndexer().getTypes()) {
            Matrix A = (Matrix) indexer.getAdjacencyMatrix().get(type1);
            for (T type2 : adapter.getIndexer().getTypes()) {
                if (type1 != type2) {
                    Matrix B = (Matrix) indexer.getAdjacencyMatrix().get(type2);
                    productSum = productSum.plus(A.mtimes(B).mtimes(A));
                }
            }
            Matrix degreeVector = A.sum(Calculation.Ret.NEW, 1, false);
            degrees = degrees.plus(degreeVector.times(degreeVector.minus(1)));

        }
        for (int i = 0; i < indexer.getSize(); i++) {
            double numerator = productSum.getAsDouble(i, i);
            double denominator = degrees.getAsDouble(i, 0) * (indexer.getTypes().size() - 1);
            if (denominator == 0) {
                data.add(0.0);
            } else {
                data.add(numerator / denominator);
            }
        }
    }

    protected <N, T> void evaluateAllUjmpElementwise(final GraphAdapter<N, T> adapter) {
        GraphIndexer indexer = adapter.getIndexer();
        Matrix productSum = SparseMatrix.Factory.zeros(indexer.getSize(), 1);
        Matrix degrees = SparseMatrix.Factory.zeros(indexer.getSize(), 1);
        for (T type1 : adapter.getIndexer().getTypes()) {
            Matrix A = (Matrix) indexer.getAdjacencyMatrix().get(type1);
            for (T type2 : adapter.getIndexer().getTypes()) {
                if (type1 != type2) {
                    Matrix B = (Matrix) indexer.getAdjacencyMatrix().get(type2);
                    productSum = productSum.plus(A.mtimes(B).times(A).sum(Calculation.Ret.NEW, 1, false));
                }
            }
            Matrix degreeVector = A.sum(Calculation.Ret.NEW, 1, false);
            degrees = degrees.plus(degreeVector.times(degreeVector.minus(1)));
        }
        Matrix tcc = productSum.divide(degrees.times(indexer.getTypes().size() - 1));
        for (int i = 0; i < indexer.getSize(); i++) {
            double n = tcc.getAsDouble(i, 0);
            if (Double.isNaN(n)) {
                data.add(0.0);
            } else {
                data.add(n);
            }
        }
    }

    @Override
    public <N, T> void evaluate(final GraphAdapter<N, T> adapter) {
        evaluateAll(adapter);
    }

    @Override
    public List<Map<String, Object>> getTsvMaps(String[] header) {
        final List<Map<String, Object>> values = new ArrayList<>();
        int index = 0;
        for (Double v : data.getValues()) {
            Map<String, Object> value = new HashMap<>();
            value.put(header[0], "TypedClusteringCoefficientDef1");
            value.put(header[1], null);
            value.put(header[2], index);
            value.put(header[3], v);
            values.add(value);
            index++;
        }
        return values;
    }

    public enum Implementation {UJMP, UJMP_EW, OJALGO, EDGELIST}


}
