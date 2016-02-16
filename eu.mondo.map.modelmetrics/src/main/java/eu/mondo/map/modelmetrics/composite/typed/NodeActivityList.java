package eu.mondo.map.modelmetrics.composite.typed;

import eu.mondo.map.core.graph.Network;
import eu.mondo.map.core.graph.Node;
import eu.mondo.map.core.metrics.ListMetric;

public class NodeActivityList extends ListMetric<Integer> {

	@Override
	public String getName() {
		return "NodeActivityList";
	}

	public void calculate(final Network<?> network) {
		clear();
		for (Node<?> node : network.getAllNodes()) {
			values.add(node.getDimensions().size());
		}
	}

}
