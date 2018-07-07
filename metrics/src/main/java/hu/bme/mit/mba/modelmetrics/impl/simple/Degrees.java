package hu.bme.mit.mba.modelmetrics.impl.simple;

import hu.bme.mit.mba.base.data.ListData;
import hu.bme.mit.mba.base.data.MapData;
import hu.bme.mit.mba.modeladapters.ModelAdapter;
import hu.bme.mit.mba.modelmetrics.AbstractModelMetric;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Degrees extends AbstractModelMetric<ListData<Integer>> {

    public Degrees() {
        super("DegreeList", new ListData<>());
    }

    @Override
    protected <N, T> void evaluateAll(ModelAdapter<N, T> adapter) {
        evaluateEveryNode(adapter);
    }

    @Override
    public <N, T> void trace() {
        tracing = new MapData<N, Integer>();
    }

    @Override
    public <N, T> MapData<N, Integer> getTracing() {
        return (MapData<N, Integer>) tracing;
    }

    @Override
    public <N, T> void evaluate(ModelAdapter<N, T> adapter, N element) {
        int degree = adapter.getDegree(element);
        data.add(degree);
        updateTracing(element, degree);
    }

    protected <N> void updateTracing(N element, int degree) {
        if (tracing != null) {
            getTracing().put(element, degree);
        }
    }

    @Override
    public List<Map<String, Object>> getTsvMaps(String[] header) {
        final List<Map<String, Object>> values = new ArrayList<>();
        int index = 0;
        for (Integer i : data.getValues()) {
            Map<String, Object> value = new HashMap<>();
            value.put(header[0], "DegreeList");
            value.put(header[1], null);
            value.put(header[2], index);
            value.put(header[3], i);
            values.add(value);
            index++;
        }
        return values;
    }


}
