package eu.mondo.map.modelmetrics.impl.composite;

import eu.mondo.map.core.graph.Network;
import eu.mondo.map.core.graph.Node;
import eu.mondo.map.core.metrics.ListMetric;

public class NeighborhoodList<N> extends ListMetric<Integer> {

	public NeighborhoodList() {
		super("NeighborhoodList");
	}

	public void calculate(final Network<N> network) {
		for (Node<N> node : network.getNodes()) {
		
		}
	}

}
