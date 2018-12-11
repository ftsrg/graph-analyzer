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


public class TypedClusteringCoefficientDef1 extends TypedClusteringCoefficient {

    protected Implementation implementation;


    public TypedClusteringCoefficientDef1(Implementation implementation) {
        super("TypedClusteringCoefficientDef1");
        this.implementation = implementation;
    }

    public TypedClusteringCoefficientDef1() {
        this(Implementation.MATRIX_EW_STREAM);
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
        DMatrixSparseTriplet tripletsA = (DMatrixSparseTriplet) indexer.getAdjacencyMatrix().get(type1);
        DMatrixSparseCSC A = ConvertDMatrixStruct.convert(tripletsA, (DMatrixSparseCSC) null);
        DMatrixSparseTriplet tripletsB = (DMatrixSparseTriplet) indexer.getAdjacencyMatrix().get(type2);
        DMatrixSparseCSC B = ConvertDMatrixStruct.convert(tripletsB, (DMatrixSparseCSC) null);
        DMatrixSparseCSC AB = new DMatrixSparseCSC(size, size, 0);
        DMatrixSparseCSC ABA = new DMatrixSparseCSC(size, size, 0);
        ABA.growMaxLength(Math.min(A.nz_length,B.nz_length),false);
        ImplSparseSparseMult_DSCC.mult(A, B, AB, null, null);
        CommonOps_DSCC.elementMult(AB, A, ABA, null, null);
        DMatrixRMaj rowSum = new DMatrixRMaj(size, 1);
        CommonOps_DSCC.sumRows(ABA, rowSum);
        return SimpleMatrix.wrap(rowSum);
    }
    protected <N, T> SimpleMatrix countWedgesEjmlEw(T type1, final GraphAdapter<N, T> adapter ) {
        GraphIndexer indexer = adapter.getIndexer();
        int size = indexer.getSize();
        DMatrixSparseTriplet tripletsA = (DMatrixSparseTriplet) indexer.getAdjacencyMatrix().get(type1);
        DMatrixSparseCSC A = ConvertDMatrixStruct.convert(tripletsA, (DMatrixSparseCSC) null);
        DMatrixRMaj degreeVector = new DMatrixRMaj(size, 1);
        CommonOps_DSCC.sumRows(A, degreeVector);
        SimpleMatrix simpleDegreeVector = SimpleMatrix.wrap(degreeVector);
        return simpleDegreeVector.elementMult(simpleDegreeVector.minus(1));
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

    private void addToPerformancemap(long millis) {
        Map<String, Object> performance = new HashMap<>();
        performance.put("metric", this.name);
        performance.put("algo", this.implementation.toString());
        performance.put("t", millis);
        performanceData.add(performance);
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


    public enum Implementation {EDGELIST, MATRIX_EW_STREAM}


}
