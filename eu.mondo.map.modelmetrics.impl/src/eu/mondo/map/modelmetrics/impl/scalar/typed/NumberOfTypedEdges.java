package eu.mondo.map.modelmetrics.impl.scalar.typed;

import eu.mondo.map.core.graph.Network;
import eu.mondo.map.core.graph.Node;
import eu.mondo.map.core.metrics.typed.TypedScalarMetric;

public class NumberOfTypedEdges extends TypedScalarMetric<String, Integer> {

	public NumberOfTypedEdges() {
		super("NumberOfTypedEdges");
	}

	public void calculate(final Network<?> network) {
		clear();
		for (String dimension : network.getNodesOnDimensions().keySet()) {
			int sumOfEdges = 0;
			for (Node<?> node : network.getNodesOnDimensions().get(dimension)) {
				sumOfEdges += node.getNumberOfNeighbors(dimension);
			}
			sumOfEdges /= 2;
			typedValues.put(dimension, sumOfEdges);
		}
	}

}
