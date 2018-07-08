package hu.bme.mit.ga.metrics.impl.typed;

import hu.bme.mit.ga.base.data.MapData;
import hu.bme.mit.ga.adapters.GraphAdapter;
import hu.bme.mit.ga.metrics.AbstractGraphMetric;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypedActivity extends AbstractGraphMetric<MapData<String, Integer>> {

    public TypedActivity() {
        super("TypedActivity", new MapData<>());
    }

    @Override
    protected <N, T> void evaluateAll(GraphAdapter<N, T> adapter) {
        for (T type : adapter.getIndexer().getTypes()) {
            data.put(type.toString(), adapter.getIndexer().getNumberOfNodes(type));
        }
    }

    @Override
    public List<Map<String, Object>> getTsvMaps(String[] header) {
        final List<Map<String, Object>> values = new ArrayList<>();
        for (String type : data.getValues().keySet()) {
            Map<String, Object> value = new HashMap<>();
            value.put(header[0], "TypedActivity");
            value.put(header[1], type);
            value.put(header[2], null);
            value.put(header[3], data.getValues().get(type));
            values.add(value);
        }
        return values;
    }


}
