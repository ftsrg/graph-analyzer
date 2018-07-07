package hu.bme.mit.mba.modelmetrics.impl.simple;

import hu.bme.mit.mba.base.data.ScalarData;
import hu.bme.mit.mba.modeladapters.ModelAdapter;
import hu.bme.mit.mba.modelmetrics.AbstractModelMetric;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberOfEdges extends AbstractModelMetric<ScalarData<Integer>> {

    public NumberOfEdges() {
        super("NumberOfEdges", new ScalarData<>());
    }

    @Override
    public void clear() {
        data.clear();
    }

    @Override
    protected <N, T> void evaluateAll(ModelAdapter<N, T> adapter) {
        data.setValue(adapter.getNumberOfEdges());
    }

    @Override
    public <N, T> void evaluate(ModelAdapter<N, T> adapter, N element) {
        data.setValue(adapter.getDegree(element));
    }

    @Override
    public List<Map<String, Object>> getTsvMaps(String[] header) {
        final List<Map<String, Object>> values = new ArrayList<>();
        Map<String, Object> value = new HashMap<>();
        value.put(header[0], "NumberOfEdges");
        value.put(header[1], null);
        value.put(header[2], null);
        value.put(header[3], data.getValue());
        values.add(value);
        return values;
    }

}
