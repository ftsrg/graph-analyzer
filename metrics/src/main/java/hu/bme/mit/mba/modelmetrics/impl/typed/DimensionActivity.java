package hu.bme.mit.mba.modelmetrics.impl.typed;

import hu.bme.mit.mba.base.data.MapData;
import hu.bme.mit.mba.modeladapters.ModelAdapter;
import hu.bme.mit.mba.modelmetrics.AbstractModelMetric;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DimensionActivity extends AbstractModelMetric<MapData<String, Integer>> {

    public DimensionActivity() {
        super("DimensionActivity", new MapData<>());
    }

    @Override
    protected <N, T> void evaluateAll(ModelAdapter<N, T> adapter) {
        for (T type : adapter.getIndexer().getDimensions()) {
            data.put(type.toString(), adapter.getIndexer().getNumberOfNodes(type));
        }
    }

    @Override
    public List<Map<String, Object>> getTsvMaps(String[] header) {
        final List<Map<String, Object>> values = new ArrayList<>();
        for (String type : data.getValues().keySet()) {
            Map<String, Object> value = new HashMap<>();
            value.put(header[0], "DimensionActivity");
            value.put(header[1], type);
            value.put(header[2], null);
            value.put(header[3], data.getValues().get(type));
            values.add(value);
        }
        return values;
    }


}
