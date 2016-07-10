package eu.mondo.map.modelmetrics.impl.simple;

import eu.mondo.map.base.data.ScalarData;
import eu.mondo.map.modeladapters.ModelAdapter;
import eu.mondo.map.modelmetrics.AbstractModelMetric;
import eu.mondo.map.modelmetrics.incr.IncrementalModelEvaluator;

public class NumberOfEdges extends AbstractModelMetric<ScalarData<Integer>> implements IncrementalModelEvaluator {

	public NumberOfEdges() {
		super("NumberOfEdges", new ScalarData<>());
	}

	@Override
	public void clear() {
		data.clear();
	}

	@Override
	public <M, N, T> void evaluate(ModelAdapter<M, N, T> adapter) {
		data.setValue(adapter.getNumberOfEdges());
	}

	@Override
	public <M, N, T> void evaluate(ModelAdapter<M, N, T> adapter, N element) {
		data.setValue(adapter.getDegree(element));
	}

	@Override
	public <M, N, T> void reevaluateNewEdge(ModelAdapter<M, N, T> adapter, T type, N sourceNode, N targetNode) {
		Integer value = data.getValue();
		value++;
		data.setValue(value);
	}

}
