package eu.mondo.map.modelmetrics.impl.simple;

import eu.mondo.map.base.data.ScalarData;
import eu.mondo.map.modeladapters.ModelAdapter;
import eu.mondo.map.modelmetrics.ModelEvaluator;

public class Density extends ScalarData<Double> implements ModelEvaluator {

	public Density() {
		super("Density");
	}

	@Override
	public void clear() {
		value = 0.0;

	}

	// public void calculate(final NumberOfNodes nodes, final NumberOfEdges
	// edges) {
	// value = edges.getValue() / (double) nodes.getValue();
	// value /= nodes.getValue() - 1;
	// }

	@Override
	public <M, N, T> void evaluate(ModelAdapter<M, N, T> adapter) {
		int numOfNodes = adapter.getNumberOfNodes();
		value = adapter.getNumberOfEdges() / (double) numOfNodes;
		value /= numOfNodes - 1;
	}

	@Override
	public <M, N, T> void evaluate(ModelAdapter<M, N, T> adapter, N element) {
		throw new UnsupportedOperationException("Cannot evaluate Density metric on an element.");
	}

}
