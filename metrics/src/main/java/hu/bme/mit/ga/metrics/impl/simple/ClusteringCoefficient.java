package hu.bme.mit.ga.metrics.impl.simple;

import hu.bme.mit.ga.adapters.GraphAdapter;
import hu.bme.mit.ga.adapters.GraphIndexer;
import hu.bme.mit.ga.base.data.ListData;
import hu.bme.mit.ga.metrics.AbstractGraphMetric;
import org.ejml.data.DMatrixRMaj;
import org.ejml.data.DMatrixSparseCSC;
import org.ejml.data.DMatrixSparseTriplet;
import org.ejml.ops.ConvertDMatrixStruct;
import org.ejml.simple.SimpleMatrix;
import org.ejml.sparse.csc.CommonOps_DSCC;
import org.ejml.sparse.csc.mult.ImplSparseSparseMult_DSCC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClusteringCoefficient extends AbstractGraphMetric<ListData<Double>> {
    protected Implementation implementation;

    public ClusteringCoefficient(Implementation implementation) {
        super("ClusteringCoefficientList", new ListData<>());
        this.implementation = implementation;
    }

    public ClusteringCoefficient() {
        this(Implementation.MATRIX);
    }

    @Override
    protected <N, T> void evaluateAll(GraphAdapter<N, T> adapter) {
        switch (implementation) {
            case MATRIX:
                evaluateAllEjml(adapter);
                break;
            case EDGELIST:
                evaluateEveryNode(adapter);
                break;
        }
    }

    public <N, T> void evaluateAllEjml(GraphAdapter<N, T> adapter) {
        GraphIndexer indexer = adapter.getIndexer();
        int size = indexer.getSize();
        DMatrixSparseTriplet tripletsA = indexer.getAdjacencyMatrixUntyped();
        DMatrixSparseCSC A = ConvertDMatrixStruct.convert(tripletsA, (DMatrixSparseCSC) null);
        DMatrixSparseCSC AA = new DMatrixSparseCSC(size, size, 0);
        DMatrixSparseCSC AAA = new DMatrixSparseCSC(size, size, 0);
        AAA.growMaxLength(Math.min(A.nz_length, A.nz_length), false);
        ImplSparseSparseMult_DSCC.mult(A, A, AA, null, null);
        CommonOps_DSCC.elementMult(AA, A, AAA, null, null);
        DMatrixRMaj rowSum = new DMatrixRMaj(size, 1);
        CommonOps_DSCC.sumRows(AAA, rowSum);

        DMatrixRMaj degreeVector = new DMatrixRMaj(size, 1);
        CommonOps_DSCC.sumRows(A, degreeVector);
        SimpleMatrix simpleDegreeVector = SimpleMatrix.wrap(degreeVector);
        SimpleMatrix wedges = simpleDegreeVector.elementMult(simpleDegreeVector.minus(1));

        for (int i = 0; i < size; i++) {
            double n_triangles = rowSum.get(i, 0);
            double n_wedges = wedges.get(i, 0);
            if (n_wedges == 0) {
                data.add(0.0);
            } else {
                data.add(n_triangles / n_wedges);
            }
        }
    }

    @Override
    public <N, T> void evaluate(GraphAdapter<N, T> adapter, N element) {
        long interConnected = 0;
        long numberOfNeighbors = 0;
        double clusteringCoef = 0.0;
        for (N neighbor1 : adapter.getIndexer().getNeighbors(element)) {
            for (N neighbor2 : adapter.getIndexer().getNeighbors(element)) {
                if (neighbor1 != neighbor2) {
                    if (adapter.getIndexer().isAdjacentUndirected(neighbor1, neighbor2)) {
                        interConnected++;
                    }
                }
            }
        }

        numberOfNeighbors = adapter.getIndexer().getNeighbors(element).size();
        if (numberOfNeighbors < 2) {
            clusteringCoef = 0.0;
        } else {
            clusteringCoef = interConnected / (double) (numberOfNeighbors * (numberOfNeighbors - 1));
        }
        data.add(clusteringCoef);
    }

    @Override
    public List<Map<String, Object>> getTsvMaps(String[] header) {
        final List<Map<String, Object>> values = new ArrayList<>();
        int index = 0;
        for (Double v : data.getValues()) {
            Map<String, Object> value = new HashMap<>();
            value.put(header[0], "ClusteringCoefficientList");
            value.put(header[1], null);
            value.put(header[2], index);
            value.put(header[3], v);
            values.add(value);
            index++;
        }
        return values;
    }

    public enum Implementation {EDGELIST, MATRIX}

}
