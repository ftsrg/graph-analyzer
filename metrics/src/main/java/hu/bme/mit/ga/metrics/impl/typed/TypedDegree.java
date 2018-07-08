package hu.bme.mit.ga.metrics.impl.typed;

import hu.bme.mit.ga.base.data.MappedListData;
import hu.bme.mit.ga.adapters.GraphAdapter;
import hu.bme.mit.ga.metrics.AbstractGraphMetric;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypedDegree extends AbstractGraphMetric<MappedListData<String, Integer>> {

    public TypedDegree() {
        super("TypedDegree", new MappedListData<>());
    }

    @Override
    protected <N, T> void evaluateAll(final GraphAdapter<N, T> adapter) {
        for (T type : adapter.getIndexer().getDimensions()) {
            for (N node : adapter.getIndexer().getNodes(type)) {
                evaluate(adapter, type, node);
            }
        }
    }

    protected <N, T> void evaluate(GraphAdapter<N, T> typedAdapter, T type, N node) {
        int degree = typedAdapter.getIndexer().getDegree(node, type);
        data.put(type.toString(), degree);
    }

    @Override
    public List<Map<String, Object>> getTsvMaps(String[] header) {
        final List<Map<String, Object>> values = new ArrayList<>();
        for (String type : data.getValues().keySet()) {
            int index = 0;
            for (Integer i : data.getValues().get(type)) {
                Map<String, Object> value = new HashMap<>();
                value.put(header[0], "TypedDegree");
                value.put(header[1], type);
                value.put(header[2], index);
                value.put(header[3], i);
                values.add(value);
                index++;
            }
        }
        return values;
    }

}
