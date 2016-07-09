package eu.mondo.map.modelmetrics.impl.simple;

import eu.mondo.map.base.data.ScalarData;
import eu.mondo.map.modeladapters.ModelAdapter;
import eu.mondo.map.modelmetrics.ModelMetric;

public class Density extends ModelMetric<ScalarData<Double>> {

	public Density() {
		super("Density", new ScalarData<>());
	}

	@Override
	public void clear() {
		data.setValue(0.0);
	}

	// public void calculate(final NumberOfNodes nodes, final NumberOfEdges
	// edges) {
	// value = edges.getValue() / (double) nodes.getValue();
	// value /= nodes.getValue() - 1;
	// }

	@Override
	public <M, N, T> void evaluate(ModelAdapter<M, N, T> adapter) {
		int numOfNodes = adapter.getNumberOfNodes();
		Double value = adapter.getNumberOfEdges() / (double) numOfNodes;
		value /= numOfNodes - 1;

		data.setValue(value);
	}

	@Override
	public <M, N, T> void evaluate(ModelAdapter<M, N, T> adapter, N element) {
		throw new UnsupportedOperationException("Cannot evaluate Density metric on an element.");
	}

}
