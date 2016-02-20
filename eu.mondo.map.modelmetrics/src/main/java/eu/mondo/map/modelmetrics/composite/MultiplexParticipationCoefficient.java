package eu.mondo.map.modelmetrics.composite;

import eu.mondo.map.core.graph.Network;
import eu.mondo.map.core.graph.Node;
import eu.mondo.map.core.metrics.ListMetric;

public class MultiplexParticipationCoefficient extends ListMetric<Double> {

	public MultiplexParticipationCoefficient() {
		super("MultiplexParticipationCoefficient");
	}

	public void calculate(final Network<?> network) {
		clear();
		for (Node<?> node : network.getAllNodes()) {
			calculate(network, node, false);
		}
	}

	public void calculateExclusive(final Network<?> network) {
		clear();
		for (Node<?> node : network.getAllNodes()) {
			calculate(network, node, true);
		}
	}

	public void calculate(final Network<?> network, final Node<?> node) {
		calculate(network, node, false);
	}

	public void calculateExclusive(final Network<?> network, final Node<?> node) {
		calculate(network, node, true);
	}

	protected void calculate(final Network<?> network, final Node<?> node, final boolean exclusive) {
		int numOfDimensions = 0;
		if (exclusive) {
			numOfDimensions = node.getNumberOfDimensions();
		} else {
			numOfDimensions = network.getNumberOfDimensions();
		}
		double coef = 0.0;
		for (String dimension : node.getDimensionsAsSet()) {
			coef += Math.pow(
					node.getNumberOfNeighbors(dimension)
							/ (double) node.getNumberOfNeighbors(), 2.0);
		}
		coef = 1 - coef;
		coef = (coef * numOfDimensions) / (numOfDimensions - 1);
		values.add(coef);
	}

}
