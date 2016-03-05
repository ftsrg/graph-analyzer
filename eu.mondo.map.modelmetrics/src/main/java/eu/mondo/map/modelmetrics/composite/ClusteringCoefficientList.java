package eu.mondo.map.modelmetrics.composite;

import eu.mondo.map.core.graph.Network;
import eu.mondo.map.core.graph.Node;
import eu.mondo.map.core.metrics.ListMetric;

public class ClusteringCoefficientList extends ListMetric<Double> {

	public ClusteringCoefficientList() {
		super("ClusteringCoefficientList");
	}

	public void calculate(final Network<?> network) {
		clear();
		for (Node<?> node : network.getAllNodes()) {
			calculate(node);
		}
	}

	public double calculate(final Node<?> node) {
		int interConnected = 0;
		int numberOfNeighbors = 0;
		for (Node<?> neighbor1 : node.getDisjunctNeighbors()) {
			for (Node<?> neighbor2 : node.getDisjunctNeighbors()) {
				if (neighbor1 != neighbor2) {
					if (neighbor1.hasNeighbor(neighbor2)) {
						interConnected++;
					}
				}
			}
		}

		numberOfNeighbors = node.getNumberOfDisjunctNeighbors();
		double clusteringCoef = 0.0;
		if (numberOfNeighbors < 2) {
			clusteringCoef = 0.0;
		} else {
			clusteringCoef = interConnected / (double) (numberOfNeighbors * (numberOfNeighbors - 1));

		}
		values.add(clusteringCoef);
		return clusteringCoef;
	}

}
