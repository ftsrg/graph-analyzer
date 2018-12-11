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

import java.util.*;

public class TypedClusteringCoefficientDef2 extends TypedClusteringCoefficient {
    protected Implementation implementation;

    public TypedClusteringCoefficientDef2(Implementation implementation) {
        super("TypedClusteringCoefficientDef2");
        this.implementation = implementation;
    }

    @Override
    public String getName() {
        return name + " " + implementation.name();
    }


    public TypedClusteringCoefficientDef2() {
        this(Implementation.MATRIX_EW_STREAM);
    }


    protected <N, T> void evaluateAllEjmlElementwiseStream(final GraphAdapter<N, T> adapter) {
        GraphIndexer indexer = adapter.getIndexer();
        List<List<T>> typeTriadList = new ArrayList<>();
        List<List<T>> typePairList = new ArrayList<>();
        for (T type1 : (Set<T>) indexer.getTypes()) {
            for (T type2 : (Set<T>) indexer.getTypes()) {
                if (type1 != type2) {
                    typePairList.add(Lists.newArrayList(type1, type2));
                    for (T type3 : (Set<T>) indexer.getTypes()) {
                        if (type3 != type2 && type3 != type1) {
                            typeTriadList.add(Lists.newArrayList(type1, type2, type3));
                        }
                    }
                }
            }
        }
        SimpleMatrix wedges = typePairList.stream().parallel().map(x -> countWedgesForTypePairEjml(x.get(0), x.get(1), adapter))
            .reduce(new SimpleMatrix(indexer.getSize(), 1), (a, b) -> a.plus(b));
        SimpleMatrix triangles = typeTriadList
            .stream().parallel().map(x -> countTrianglesForTypeTriadEjml(x.get(0), x.get(1), x.get(2), adapter))
            .reduce(new SimpleMatrix(indexer.getSize(), 1), (a, b) -> a.plus(b)
            );

        for (int i = 0; i < indexer.getSize(); i++) {
            double n_triangles = triangles.get(i, 0);
            double n_wedges = wedges.get(i, 0) * (indexer.getTypes().size() - 2);
            if (n_wedges == 0) {
                data.add(0.0);
            } else {
                data.add(n_triangles / n_wedges);
            }
        }
    }

    protected <N, T> SimpleMatrix countTrianglesForTypeTriadEjml(T type1, T type2, T type3, final GraphAdapter<N, T> adapter) {
        GraphIndexer indexer = adapter.getIndexer();
        int size = indexer.getSize();
        DMatrixSparseTriplet tripletsA = (DMatrixSparseTriplet) indexer.getAdjacencyMatrix().get(type1);
        DMatrixSparseCSC A = ConvertDMatrixStruct.convert(tripletsA, (DMatrixSparseCSC) null);
        DMatrixSparseTriplet tripletsB = (DMatrixSparseTriplet) indexer.getAdjacencyMatrix().get(type2);
        DMatrixSparseCSC B = ConvertDMatrixStruct.convert(tripletsB, (DMatrixSparseCSC) null);
        DMatrixSparseCSC AB = new DMatrixSparseCSC(size, size, 0);
        ImplSparseSparseMult_DSCC.mult(A, B, AB, null, null);
        DMatrixSparseTriplet tripletsC = (DMatrixSparseTriplet) indexer.getAdjacencyMatrix().get(type3);
        DMatrixSparseCSC C = ConvertDMatrixStruct.convert(tripletsC, (DMatrixSparseCSC) null);
        DMatrixSparseCSC ABC = new DMatrixSparseCSC(size, size, 0);
        ABC.growMaxLength(Math.min(A.nz_length, B.nz_length), false);
        CommonOps_DSCC.elementMult(AB, C, ABC, null, null);
        DMatrixRMaj rowSum = new DMatrixRMaj(size, 1);
        CommonOps_DSCC.sumRows(ABC, rowSum);
        return SimpleMatrix.wrap(rowSum);
    }

    protected <N, T> SimpleMatrix countWedgesForTypePairEjml(T type1, T type2, final GraphAdapter<N, T> adapter) {
        GraphIndexer indexer = adapter.getIndexer();
        int size = indexer.getSize();
        DMatrixSparseTriplet tripletsA = (DMatrixSparseTriplet) indexer.getAdjacencyMatrix().get(type1);
        DMatrixSparseCSC A = ConvertDMatrixStruct.convert(tripletsA, (DMatrixSparseCSC) null);
        DMatrixSparseTriplet tripletsB = (DMatrixSparseTriplet) indexer.getAdjacencyMatrix().get(type2);
        DMatrixSparseCSC B = ConvertDMatrixStruct.convert(tripletsB, (DMatrixSparseCSC) null);

        DMatrixRMaj degreeVectorA = new DMatrixRMaj(size, 1);
        CommonOps_DSCC.sumRows(A, degreeVectorA);

        DMatrixRMaj degreeVectorB = new DMatrixRMaj(size, 1);
        CommonOps_DSCC.sumRows(B, degreeVectorB);

        DMatrixSparseCSC ABew = new DMatrixSparseCSC(size, size, 0);
        CommonOps_DSCC.elementMult(A, B, ABew, null, null);

        SimpleMatrix simpleDegreeVectorA = SimpleMatrix.wrap(degreeVectorA);
        SimpleMatrix simpleDegreeVectorB = SimpleMatrix.wrap(degreeVectorB);
        DMatrixRMaj degreeVectorAB = new DMatrixRMaj(size, 1);
        CommonOps_DSCC.sumRows(ABew, degreeVectorAB);
        return simpleDegreeVectorA.elementMult(simpleDegreeVectorB).minus(SimpleMatrix.wrap(degreeVectorAB));
    }

    private void addToPerformancemap(long millis) {
        Map<String, Object> performance = new HashMap<>();
        performance.put("metric", this.name);
        performance.put("algo", this.implementation.toString());
        performance.put("t", millis);
        performanceData.add(performance);
    }

    @Override
    protected <N, T> void evaluateAll(final GraphAdapter<N, T> adapter) {
        long start = System.currentTimeMillis();
        switch (implementation) {
            case EDGELIST:
                evaluateAllEdgeList(adapter);
                break;
            case MATRIX_EW_STREAM:
                evaluateAllEjmlElementwiseStream(adapter);
                break;
        }
        long end = System.currentTimeMillis();
        addToPerformancemap(end - start);
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

    public enum Implementation {EDGELIST, MATRIX_EW_STREAM}

}
