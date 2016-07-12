package eu.mondo.map.modelmetrics.impl.simple;

import eu.mondo.map.base.data.ScalarData;
import eu.mondo.map.modeladapters.ModelAdapter;
import eu.mondo.map.modelmetrics.AbstractModelMetric;
import eu.mondo.map.modelmetrics.incr.IncrementalModelEvaluator;

public class Density extends AbstractModelMetric<ScalarData<Double>> implements IncrementalModelEvaluator {

	public Density() {
		super("Density", new ScalarData<>());
	}

	@Override
	public <M, N, T> void evaluate(ModelAdapter<M, N, T> adapter) {
		int numOfNodes = adapter.getNumberOfNodes();
		Double value = adapter.getNumberOfEdges() / (double) numOfNodes;
		value /= numOfNodes - 1;

		data.setValue(value);
	}

	@Override
	public <M, N, T> void reevaluateNewEdge(ModelAdapter<M, N, T> adapter, T type, N sourceNode, N targetNode) {
		evaluate(adapter);
	}

}
