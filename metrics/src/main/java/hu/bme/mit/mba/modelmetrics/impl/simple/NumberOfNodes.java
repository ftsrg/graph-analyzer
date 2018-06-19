package hu.bme.mit.mba.modelmetrics.impl.simple;

import hu.bme.mit.mba.base.data.ScalarData;
import hu.bme.mit.mba.modeladapters.ModelAdapter;
import hu.bme.mit.mba.modelmetrics.AbstractModelMetric;
import hu.bme.mit.mba.modelmetrics.incr.IncrementalModelEvaluator;
import org.supercsv.io.ICsvMapWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberOfNodes extends AbstractModelMetric<ScalarData<Integer>> implements IncrementalModelEvaluator {

    public NumberOfNodes() {
        super("NumberOfNodes", new ScalarData<>());
    }

    @Override
    public void clear() {
        data.setValue(0);
    }

    @Override
    protected <N, T> void evaluateAll(ModelAdapter<N, T> adapter) {
        data.setValue(adapter.getNumberOfNodes());
    }

    @Override
    public <N, T> void reevaluateNewEdge(ModelAdapter<N, T> adapter, T type, N sourceNode, N targetNode) {
        evaluateAll(adapter);
    }

    @Override
    public List<Map<String, Object>> getTsvMaps(String[] header) {
        final List<Map<String, Object>> values = new ArrayList<>();
        Map<String, Object> row = new HashMap<>();
        row.put(header[0], "NumberOfNodes");
        row.put(header[1], null);
        row.put(header[2], null);
        row.put(header[3], data.getValue());
        values.add(row);
        return values;
    }

}
