package eu.mondo.map.modelmetrics.scalar;

import eu.mondo.map.core.metrics.ScalarMetric;

public class Density extends ScalarMetric<Double> {

	@Override
	public void clear() {
		value = 0.0;

	}

	@Override
	public String getName() {
		return "Density";
	}

	public void calculate(final NumberOfNodes nodes, final NumberOfEdges edges) {
		value = edges.getValue() / (double) nodes.getValue();
		value /= (nodes.getValue() - 1);
	}

}
