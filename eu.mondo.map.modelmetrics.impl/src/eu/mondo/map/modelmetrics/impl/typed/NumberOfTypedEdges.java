package eu.mondo.map.modelmetrics.impl.typed;

import eu.mondo.map.base.data.MapData;
import eu.mondo.map.modeladapters.ModelAdapter;
import eu.mondo.map.modeladapters.TypedModelAdapter;
import eu.mondo.map.modelmetrics.AbstractModelMetric;

public class NumberOfTypedEdges extends AbstractModelMetric<MapData<String, Integer>> {

	public NumberOfTypedEdges() {
		super("NumberOfTypedEdges", new MapData<>());
	}

	// public void calculate(final Network<?> network) {
	// clear();
	// for (String dimension : network.getNodesOnDimensions().keySet()) {
	// int sumOfEdges = 0;
	// for (Node<?> node : network.getNodesOnDimensions().get(dimension)) {
	// sumOfEdges += node.getNumberOfNeighbors(dimension);
	// }
	// sumOfEdges /= 2;
	// typedValues.put(dimension, sumOfEdges);
	// }
	// }

	@Override
	public <M, N, T> void evaluate(ModelAdapter<M, N, T> adapter) {
		TypedModelAdapter<M, N, T> typedAdapter = castAdapter(adapter);
		for (T type : typedAdapter.getTypes()) {
			int sumOfEdges = 0;
			for (N node : typedAdapter.getNodes(type)) {
				sumOfEdges += typedAdapter.getDegree(node, type);
			}
			sumOfEdges /= 2;
			data.put(type.toString(), sumOfEdges);
		}
	}

	@Override
	public <M, N, T> void evaluate(ModelAdapter<M, N, T> adapter, N element) {
		throw new UnsupportedOperationException("Cannot evaluate NumberOfTypedEdges metric on an element.");
	}

}
