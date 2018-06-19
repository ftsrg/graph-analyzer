package hu.bme.mit.mba.modelmetrics.impl.typed;

import hu.bme.mit.mba.base.data.MapData;
import hu.bme.mit.mba.base.data.MatrixData;
import hu.bme.mit.mba.modeladapters.ModelAdapter;
import hu.bme.mit.mba.modelmetrics.AbstractModelMetric;
import hu.bme.mit.mba.modelmetrics.incr.IncrementalModelEvaluator;
import org.supercsv.io.ICsvMapWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DimensionActivity extends AbstractModelMetric<MapData<String, Integer>>
    implements IncrementalModelEvaluator {

    public DimensionActivity() {
        super("DimensionActivity", new MapData<>());
    }

    @Override
    protected <N, T> void evaluateAll(ModelAdapter<N, T> adapter) {
        for (T type : adapter.getTypes()) {
            if (tracing != null) {
                for (N node : adapter.getNodes(type)) {
                    getTracing().put(node, type, 1);
                }
            }
            data.put(type.toString(), adapter.getNumberOfNodes(type));
        }
    }

    @Override
    public <N, T> void trace() {
        tracing = new MatrixData<N, T, Integer>();
    }

    @Override
    public <N, T> MatrixData<N, T, Integer> getTracing() {
        return (MatrixData<N, T, Integer>) tracing;
    }

    @Override
    public <N, T> void reevaluateNewEdge(ModelAdapter<N, T> adapter, T type, N sourceNode, N targetNode) {
        update(type, sourceNode, getTracing());
        update(type, targetNode, getTracing());
    }

    protected <N, T> void update(T type, N node, MatrixData<N, T, Integer> tracing) {
        if (!tracing.getValues().contains(node, type)) {
            tracing.put(node, type, 1);

            int value = 0;
            if (data.containsKey(type.toString())) {
                value = data.get(type.toString());
            }
            value++;
            data.put(type.toString(), value);

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
