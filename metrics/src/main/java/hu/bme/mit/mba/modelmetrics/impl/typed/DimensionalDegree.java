package hu.bme.mit.mba.modelmetrics.impl.typed;

import hu.bme.mit.mba.base.data.MappedListData;
import hu.bme.mit.mba.modeladapters.ModelAdapter;
import hu.bme.mit.mba.modelmetrics.AbstractModelMetric;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DimensionalDegree extends AbstractModelMetric<MappedListData<String, Integer>> {

    public DimensionalDegree() {
        super("DimensionalDegreeList", new MappedListData<>());
    }

    @Override
    protected <N, T> void evaluateAll(final ModelAdapter<N, T> adapter) {
        for (T type : adapter.getTypes()) {
            for (N node : adapter.getNodes(type)) {
                evaluate(adapter, type, node);
            }
        }
    }

    protected <N, T, M> void evaluate(ModelAdapter<N, T> typedAdapter, T type, N node) {
        int degree = typedAdapter.getDegree(node, type);
        data.put(type.toString(), degree);
    }

    @Override
    public List<Map<String, Object>> getTsvMaps(String[] header) {
        final List<Map<String, Object>> values = new ArrayList<>();
        for (String type : data.getValues().keySet()) {
            int index = 0;
            for (Integer i : data.getValues().get(type)) {
                Map<String, Object> value = new HashMap<>();
                value.put(header[0], "DimensionalDegree");
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
