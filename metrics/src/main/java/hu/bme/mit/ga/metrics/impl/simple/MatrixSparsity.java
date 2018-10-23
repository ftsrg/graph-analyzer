package hu.bme.mit.ga.metrics.impl.simple;

import hu.bme.mit.ga.adapters.GraphAdapter;
import hu.bme.mit.ga.adapters.GraphIndexer;
import hu.bme.mit.ga.base.data.ScalarData;
import hu.bme.mit.ga.metrics.AbstractGraphMetric;
import org.ejml.data.DMatrixRMaj;
import org.ejml.data.DMatrixSparseCSC;
import org.ejml.data.DMatrixSparseTriplet;
import org.ejml.ops.ConvertDMatrixStruct;
import org.ejml.simple.SimpleMatrix;
import org.ejml.sparse.csc.CommonOps_DSCC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatrixSparsity  extends AbstractGraphMetric<ScalarData<Double>> {
    public MatrixSparsity() {
        super("MatrixSparsity", new ScalarData<>());
    }
    @Override
    protected <N, T> void evaluateAll(GraphAdapter<N, T> adapter) {
        GraphIndexer<N, T> indexer = adapter.getIndexer();
        int size = indexer.getSize();
        DMatrixSparseTriplet triplets =  indexer.getAdjacencyMatrixEjmlUntyped();
        DMatrixSparseCSC A = ConvertDMatrixStruct.convert(triplets, (DMatrixSparseCSC) null);
        DMatrixRMaj rowSum = new DMatrixRMaj(size, 1);
        CommonOps_DSCC.sumRows(A,rowSum);
        double d = SimpleMatrix.wrap(rowSum).elementSum();
        double n = size*size;
        data.setValue(d/n);
    }

    @Override
    public List<Map<String, Object>> getTsvMaps(String[] header) {
        final List<Map<String, Object>> values = new ArrayList<>();
        Map<String, Object> value = new HashMap<>();
        value.put(header[0], "MatrixSparsity");
        value.put(header[1], null);
        value.put(header[2], null);
        value.put(header[3], data.getValue());
        values.add(value);
        return values;
    }

}
