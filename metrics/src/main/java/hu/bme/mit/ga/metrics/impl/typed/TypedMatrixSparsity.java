package hu.bme.mit.ga.metrics.impl.typed;

import hu.bme.mit.ga.adapters.GraphIndexer;
import hu.bme.mit.ga.base.data.MapData;
import hu.bme.mit.ga.adapters.GraphAdapter;
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

public class TypedMatrixSparsity extends AbstractGraphMetric<MapData<String, Double>> {

    public TypedMatrixSparsity() {
        super("TypedMatrixSparsity", new MapData<>());
    }

    @Override
    protected <N, T> void evaluateAll(GraphAdapter<N, T> adapter) {
        for (T type : adapter.getIndexer().getTypes()) {
            evaluateT(adapter, type);
        }
    }

    protected <N, T> void evaluateT(GraphAdapter<N, T> adapter, T type) {
        GraphIndexer indexer = adapter.getIndexer();
        int n = adapter.getIndexer().getSize();
        double nonzero = (double)indexer.getTypedEdges().get(type)*2;
        double size = n*n;
        data.put(type.toString(), nonzero / size);
    }

    @Override
    public List<Map<String, Object>> getTsvMaps(String[] header) {
        final List<Map<String, Object>> values = new ArrayList<>();
        for (String type : data.getValues().keySet()) {
            Double value = data.getValues().get(type);
            Map<String, Object> row = new HashMap<>();
            row.put(header[0], "TypedMatrixSparsity");
            row.put(header[1], type);
            row.put(header[2], null);
            row.put(header[3], value);
            values.add(row);
        }
        return values;
    }
}
