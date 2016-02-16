package eu.mondo.map.modelmetrics.scalar.typed;

import eu.mondo.map.core.graph.Network;
import eu.mondo.map.core.graph.Node;
import eu.mondo.map.core.metrics.typed.TypedScalarMetric;

public class NumberOfTypedEdges extends TypedScalarMetric<String, Integer> {

	@Override
	public String getName() {
		return "NumberOfTypedEdges";
	}

	public void calculate(final Network<?> network) {
		clear();
		for (String dimension : network.getNodesOnDimensions().keySet()) {
			int sumOfEdges = 0;
			for (Node<?> node : network.getNodesOnDimensions().get(dimension)) {
				sumOfEdges += node.numberOfNeighborsWithDimension(dimension);
			}
			sumOfEdges /= 2;
			typedValues.put(dimension, sumOfEdges);
		}

	}

}
