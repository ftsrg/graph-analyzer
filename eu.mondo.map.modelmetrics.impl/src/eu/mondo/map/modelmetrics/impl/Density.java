package eu.mondo.map.modelmetrics.impl;

import eu.mondo.map.core.metrics.ScalarMetric;

public class Density extends ScalarMetric<Double> {

	public Density() {
		super("Density");
	}

	@Override
	public void clear() {
		value = 0.0;

	}

	public void calculate(final NumberOfNodes nodes, final NumberOfEdges edges) {
		value = edges.getValue() / (double) nodes.getValue();
		value /= nodes.getValue() - 1;
	}

}
