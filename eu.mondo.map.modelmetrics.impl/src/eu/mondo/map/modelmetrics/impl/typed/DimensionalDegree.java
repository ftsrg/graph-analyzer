package eu.mondo.map.modelmetrics.impl.typed;

import eu.mondo.map.base.data.MappedListData;
import eu.mondo.map.base.data.MatrixData;
import eu.mondo.map.modeladapters.ModelAdapter;
import eu.mondo.map.modeladapters.TypedModelAdapter;
import eu.mondo.map.modelmetrics.AbstractModelMetric;
import eu.mondo.map.modelmetrics.incr.IncrementalModelEvaluator;

public class DimensionalDegree extends AbstractModelMetric<MappedListData<String, Integer>>
	implements IncrementalModelEvaluator {

    public DimensionalDegree() {
	super("DimensionalDegreeList", new MappedListData<>());
    }

    @Override
    protected <N, T> void evaluateAll(final ModelAdapter<N, T> adapter) {
	TypedModelAdapter<N, T> typedAdapter = castAdapter(adapter);
	for (T type : typedAdapter.getTypes()) {
	    for (N node : typedAdapter.getNodes(type)) {
		evaluate(typedAdapter, type, node);
	    }
	}
    }

    protected <N, T, M> void evaluate(TypedModelAdapter<N, T> typedAdapter, T type, N node) {
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
	reevaluate(castAdapter(adapter), getTracing(), sourceNode, type);
	reevaluate(castAdapter(adapter), getTracing(), targetNode, type);
    }

    protected <N, T> void reevaluate(TypedModelAdapter<N, T> adapter, MatrixData<N, T, Integer> castedTracing, N node,
	    T type) {
	if (castedTracing.getValues().contains(node, type)) {
	    Integer value = castedTracing.getValues().get(node, type);
	    value++;
	    castedTracing.put(node, type, value);
	} else {
	    evaluate(adapter, type, node);
	}
    }

}
