package eu.mondo.map.modelmetrics.impl.typed;

import eu.mondo.map.core.metrics.typed.TypedScalarMetric;
import eu.mondo.map.modeladapters.ModelAdapter;
import eu.mondo.map.modeladapters.TypedModelAdapter;
import eu.mondo.map.modelmetrics.ModelEvaluator;

public class EdgeDimensionConnectivity extends TypedScalarMetric<String, Double> implements ModelEvaluator {

	public EdgeDimensionConnectivity() {
		super("EdgeDimensionConnectivity");
	}

	// public void calculate(final String dimension, final Network<?> network) {
	// int sumOfEdges = 0;
	// for (Node<?> node : network.getNodes(dimension)) {
	// sumOfEdges += node.getNumberOfNeighbors(dimension);
	// }
	// sumOfEdges /= 2;
	// typedValues.put(dimension, (double) sumOfEdges /
	// network.getNumberOfEdges());
	// }

	// public void calculate(final Network<?> network) {
	// clear();
	// for (String dimension : network.getDimensions()) {
	// calculate(dimension, network);
	// }
	// }

	// public void calculateExclusive(final String dimension, final Network<?>
	// network) {
	// throw new UnsupportedOperationException("calculateExclusive is not
	// implemented");
	// int sumOfEdges = 0;
	// Set<String> otherDimensions;
	// for (Node<?> node : network.getNodes(dimension)) {
	// for (Node<?> neighbor : node.getNeighbors(dimension)) {
	// otherDimensions = node.getDimensionsAsSet();
	// otherDimensions.remove(dimension);
	// for (String dim : otherDimensions) {
	// if (node.getNeighbors(dimension).contains(neighbor)) {
	// continue;
	// }
	// sumOfEdges++;
	// }
	// }
	// }
	// sumOfEdges /= 2;
	// typedValues.put(dimension, (double) sumOfEdges /
	// network.getNumberOfEdges());
	// }

	// public void calculateExclusive(final Network<?> network) {
	// clear();
	// for (String dimension : network.getDimensions()) {
	// calculateExclusive(dimension, network);
	// }
	// }

	@Override
	public <M> void evaluate(ModelAdapter<M> adapter) {
		TypedModelAdapter<M> typedAdapter = TypedModelMetric.castAdapter(adapter);
		for (Object type : typedAdapter.getTypes()) {
			evaluate(typedAdapter, type);
		}
	}

	protected <M> void evaluate(TypedModelAdapter<M> adapter, Object type) {
		int sumOfEdges = 0;
		for (Object node : adapter.getNodes(type)) {
			sumOfEdges += adapter.getDegree(node, type);
		}
		sumOfEdges /= 2;
		typedValues.put(type.toString(), (double) sumOfEdges / adapter.getNumberOfEdges());
	}

	@Override
	public <M> void evaluate(ModelAdapter<M> adapter, Object element) {
		throw new UnsupportedOperationException("Cannot evaluate EdgeDimensionConnectivity metric on an element.");
	}
}
