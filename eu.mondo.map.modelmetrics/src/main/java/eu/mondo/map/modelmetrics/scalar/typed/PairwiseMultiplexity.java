package eu.mondo.map.modelmetrics.scalar.typed;

import eu.mondo.map.core.graph.Network;
import eu.mondo.map.core.graph.Node;
import eu.mondo.map.core.metrics.typed.TypedScalarMetric;

public class PairwiseMultiplexity extends TypedScalarMetric<String, Double> {

	public PairwiseMultiplexity() {
		super("PairwiseMultiplexity");
	}

	public void calculate(final Network<?> network, final String firstDimension,
			final String secondDimension) {
		typedValues.put(String.format("%s-%s", firstDimension, secondDimension),
				calculate(network, firstDimension, secondDimension, false));
	}

//	private String newName(final String firstDimension, final String secondDimension) {
//		name = String.format("%%s-%s", defaultName, firstDimension, secondDimension);
//		return name;
//	}

	public void calculateExclusive(final Network<?> network, final String firstDimension,
			final String secondDimension) {
		typedValues.put(String.format("%s-%s", firstDimension, secondDimension),
				calculate(network, firstDimension, secondDimension, true));
	}

	protected double calculate(final Network<?> network, final String firstDimension,
			final String secondDimension, final boolean exclusive) {
		int firstSizeofNodes = network.getNumberOfNodes(firstDimension);
		int secondSizeofNodes = network.getNumberOfNodes(secondDimension);
		int nodesInIntersection = 0;

		if (firstSizeofNodes < secondSizeofNodes) {
			for (Node<?> node : network.getNodes(firstDimension)) {
				if (node.hasDimension(secondDimension)) {
					nodesInIntersection++;
				}
			}
		} else {
			for (Node<?> node : network.getNodes(secondDimension)) {
				if (node.hasDimension(firstDimension)) {
					nodesInIntersection++;
				}
			}
		}
		if (exclusive) {
			int union = firstSizeofNodes + secondSizeofNodes;
			union -= nodesInIntersection;
			if (union == 0) {
				return 0.0;
			} else {
				return nodesInIntersection / (double) union;
			}
		}
		return nodesInIntersection / (double) network.getNumberOfNodes();
	}
}
