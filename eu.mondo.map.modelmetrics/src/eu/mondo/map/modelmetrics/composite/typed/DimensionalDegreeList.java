package eu.mondo.map.modelmetrics.composite.typed;

import eu.mondo.map.core.graph.Network;
import eu.mondo.map.core.graph.Node;
import eu.mondo.map.core.metrics.typed.TypedListMetric;

public class DimensionalDegreeList<N> extends TypedListMetric<String, Integer> {

	public DimensionalDegreeList() {
		super("DimensionalDegreeList");
	}

	public void calculate(final Network<N> network) {
		clear();
		for (String dimension : network.getDimensions()) {
			for (Node<N> node : network.getNodes()) {
				typedValues.put(dimension, node.getNumberOfNeighbors(dimension));
			}
		}
	}

}
