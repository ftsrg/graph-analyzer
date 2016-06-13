package eu.mondo.map.modelmetrics.impl.typed;

import static eu.mondo.map.modelmetrics.impl.typed.TypedModelMetric.castAdapter;

import eu.mondo.map.core.metrics.typed.TypedScalarMetric;
import eu.mondo.map.modeladapters.ModelAdapter;
import eu.mondo.map.modeladapters.TypedModelAdapter;
import eu.mondo.map.modelmetrics.ModelEvaluator;

public class NumberOfTypedEdges extends TypedScalarMetric<String, Integer> implements ModelEvaluator {

	public NumberOfTypedEdges() {
		super("NumberOfTypedEdges");
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
	public <M> void evaluate(ModelAdapter<M> adapter) {
		TypedModelAdapter<M> typedAdapter = castAdapter(adapter);
		for (Object type : typedAdapter.getTypes()) {
			int sumOfEdges = 0;
			for (Object node : typedAdapter.getNodes(type)) {
				sumOfEdges += typedAdapter.getDegree(node, type);
			}
			sumOfEdges /= 2;
			typedValues.put(type.toString(), sumOfEdges);
		}
	}

	@Override
	public <M> void evaluate(ModelAdapter<M> adapter, Object element) {
		throw new UnsupportedOperationException("Cannot evaluate NumberOfTypedEdges metric on an element.");
	}

}
