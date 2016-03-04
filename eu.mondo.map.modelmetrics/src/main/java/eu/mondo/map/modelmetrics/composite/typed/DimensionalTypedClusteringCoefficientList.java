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
			calculate(network, node);
		}
	}

	public void calculate(final Network<?> network, final Node<?> node) {
		int interConnected = 0;
		int numberOfNeighbors = 0;

		for (String dimension : node.getDimensionsAsSet()) {
			for (Node<?> neighbor1 : node.getDisjunctNeighbors(dimension)) {
				for (Node<?> neighbor2 : node.getDisjunctNeighbors(dimension)) {
					if (neighbor1 != neighbor2) {
						if (neighbor1.hasNeighbor(neighbor2, dimension)) {
							interConnected++;
						}
					}

				}

			}
			numberOfNeighbors = node.getNumberOfDisjunctNeighbors(dimension);
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
