package eu.mondo.map.modelmetrics.impl.typed;

import eu.mondo.map.base.data.MapData;
import eu.mondo.map.base.data.MatrixData;
import eu.mondo.map.modeladapters.ModelAdapter;
import eu.mondo.map.modeladapters.TypedModelAdapter;
import eu.mondo.map.modelmetrics.AbstractModelMetric;
import eu.mondo.map.modelmetrics.incr.IncrementalModelEvaluator;

public class DimensionActivity extends AbstractModelMetric<MapData<String, Integer>>
		implements IncrementalModelEvaluator {

	public DimensionActivity() {
		super("DimensionActivity", new MapData<>());
	}

	@Override
	public <M, N, T> void evaluate(ModelAdapter<M, N, T> adapter) {
		TypedModelAdapter<M, N, T> typedAdapter = castAdapter(adapter);
		for (T type : typedAdapter.getTypes()) {
			if (tracing != null) {
				for (N node : typedAdapter.getNodes(type)) {
					getTracing().put(node, type, 1);
				}
			}
			data.put(type.toString(), typedAdapter.getNumberOfNodes(type));
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
	public <M, N, T> void reevaluateNewEdge(ModelAdapter<M, N, T> adapter, T type, N sourceNode, N targetNode) {
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
