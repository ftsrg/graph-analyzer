package hu.bme.mit.ga.metrics.impl.simple;

import hu.bme.mit.ga.base.data.ListData;
import hu.bme.mit.ga.adapters.GraphAdapter;
import hu.bme.mit.ga.metrics.AbstractGraphMetric;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Degrees extends AbstractGraphMetric<ListData<Integer>> {

    public Degrees() {
        super("DegreeList", new ListData<>());
    }

    @Override
    protected <N, T> void evaluateAll(GraphAdapter<N, T> adapter) {
        evaluateEveryNode(adapter);
    }

    @Override
    public <N, T> void evaluate(GraphAdapter<N, T> adapter, N element) {
        int degree = adapter.getIndexer().getDegree(element);
        data.add(degree);
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
