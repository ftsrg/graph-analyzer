package eu.mondo.map.modelmetrics.composite.typed;

import eu.mondo.map.core.graph.Network;
import eu.mondo.map.core.graph.Node;
import eu.mondo.map.core.metrics.typed.TypedListMetric;

public class DimensionalTypedClusteringCoefficientList extends TypedListMetric<String, Double> {

	public DimensionalTypedClusteringCoefficientList() {
		super("DimensionalTypedClusteringCoefficientList");
	}

	public void calculate(final Network<?> network) {
		clear();
		for (Node<?> node : network.getAllNodes()) {
			calculate(network, node, false, 0);
		}
	}

	public void calculate(final Network<?> network, final int maxNumberOfNeighbors) {
		clear();
		for (Node<?> node : network.getAllNodes()) {
			calculate(network, node, true, maxNumberOfNeighbors);
		}
	}

	public void calculate(final Network<?> network, final Node<?> node, final int maxNumberOfNeighbors) {
		calculate(network, node, true, maxNumberOfNeighbors);
	}

	public void calculate(final Network<?> network, final Node<?> node) {
		calculate(network, node, false, 0);
	}

	protected void calculate(final Network<?> network, final Node<?> node, final boolean bounded,
			final int maxNumberOfNeighbors) {
		int interConnected = 0;
		int numberOfNeighbors = 0;
		for (String dimension : node.getDimensionsAsSet()) {
			interConnected = 0;
			numberOfNeighbors = 0;
			numberOfNeighbors = node.getNumberOfDisjunctNeighbors(dimension);
			if (bounded && numberOfNeighbors > maxNumberOfNeighbors) {
				typedValues.put(dimension, 0.0);
				continue;
			}
			for (Node<?> neighbor1 : node.getNeighbors(dimension)) {
				for (Node<?> neighbor2 : node.getNeighbors(dimension)) {
					if (neighbor1 != neighbor2) {
						if (network.isAdjacentUndirected(neighbor1, neighbor2, dimension)) {
							interConnected++;
						}
					}

				}
			}
			double clusteringCoef = 0.0;
			if (numberOfNeighbors < 2) {
				clusteringCoef = 0.0;
			} else {
				clusteringCoef = interConnected
						/ (double) (numberOfNeighbors * (numberOfNeighbors - 1));
			}
			typedValues.put(dimension, clusteringCoef);
		}
	}

}
