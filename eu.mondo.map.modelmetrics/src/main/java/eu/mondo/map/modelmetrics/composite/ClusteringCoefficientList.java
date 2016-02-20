package eu.mondo.map.modelmetrics.composite;

import eu.mondo.map.core.graph.Network;
import eu.mondo.map.core.graph.Node;
import eu.mondo.map.core.metrics.ListMetric;

public class ClusteringCoefficientList extends ListMetric<Double> {

	public ClusteringCoefficientList() {
		super("ClusteringCoefficientList");
	}

	public void calculate(final Network<?> network) {
		for (Node<?> node : network.getAllNodes()) {
			int interConnected = 0;
			for (Node<?> neighbor1 : node.getDisjunctNeighbors()) {
				for (Node<?> neighbor2 : node.getDisjunctNeighbors()) {
					if (neighbor1 != neighbor2) {
						if (neighbor1.hasNeighbor(neighbor2)) {
							interConnected++;
						}
					}
				}
				int numberOfNeighbors = node.getNumberOfDisjunctNeighbors();
				values.add(interConnected
						/ (double) (numberOfNeighbors * (numberOfNeighbors - 1)));
			}
		}
	}
}
