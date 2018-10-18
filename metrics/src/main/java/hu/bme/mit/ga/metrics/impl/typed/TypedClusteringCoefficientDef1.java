package hu.bme.mit.ga.metrics.impl.typed;

import com.google.common.collect.Lists;
import hu.bme.mit.ga.adapters.GraphAdapter;
import hu.bme.mit.ga.adapters.GraphIndexer;
import org.ejml.data.DMatrixRMaj;
import org.ejml.data.DMatrixSparseCSC;
import org.ejml.data.DMatrixSparseTriplet;
import org.ejml.ops.ConvertDMatrixStruct;
import org.ejml.simple.SimpleMatrix;
import org.ejml.sparse.csc.CommonOps_DSCC;
import org.ejml.sparse.csc.mult.ImplSparseSparseMult_DSCC;
import org.ojalgo.array.Array1D;
import org.ojalgo.function.PrimitiveFunction;
import org.ojalgo.matrix.store.ElementsSupplier;
import org.ojalgo.matrix.store.MatrixStore;
import org.ojalgo.matrix.store.PrimitiveDenseStore;
import org.ojalgo.matrix.store.SparseStore;
import org.ujmp.core.Matrix;
import org.ujmp.core.SparseMatrix;
import org.ujmp.core.calculation.Calculation;

import java.util.*;

import static org.ojalgo.function.PrimitiveFunction.*;

public class TypedClusteringCoefficientDef1 extends TypedClusteringCoefficient {

    protected Implementation implementation;


    public TypedClusteringCoefficientDef1(Implementation implementation) {
        super("TypedClusteringCoefficientDef1");
        this.implementation = implementation;
    }

    public TypedClusteringCoefficientDef1() {
        this(Implementation.EJML_EW_STREAM);
    }

    @Override
    public String getName() {
        return name + " " + implementation.name();
    }

    protected <N, T> void evaluateAllEjmlElementwiseStream(final GraphAdapter<N, T> adapter) {
        GraphIndexer indexer = adapter.getIndexer();
        List<T> typeList = new ArrayList<>();
        typeList.addAll(indexer.getTypes());
        List<List<T>> typePairs = new ArrayList<>();
        for (T type1 : typeList) {
            for (T type2 : typeList) {
                if (type1 != type2) {
                    typePairs.add(Lists.newArrayList(type1, type2));
                }
            }
        }
        SimpleMatrix wedges = typeList.stream().parallel().map(x -> countWedgesEjmlEw(x, adapter))
            .reduce(new SimpleMatrix(indexer.getSize(),1),(a, b) -> a.plus(b));
        SimpleMatrix triangles = typePairs
            .stream().parallel().map(x -> countTrianglesEjmlEw(x.get(0), x.get(1), adapter))
            .reduce(new SimpleMatrix(indexer.getSize(),1), (a, b) -> a.plus(b)
            );

        for (int i = 0; i < indexer.getSize(); i++) {
            double n_triangles = triangles.get(i, 0);
            double n_wedges = wedges.get(i, 0) * (indexer.getTypes().size() - 1);
            if (n_wedges == 0) {
                data.add(0.0);
            } else {
                data.add(n_triangles / n_wedges);
            }
        }
    }


    protected <N, T> SimpleMatrix countTrianglesEjmlEw(T type1, T type2, final GraphAdapter<N, T> adapter ) {
        GraphIndexer indexer = adapter.getIndexer();
        int size = indexer.getSize();
        SimpleMatrix ones = new SimpleMatrix(size, 1);
        ones.fill(1);
        DMatrixSparseTriplet tripletsA = (DMatrixSparseTriplet) indexer.getAdjacencyMatrixEjml().get(type1);
        DMatrixSparseCSC A = ConvertDMatrixStruct.convert(tripletsA, (DMatrixSparseCSC) null);
        DMatrixSparseTriplet tripletsB = (DMatrixSparseTriplet) indexer.getAdjacencyMatrixEjml().get(type2);
        DMatrixSparseCSC B = ConvertDMatrixStruct.convert(tripletsB, (DMatrixSparseCSC) null);
        DMatrixSparseCSC AB = new DMatrixSparseCSC(size, size, 0);
        DMatrixSparseCSC ABA = new DMatrixSparseCSC(size, size, 0);
        ImplSparseSparseMult_DSCC.mult(A, B, AB, null, null);
        CommonOps_DSCC.elementMult(AB, A, ABA, null, null);
        DMatrixRMaj rowSum = new DMatrixRMaj(size, 1);
        CommonOps_DSCC.mult(ABA, ones.getMatrix(), rowSum);
        return SimpleMatrix.wrap(rowSum);
    }
    protected <N, T> SimpleMatrix countWedgesEjmlEw(T type1, final GraphAdapter<N, T> adapter ) {
        GraphIndexer indexer = adapter.getIndexer();
        int size = indexer.getSize();
        SimpleMatrix ones = new SimpleMatrix(size, 1);
        ones.fill(1);
        DMatrixSparseTriplet tripletsA = (DMatrixSparseTriplet) indexer.getAdjacencyMatrixEjml().get(type1);
        DMatrixSparseCSC A = ConvertDMatrixStruct.convert(tripletsA, (DMatrixSparseCSC) null);
        DMatrixRMaj degreeVector = new DMatrixRMaj(size, 1);
        CommonOps_DSCC.mult(A, ones.getMatrix(), degreeVector);
        SimpleMatrix simpleDegreeVector = SimpleMatrix.wrap(degreeVector);
        return simpleDegreeVector.elementMult(simpleDegreeVector.minus(1));
    }


    protected <N, T> void evaluateAllEjmlElementwise(final GraphAdapter<N, T> adapter) {
        GraphIndexer indexer = adapter.getIndexer();
        int size = indexer.getSize();
        SimpleMatrix productSum = new SimpleMatrix(size, 1);
        SimpleMatrix degrees = new SimpleMatrix(size, 1);
        SimpleMatrix ones = new SimpleMatrix(size, 1);
        ones.fill(1);

        for (T type1 : adapter.getIndexer().getTypes()) {
            DMatrixSparseTriplet tripletsA = (DMatrixSparseTriplet) indexer.getAdjacencyMatrixEjml().get(type1);
            DMatrixSparseCSC A = ConvertDMatrixStruct.convert(tripletsA, (DMatrixSparseCSC) null);
            for (T type2 : adapter.getIndexer().getTypes()) {
                if (type1 != type2) {
                    DMatrixSparseTriplet tripletsB = (DMatrixSparseTriplet) indexer.getAdjacencyMatrixEjml().get(type2);
                    DMatrixSparseCSC B = ConvertDMatrixStruct.convert(tripletsB, (DMatrixSparseCSC) null);
                    DMatrixSparseCSC AB = new DMatrixSparseCSC(size, size, 0);
                    DMatrixSparseCSC ABA = new DMatrixSparseCSC(size, size, 0);
                    ImplSparseSparseMult_DSCC.mult(A, B, AB, null, null);
                    CommonOps_DSCC.elementMult(AB, A, ABA, null, null);
                    DMatrixRMaj rowSum = new DMatrixRMaj(size, 1);
                    CommonOps_DSCC.mult(ABA, ones.getMatrix(), rowSum);
                    productSum = productSum.plus(SimpleMatrix.wrap(rowSum));
                }
            }
            DMatrixRMaj degreeVector = new DMatrixRMaj(size, 1);
            CommonOps_DSCC.mult(A, ones.getMatrix(), degreeVector);
            SimpleMatrix simpleDegreeVector = SimpleMatrix.wrap(degreeVector);
            degrees = degrees.plus(simpleDegreeVector.elementMult(simpleDegreeVector.minus(1)));
        }
        for (int i = 0; i < indexer.getSize(); i++) {
            double numerator = productSum.get(i, 0);
            double denominator = degrees.get(i, 0) * (indexer.getTypes().size() - 1);
            if (denominator == 0) {
                data.add(0.0);
            } else {
                data.add(numerator / denominator);
            }
        }
    }


    protected <N, T> void evaluateAllOjalgoElementwise(final GraphAdapter<N, T> adapter) {
        GraphIndexer indexer = adapter.getIndexer();
        MatrixStore productSum = PrimitiveDenseStore.FACTORY.makeZero(indexer.getSize(), 1);
        PrimitiveDenseStore degrees = PrimitiveDenseStore.FACTORY.makeZero(indexer.getSize(), 1);
        PrimitiveDenseStore ones = PrimitiveDenseStore.FACTORY.makeZero(indexer.getSize(), 1);
        ones.operateOnAll(ADD, 1).supplyTo(ones);
        for (T type1 : adapter.getIndexer().getTypes()) {
            SparseStore<Double> A = (SparseStore<Double>) indexer.getAdjacencyMatrix2().get(type1);
            for (T type2 : adapter.getIndexer().getTypes()) {
                if (type1 != type2) {
                    SparseStore<Double> B = (SparseStore<Double>) indexer.getAdjacencyMatrix2().get(type2);
                    SparseStore<Double> C = SparseStore.PRIMITIVE.make(indexer.getSize(), indexer.getSize());

                    SparseStore<Double> AB = SparseStore.PRIMITIVE.make(indexer.getSize(), indexer.getSize());
                    A.multiply(B).supplyTo(AB);

                    AB.operateOnMatching(MULTIPLY, A).supplyTo(C);

                    productSum = C.multiply(ones).add(productSum);
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

    private <N, T> MatrixStore countTrianglesForTypePair(T type1, T type2, final GraphAdapter<N, T> adapter) {
        GraphIndexer<N, T> indexer = adapter.getIndexer();
        PrimitiveDenseStore ones = PrimitiveDenseStore.FACTORY.makeZero(indexer.getSize(), 1);
        ones.operateOnAll(ADD, 1).supplyTo(ones);
        SparseStore<Double> A = indexer.getAdjacencyMatrix2().get(type1);
        SparseStore<Double> B = indexer.getAdjacencyMatrix2().get(type2);
        SparseStore<Double> C = SparseStore.PRIMITIVE.make(indexer.getSize(), indexer.getSize());
        SparseStore<Double> AB = SparseStore.PRIMITIVE.make(indexer.getSize(), indexer.getSize());
        A.multiply(B).supplyTo(AB);
        AB.operateOnMatching(MULTIPLY, A).supplyTo(C);
        return C.multiply(ones);
    }

    private <N, T> MatrixStore countWedgesForType(T type, final GraphAdapter<N, T> adapter) {
        GraphIndexer indexer = adapter.getIndexer();
        PrimitiveDenseStore ones = PrimitiveDenseStore.FACTORY.makeZero(indexer.getSize(), 1);
        ones.operateOnAll(ADD, 1).supplyTo(ones);
        SparseStore<Double> A = (SparseStore<Double>) indexer.getAdjacencyMatrix2().get(type);
        MatrixStore<Double> degreeVector = A.multiply(ones);
        PrimitiveDenseStore degrees = PrimitiveDenseStore.FACTORY.makeZero(indexer.getSize(), 1);
        final ElementsSupplier<Double> tmpIntermediateA = degreeVector.operateOnMatching(SUBTRACT, ones);
        final ElementsSupplier<Double> tmpIntermediateB = tmpIntermediateA.operateOnMatching(PrimitiveFunction.MULTIPLY, degreeVector);
        final ElementsSupplier<Double> tmpIntermediateC = tmpIntermediateB.operateOnAll(PrimitiveFunction.MULTIPLY, indexer.getTypes().size() - 1);
        tmpIntermediateC.supplyTo(degrees);
        return degrees;
    }


    protected <N, T> void evaluateAllOjalgoElementwiseStream(final GraphAdapter<N, T> adapter) {
        GraphIndexer indexer = adapter.getIndexer();
        List<T> typeList = new ArrayList<>();
        typeList.addAll(indexer.getTypes());
        List<List<T>> typePairs = new ArrayList<>();
        for (T type1 : typeList) {
            for (T type2 : typeList) {
                if (type1 != type2) {
                    typePairs.add(Lists.newArrayList(type1, type2));
                }
            }
        }
        MatrixStore wedges = typeList.stream().parallel().map(x -> countWedgesForType(x, adapter))
            .reduce((a, b) -> a.add(b)).get();
        MatrixStore triangles = typePairs
            .stream().parallel().map(x -> countTrianglesForTypePair(x.get(0), x.get(1), adapter))
            .reduce(PrimitiveDenseStore.FACTORY.makeZero(indexer.getSize(), 1), (a, b) -> a.add(b)
            ).get();

        for (int i = 0; i < indexer.getSize(); i++) {
            double n_triangles = triangles.doubleValue(i, 0);
            double n_wedges = wedges.doubleValue(i, 0);
            if (n_wedges == 0) {
                data.add(0.0);
            } else {
                data.add(n_triangles / n_wedges);
            }
        }
    }


    protected <N, T> void evaluateAllOjalgo(final GraphAdapter<N, T> adapter) {
        GraphIndexer indexer = adapter.getIndexer();
        final Array1D<Double> productSum = Array1D.DIRECT64.makeZero(indexer.getSize());
        PrimitiveDenseStore degrees = PrimitiveDenseStore.FACTORY.makeZero(indexer.getSize(), 1);
        PrimitiveDenseStore ones = PrimitiveDenseStore.FACTORY.makeZero(indexer.getSize(), 1);
        ones.operateOnAll(ADD, 1).supplyTo(ones);
        for (T type1 : adapter.getIndexer().getTypes()) {
            SparseStore<Double> A = (SparseStore<Double>) indexer.getAdjacencyMatrix2().get(type1);
            for (T type2 : adapter.getIndexer().getTypes()) {
                if (type1 != type2) {
                    SparseStore<Double> B = (SparseStore<Double>) indexer.getAdjacencyMatrix2().get(type2);
                    productSum.modifyMatching(ADD, A.multiply(B).multiply(A).sliceDiagonal());
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
            double numerator = productSum.get(i);
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
        long start = System.currentTimeMillis();
        switch (implementation) {
            case UJMP:
                evaluateAllUjmp(adapter);
                break;
            case UJMP_EW:
                evaluateAllUjmpElementwise(adapter);
                break;
            case EDGELIST:
                evaluateAllEdgeList(adapter);
                break;
            case OJALGO:
                evaluateAllOjalgo(adapter);
                break;
            case OJALGO_EW:
                evaluateAllOjalgoElementwise(adapter);
                break;
            case OJALGO_EW_STREAM:
                evaluateAllOjalgoElementwiseStream(adapter);
                break;
            case EJML_EW:
                evaluateAllEjmlElementwise(adapter);
                break;
            case EJML_EW_STREAM:
                evaluateAllEjmlElementwiseStream(adapter);
                break;
        }
        long end = System.currentTimeMillis();
        addToPerformancemap(end - start);
    }

    private void addToPerformancemap(long millis) {
        Map<String, Object> performance = new HashMap<>();
        performance.put("metric", this.name);
        performance.put("algo", this.implementation.toString());
        performance.put("t", millis);
        performanceData.add(performance);
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

    protected <N, T> void evaluateAllEdgeList(final GraphAdapter<N, T> adapter) {
        for (N node : adapter.getIndexer().getNodes()) {
            evaluateEdgeList(adapter, node);
        }

    }

    public <N, T> void evaluateEdgeList(final GraphAdapter<N, T> adapter, final N node) {
        long interConnected = 0;
        long numberOfNeighbors = 0;
        long numberOfPossibleConnections = 0;
        double coef = 0.0;

        for (T type1 : adapter.getIndexer().getTypes(node)) {
            numberOfNeighbors = adapter.getIndexer().getNeighbors(node, type1).size();
            if (useHeuristic && numberOfNeighbors > maxNeighbours) {
                coef = 0.0;
                data.add(coef);
                return;
            }
            numberOfPossibleConnections += numberOfNeighbors * (numberOfNeighbors - 1);
            for (N neighbor1 : adapter.getIndexer().getNeighbors(node, type1)) {
                for (N neighbor2 : adapter.getIndexer().getNeighbors(node, type1)) {
                    if (neighbor1 != neighbor2) {
                        Set<T> types = adapter.getIndexer().getTypes(neighbor1);
                        for (T type2 : types) {
                            if (type1 != type2) {
                                if (adapter.getIndexer().isAdjacentUndirected(neighbor1, neighbor2, type2)) {
                                    interConnected++;
                                }
                            }

                        }
                    }
                }
            }
        }
        if (numberOfPossibleConnections == 0) {
            coef = 0.0;
        } else {
            coef = (double) interConnected / (double) numberOfPossibleConnections;
        }
        data.add(coef);
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


    public enum Implementation {EDGELIST, UJMP, UJMP_EW, OJALGO, OJALGO_EW, OJALGO_EW_STREAM, EJML_EW, EJML_EW_STREAM}


}
