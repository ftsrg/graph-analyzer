package eu.mondo.map.modelmetrics.impl.typed;

import static eu.mondo.map.modelmetrics.impl.typed.TypedModelMetric.castAdapter;

import eu.mondo.map.base.metrics.typed.TypedScalarMetric;
import eu.mondo.map.modeladapters.ModelAdapter;
import eu.mondo.map.modeladapters.TypedModelAdapter;
import eu.mondo.map.modelmetrics.ModelEvaluator;

/**
 * Takes values in [0,1] and computes the ratio of nodes of the network that
 * belong to a particular dimension.
 *
 */
public class NodeDimensionConnectivity extends TypedScalarMetric<String, Double> implements ModelEvaluator {

	public NodeDimensionConnectivity() {
		super("NodeDimensionConnectivity");
	}

	// /**
	// * Calculates the Node Dimension Connectivity for a particular dimension.
	// *
	// * @param dimension
	// * String representing the dimension
	// * @param typedEdges
	// * NumberOfTypedEdges object
	// * @param numberOfNodes
	// * NumberOfNodes object
	// */
	// public void calculate(final String dimension, final NumberOfTypedEdges
	// typedEdges,
	// final NumberOfNodes numberOfNodes) {
	// if (!typedEdges.getValues().containsKey(dimension)) {
	// throw new IllegalArgumentException("The dimension does not exist in the
	// map:" + dimension);
	// }
	// putRatio(dimension, numberOfNodes.getValue(), typedEdges.getValues());
	// }

	// /**
	// * Calculates the Node Dimension Connectivity for every possible dimension
	// * that can be found in the given parameter.
	// *
	// * @param typedEdges
	// * NumberOfTypedEdges object
	// */
	// public void calculate(final NumberOfTypedEdges typedEdges, final
	// NumberOfNodes numberOfNodes) {
	// clear();
	// for (String key : typedEdges.getValues().keySet()) {
	// putRatio(key, numberOfNodes.getValue(), typedEdges.getValues());
	// }
	// }

	// public void calculate(final String dimension, final Network<?> network) {
	// typedValues.put(dimension, (double) network.getNumberOfNodes(dimension) /
	// network.getNumberOfNodes());
	// }

	// public void calculateExclusive(final String dimension, final Network<?>
	// network) {
	// if (!network.getNodesOnDimensions().containsKey(dimension)) {
	// throw new IllegalArgumentException("Dimension does not exist: " +
	// dimension);
	// }
	// int numOfNodes = 0;
	// for (Node<?> node : network.getNodesOnDimensions().get(dimension)) {
	// if (node.getDimensions().keySet().size() == 1) {
	// numOfNodes++;
	// }
	// }
	// typedValues.put(dimension, (double) numOfNodes /
	// network.getNumberOfNodes());
	// }
	//
	// public void calculate(final Network<?> network) {
	// clear();
	// for (String dimension : network.getDimensions()) {
	// calculate(dimension, network);
	// }
	// }

	// public void calculateExclusive(final Network<?> network) {
	// clear();
	// for (String dimension : network.getDimensions()) {
	// calculateExclusive(dimension, network);
	// }
	// }
	//
	// protected void putRatio(final String dimension, final double allNodes,
	// final Map<String, Integer> edges) {
	// typedValues.put(dimension, edges.get(dimension) / allNodes);
	// }

	@Override
	public <M, N, T> void evaluate(ModelAdapter<M, N, T> adapter) {
		TypedModelAdapter<M, N, T> typedAdapter = castAdapter(adapter);
		for (T type : typedAdapter.getTypes()) {
			evaluate(typedAdapter, type);
		}

	}

	@Override
	public <M, N, T> void evaluate(ModelAdapter<M, N, T> adapter, N element) {
		throw new UnsupportedOperationException("Cannot evaluate NodeDimensionConnectivity metric on an element.");
	}

	protected <M, N, T> void evaluate(final TypedModelAdapter<M, N, T> adapter, final T type) {
		typedValues.put(type.toString(), (double) adapter.getNumberOfNodes(type) / adapter.getNumberOfNodes());
	}
}
