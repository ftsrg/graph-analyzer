package eu.mondo.map.modelmetrics.composite;

import eu.mondo.map.core.graph.Network;
import eu.mondo.map.core.graph.Node;
import eu.mondo.map.core.metrics.ListMetric;

public class DimensionalClusteringCoefficient extends ListMetric<Double> {

	public DimensionalClusteringCoefficient() {
		super("DimensionalClusteringCoefficient");
	}

	public <N> void calculateFirstDefinition(final Network<N> network) {
		clear();
		for (Node<N> node : network.getNodes()) {
			calculateFirstDefinition(network, node);
		}
	}

	public <N> void calculateSecondDefinition(final Network<N> network) {
		clear();
		for (Node<N> node : network.getNodes()) {
			calculateSecondDefinition(network, node);
		}
	}

	public <N> double calculateFirstDefinition(final Network<N> network, final Node<N> node) {
		long interConnected = 0;
		long numberOfNeighbors = 0;
		long numberOfPossibleConnections = 0;
		double coef = 0.0;

		for (String dimension1 : node.getDimensionsAsSet()) {
			for (Node<N> neighbor1 : node.getNeighbors(dimension1)) {
				for (Node<N> neighbor2 : node.getNeighbors(dimension1)) {
					if (neighbor1 != neighbor2) {
						for (String dimension2 : neighbor1.getDimensionsAsSet()) {
							if (!dimension1.equals(dimension2)) {
								if (neighbor1.hasNeighbor(neighbor2, dimension2)) {
									interConnected++;
								}
							}

						}
					}
				}
			}
			numberOfNeighbors = node.getNumberOfDisjunctNeighbors(dimension1);
			if (numberOfNeighbors > 1) {
				numberOfPossibleConnections += (numberOfNeighbors) * (numberOfNeighbors - 1);
			}
		}
		numberOfPossibleConnections *= (network.getNumberOfDimensions() - 1);
		if (numberOfPossibleConnections == 0) {
			coef = 0.0;
		} else {
			coef = interConnected / (double) numberOfPossibleConnections;
		}
		values.add(coef);
		return coef;
	}

	public <N> double calculateSecondDefinition(final Network<N> network, final Node<N> node) {
		// int numberOfNeighbors = 0;
		long interConnected = 0;
		long numberOfPossibleConnections = 0;
		double coef = 0.0;

		for (String dimension1 : node.getDimensionsAsSet()) {
			for (String dimension2 : node.getDimensionsAsSet()) {
				if (!dimension1.equals(dimension2)) {
					for (Node<N> neighbor1 : node.getNeighbors(dimension1)) {
						for (Node<N> neighbor2 : node.getNeighbors(dimension2)) {
							if (neighbor1 != neighbor2) {
								numberOfPossibleConnections++;
								for (String dimension3 : neighbor1
										.getDimensionsAsSet()) {
									if (!dimension1.equals(dimension3)
											&& !dimension2.equals(
													dimension3)) {
										if (neighbor1.hasNeighbor(neighbor2,
												dimension3)) {
											interConnected++;
										}
									}
								}
							}
						}
					}
				}
			}
		}

		numberOfPossibleConnections *= (network.getNumberOfDimensions() - 2);
		if (numberOfPossibleConnections == 0) {
			coef = 0.0;
		} else {
			coef = interConnected / (double) numberOfPossibleConnections;
		}
		values.add(coef);

		return coef;
	}

}
