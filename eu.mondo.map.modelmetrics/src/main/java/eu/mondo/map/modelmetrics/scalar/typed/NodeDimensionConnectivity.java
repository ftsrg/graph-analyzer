package eu.mondo.map.modelmetrics.scalar.typed;

import java.util.Map;

import eu.mondo.map.core.metrics.typed.TypedScalarMetric;
import eu.mondo.map.modelmetrics.scalar.NumberOfNodes;

/**
 * Takes values in [0,1] and computes the ratio of nodes of the network that belong to a particular dimension.
 * 
 *
 */
public class NodeDimensionConnectivity extends TypedScalarMetric<String, Double> {

	@Override
	public String getName() {
		return "NodeDimensionConnectivity";
	}

	/**
	 * Calculates the Node Dimension Connectivity for a particular dimension.
	 * 
	 * @param dimension
	 *                String representing the dimension
	 * @param typedEdges
	 *                NumberOfTypedEdges object
	 * @param numberOfNodes
	 *                NumberOfNodes object
	 */
	public void calculate(final String dimension, final NumberOfTypedEdges typedEdges,
			final NumberOfNodes numberOfNodes) {
		if (!typedEdges.getValues().containsKey(dimension)) {
			throw new IllegalArgumentException("The dimension does not exist in the map:"
					+ dimension);
		}
		clear();
		putRatio(dimension, numberOfNodes.getValue(), typedEdges.getValues());
	}

	/**
	 * Calculates the Node Dimension Connectivity for every possible dimension that can be found in the
	 * given parameter.
	 * 
	 * @param typedEdges
	 *                NumberOfTypedEdges object
	 */
	public void calculate(final NumberOfTypedEdges typedEdges, final NumberOfNodes numberOfNodes) {
		clear();
		for (String key : typedEdges.getValues().keySet()) {
			putRatio(key, numberOfNodes.getValue(), typedEdges.getValues());
		}
	}

	protected void putRatio(final String dimension, final double allNodes,
			final Map<String, Integer> edges) {
		typedValues.put(dimension, (edges.get(dimension) * 2) / allNodes);
	}

}
