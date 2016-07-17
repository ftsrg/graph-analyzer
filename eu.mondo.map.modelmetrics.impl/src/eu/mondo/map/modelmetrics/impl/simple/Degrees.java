package eu.mondo.map.modelmetrics.impl.simple;

import eu.mondo.map.base.data.ListData;
import eu.mondo.map.base.data.MapData;
import eu.mondo.map.modeladapters.ModelAdapter;
import eu.mondo.map.modelmetrics.AbstractModelMetric;
import eu.mondo.map.modelmetrics.incr.IncrementalModelEvaluator;

public class Degrees extends AbstractModelMetric<ListData<Integer>> implements IncrementalModelEvaluator {

	public Degrees() {
		super("DegreeList", new ListData<>());
	}

	@Override
	public <M, N, T> void evaluate(ModelAdapter<M, N, T> adapter) {
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
	public <M, N, T> void evaluate(ModelAdapter<M, N, T> adapter, N element) {
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
	public <M, N, T> void reevaluateNewEdge(ModelAdapter<M, N, T> adapter, T type, N sourceNode, N targetNode) {
		reevaluateNode(adapter, getTracing(), sourceNode);
		reevaluateNode(adapter, getTracing(), targetNode);
	}

	protected <M, N, T> void reevaluateNode(ModelAdapter<M, N, T> adapter, MapData<N, Integer> castedTracing, N node) {
		if (castedTracing.containsKey(node)) {
			Integer value = castedTracing.get(node);
			value++;
			castedTracing.put(node, value);
		} else {
			evaluate(adapter, node);
		}
	}

}
