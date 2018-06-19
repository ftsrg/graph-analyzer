package hu.bme.mit.mba.modelmetrics.impl.typed;

import hu.bme.mit.mba.base.data.MappedListData;
import hu.bme.mit.mba.base.data.MatrixData;
import hu.bme.mit.mba.modeladapters.ModelAdapter;
import hu.bme.mit.mba.modelmetrics.AbstractModelMetric;
import hu.bme.mit.mba.modelmetrics.incr.IncrementalModelEvaluator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DimensionalDegree extends AbstractModelMetric<MappedListData<String, Integer>>
    implements IncrementalModelEvaluator {

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
        updateTracing(node, type, degree);
    }

    protected <N, T> void updateTracing(N node, T type, int degree) {
        if (tracing != null) {
            getTracing().put(node, type, degree);
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
        reevaluate(adapter, getTracing(), sourceNode, type);
        reevaluate(adapter, getTracing(), targetNode, type);
    }

    protected <N, T> void reevaluate(ModelAdapter<N, T> adapter, MatrixData<N, T, Integer> castedTracing, N node,
                                     T type) {
        if (castedTracing.getValues().contains(node, type)) {
            Integer value = castedTracing.getValues().get(node, type);
            value++;
            castedTracing.put(node, type, value);
        } else {
            evaluate(adapter, type, node);
        }
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
