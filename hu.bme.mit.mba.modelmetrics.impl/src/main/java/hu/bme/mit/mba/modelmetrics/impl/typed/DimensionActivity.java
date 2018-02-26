package hu.bme.mit.mba.modelmetrics.impl.typed;

import hu.bme.mit.mba.base.data.MapData;
import hu.bme.mit.mba.base.data.MatrixData;
import hu.bme.mit.mba.modeladapters.ModelAdapter;
import hu.bme.mit.mba.modelmetrics.AbstractModelMetric;
import hu.bme.mit.mba.modelmetrics.incr.IncrementalModelEvaluator;

public class DimensionActivity extends AbstractModelMetric<MapData<String, Integer>>
        implements IncrementalModelEvaluator {

    public DimensionActivity() {
        super("DimensionActivity", new MapData<>());
    }

    @Override
    protected <N, T> void evaluateAll(ModelAdapter adapter) {
        // ModelAdapter typedAdapter = castAdapter(adapter);
        for (T type : adapter.<N, T>getTypes()) {
            if (tracing != null) {
                for (N node : adapter.<N, T>getNodes(type)) {
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
    public <N, T> void reevaluateNewEdge(ModelAdapter adapter, T type, N sourceNode, N targetNode) {
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

}
