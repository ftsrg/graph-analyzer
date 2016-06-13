package eu.mondo.map.modelmetrics.impl.typed;

import eu.mondo.map.core.graph.Network;
import eu.mondo.map.core.graph.Node;
import eu.mondo.map.core.metrics.ListMetric;

public class NodeActivityList<N> extends ListMetric<Integer> {

	public NodeActivityList() {
		super("NodeActivityList");
	}

	public void calculate(final Network<N> network) {
		clear();
		for (Node<N> node : network.getNodes()) {
			values.add(node.getDimensions().keySet().size());
		}
	}

}
