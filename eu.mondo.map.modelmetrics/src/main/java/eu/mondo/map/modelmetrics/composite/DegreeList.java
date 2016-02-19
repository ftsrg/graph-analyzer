package eu.mondo.map.modelmetrics.composite;

import eu.mondo.map.core.graph.Network;
import eu.mondo.map.core.graph.Node;
import eu.mondo.map.core.metrics.ListMetric;

public class DegreeList extends ListMetric<Integer> {

	@Override
	public String getName() {
		return "DegreeList";
	}

	public void calculate(final Network<?> network) {
		for (Node<?> node : network.getAllNodes()) {
			values.add(node.getDegree());
		}
	}

}
