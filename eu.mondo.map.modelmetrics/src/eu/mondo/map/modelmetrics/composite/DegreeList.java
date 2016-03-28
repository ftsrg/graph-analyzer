package eu.mondo.map.modelmetrics.composite;

import eu.mondo.map.core.graph.Network;
import eu.mondo.map.core.graph.Node;
import eu.mondo.map.core.metrics.ListMetric;

public class DegreeList extends ListMetric<Integer> {

	public DegreeList() {
		super("DegreeList");
	}

	public <N> void calculate(final Network<N> network) {
		for (Node<N> node : network.getNodes()) {
			values.add(node.getDegree());
		}
	}

}