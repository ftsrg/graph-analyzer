package eu.mondo.map.modelmetrics.composite.typed;

import eu.mondo.map.core.graph.Network;
import eu.mondo.map.core.graph.Node;
import eu.mondo.map.core.metrics.ListMetric;

public class NodeActivityList extends ListMetric<Integer> {

	public NodeActivityList() {
		super("NodeActivityList");
	}

	public void calculate(final Network<?> network) {
		clear();
		for (Node<?> node : network.getNodes()) {
			values.add(node.getDimensions().size());
		}
	}

}
