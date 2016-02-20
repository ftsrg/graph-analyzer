package eu.mondo.map.modelmetrics.composite;

import java.util.List;

import eu.mondo.map.core.graph.Network;
import eu.mondo.map.core.graph.Node;
import eu.mondo.map.core.metrics.ListMetric;

public class ClusteringCoefficientList extends ListMetric<Double> {

	public ClusteringCoefficientList() {
		super("ClusteringCoefficientList");
	}

	public void calculate(final Network<?> network) {
		for (Node<?> node : network.getAllNodes()) {
//			findConnections(node.getNeighbors());
		}
	}

	protected int findConnections(List<Node> nodes) {
		return 0;

	}
}
