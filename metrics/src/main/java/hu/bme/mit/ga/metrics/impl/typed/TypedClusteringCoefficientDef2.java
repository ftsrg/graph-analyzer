package hu.bme.mit.ga.metrics.impl.typed;

import hu.bme.mit.ga.adapters.GraphAdapter;
import hu.bme.mit.ga.adapters.GraphIndexer;
import org.ojalgo.function.PrimitiveFunction;
import org.ojalgo.matrix.store.ElementsSupplier;
import org.ojalgo.matrix.store.MatrixStore;
import org.ojalgo.matrix.store.PrimitiveDenseStore;
import org.ojalgo.matrix.store.SparseStore;

import java.sql.Timestamp;
import java.util.*;

import static org.ojalgo.function.PrimitiveFunction.ADD;
import static org.ojalgo.function.PrimitiveFunction.MULTIPLY;

public class TypedClusteringCoefficientDef2 extends TypedClusteringCoefficient {
    protected Implementation implementation;

    public TypedClusteringCoefficientDef2(Implementation implementation) {
        super("TypedClusteringCoefficientDef2");
        this.implementation = implementation;
    }

    public TypedClusteringCoefficientDef2() {
        this(Implementation.OJALGO_EW);
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
                SparseStore<Double> B = (SparseStore<Double>) indexer.getAdjacencyMatrix2().get(type2);
                if (type1 != type2 ) {

                    for (T type3 : adapter.getIndexer().getTypes()) {
                    if ( type1 != type3 && type3 != type2) {
                        SparseStore<Double> C = (SparseStore<Double>) indexer.getAdjacencyMatrix2().get(type3);

                        System.out.println(new Timestamp(new Date().getTime()) + String.format(" Calculating clustering for types %s × %s x %s", type1, type2, type3));
                        SparseStore<Double> D = SparseStore.PRIMITIVE.make(indexer.getSize(), indexer.getSize());

                        System.out.println(new Timestamp(new Date().getTime()) + " -> AB = A * B");
                        SparseStore<Double> AB = SparseStore.PRIMITIVE.make(indexer.getSize(), indexer.getSize());
                        A.multiply(B).get().supplyTo(AB);

                        System.out.println(new Timestamp(new Date().getTime()) + " -> D = AB .* C");
                        AB.operateOnMatching(MULTIPLY, C).supplyTo(D);

                        System.out.println(new Timestamp(new Date().getTime()) + " -> sum = D * 1");
                        //productSum = C.reduceRows(Aggregator.SUM).get().add(productSum);
                        productSum = D.multiply(ones).add(productSum);
                    }
                }
                MatrixStore<Double> degreeVectorA = A.multiply(ones);
                MatrixStore<Double> degreeVectorB = B.multiply(ones);
                SparseStore<Double> E = SparseStore.PRIMITIVE.make(indexer.getSize(), indexer.getSize());
                A.operateOnMatching(MULTIPLY, B).supplyTo(E);
                final ElementsSupplier<Double> tmpIntermediateA = degreeVectorA.operateOnMatching(PrimitiveFunction.MULTIPLY, degreeVectorB);
                final ElementsSupplier<Double> tmpIntermediateB = tmpIntermediateA.operateOnMatching(PrimitiveFunction.SUBTRACT, E.multiply(ones));
                final ElementsSupplier<Double> tmpIntermediateC = tmpIntermediateB.operateOnMatching(ADD, degrees);
                PrimitiveDenseStore degrees_tmp = PrimitiveDenseStore.FACTORY.makeZero(indexer.getSize(), 1);
                tmpIntermediateC.supplyTo(degrees_tmp);
                degrees = degrees_tmp.copy();
                }
            }
        }
        degrees.multiply(indexer.getTypes().size()-2).supplyTo(degrees);
        for (int i = 0; i < indexer.getSize(); i++) {
            double numerator = productSum.doubleValue(i, 0);
            double denominator = degrees.doubleValue(i, 0) ;
            if (denominator == 0) {
                data.add(0.0);
            } else {
                data.add(numerator / denominator + 0.0);
            }
        }
    }

    @Override
    protected <N, T> void evaluateAll(final GraphAdapter<N, T> adapter) {
        switch (implementation) {
            case EDGELIST:
                evaluateAllEdgeList(adapter);
                break;
            case OJALGO_EW:
                evaluateAllOjalgoElementwise(adapter);
                break;
        }
    }


    public <N, T> void evaluateEdgeList(final GraphAdapter<N, T> adapter, final N node) {
        long interConnected = 0;
        long numberOfPossibleConnections = 0;
        double coef = 0.0;
        int numberOfNeighbors = 0;

        for (T type1 : adapter.getIndexer().getTypes(node)) {
            for (T type2 : adapter.getIndexer().getTypes(node)) {
                if (!type1.equals(type2)) {
                    numberOfNeighbors = adapter.getIndexer().getNeighbors(node, type1).size();
                    if (useHeuristic && numberOfNeighbors > maxNeighbours) {
                        coef = 0.0;
                        data.add(coef);
                        return;
                    }
                    numberOfNeighbors = adapter.getIndexer().getNeighbors(node, type2).size();
                    if (useHeuristic && numberOfNeighbors > maxNeighbours) {
                        coef = 0.0;
                        data.add(coef);
                        return;
                    }
                    for (N neighbor1 : adapter.getIndexer().getNeighbors(node, type1)) {
                        for (N neighbor2 : adapter.getIndexer().getNeighbors(node, type2)) {
                            if (neighbor1 != neighbor2) {
                                Set<T> types = new HashSet<>();
                                types.addAll(adapter.getIndexer().getTypes(neighbor1));
                                types.addAll(adapter.getIndexer().getTypes(neighbor2));

                                for (T type3 : types) {
                                    if (!type1.equals(type3) && !type2.equals(type3)) {
                                        numberOfPossibleConnections++;
                                        if (adapter.getIndexer().isAdjacentUndirected(neighbor1, neighbor2, type3)) {
                                            interConnected++;
                                        }
                                    }
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
            coef = interConnected / (double) numberOfPossibleConnections;
        }
        data.add(coef);
    }

    @Override
    public List<Map<String, Object>> getTsvMaps(String[] header) {
        final List<Map<String, Object>> values = new ArrayList<>();
        int index = 0;
        for (Double v : data.getValues()) {
            Map<String, Object> value = new HashMap<>();
            value.put(header[0], "TypedClusteringCoefficientDef2");
            value.put(header[1], null);
            value.put(header[2], index);
            value.put(header[3], v);
            values.add(value);
            index++;
        }
        return values;
    }

    @Override
    public <N, T> void evaluate(final GraphAdapter<N, T> adapter) {
        evaluateAll(adapter);
    }

    protected <N, T> void evaluateAllEdgeList(final GraphAdapter<N, T> adapter) {
        for (N node : adapter.getIndexer().getNodes()) {
            evaluateEdgeList(adapter, node);
        }

    }


    public enum Implementation {OJALGO_EW, EDGELIST}

}
