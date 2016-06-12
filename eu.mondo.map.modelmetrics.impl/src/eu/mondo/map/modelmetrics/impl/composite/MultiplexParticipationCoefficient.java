package eu.mondo.map.modelmetrics.impl.composite;

import eu.mondo.map.core.graph.Network;
import eu.mondo.map.core.graph.Node;
import eu.mondo.map.core.metrics.ListMetric;

public class MultiplexParticipationCoefficient extends ListMetric<Double> {

	public MultiplexParticipationCoefficient() {
		super("MultiplexParticipationCoefficient");
	}

	public <N> void calculate(final Network<N> network) {
		clear();
		for (Node<N> node : network.getNodes()) {
			calculate(network, node, false);
		}
	}

	public <N> void calculateExclusive(final Network<N> network) {
		clear();
		for (Node<N> node : network.getNodes()) {
			calculate(network, node, true);
		}
	}

	public <N> void calculate(final Network<N> network, final Node<N> node) {
		calculate(network, node, false);
	}

	public <N> void calculateExclusive(final Network<N> network, final Node<N> node) {
		calculate(network, node, true);
	}

	protected <N> void calculate(final Network<N> network, final Node<N> node, final boolean exclusive) {
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
