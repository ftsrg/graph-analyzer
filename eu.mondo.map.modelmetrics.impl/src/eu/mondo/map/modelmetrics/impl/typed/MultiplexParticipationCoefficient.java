package eu.mondo.map.modelmetrics.impl.typed;

import static eu.mondo.map.modelmetrics.impl.typed.TypedModelMetric.castAdapter;
import static eu.mondo.map.modelmetrics.impl.typed.TypedModelMetric.evaluateEveryNode;

import eu.mondo.map.core.metrics.ListMetric;
import eu.mondo.map.modeladapters.ModelAdapter;
import eu.mondo.map.modeladapters.TypedModelAdapter;
import eu.mondo.map.modelmetrics.ModelEvaluator;

public class MultiplexParticipationCoefficient extends ListMetric<Double> implements ModelEvaluator {

	public MultiplexParticipationCoefficient() {
		super("MultiplexParticipationCoefficient");
	}

	// public <N> void calculate(final Network<N> network) {
	// clear();
	// for (Node<N> node : network.getNodes()) {
	// calculate(network, node, false);
	// }
	// }
	//
	// public <N> void calculateExclusive(final Network<N> network) {
	// clear();
	// for (Node<N> node : network.getNodes()) {
	// calculate(network, node, true);
	// }
	// }
	//
	// public <N> void calculate(final Network<N> network, final Node<N> node) {
	// calculate(network, node, false);
	// }
	//
	// public <N> void calculateExclusive(final Network<N> network, final
	// Node<N> node) {
	// calculate(network, node, true);
	// }
	//
	// protected <N> void calculate(final Network<N> network, final Node<N>
	// node, final boolean exclusive) {
	// int numOfDimensions = 0;
	// if (exclusive) {
	// numOfDimensions = node.getNumberOfDimensions();
	// } else {
	// numOfDimensions = network.getNumberOfDimensions();
	// }
	// double coef = 0.0;
	// for (String dimension : node.getDimensionsAsSet()) {
	// coef += Math.pow(node.getNumberOfNeighbors(dimension) / (double)
	// node.getNumberOfNeighbors(), 2.0);
	// }
	// coef = 1 - coef;
	// coef = coef * numOfDimensions / (numOfDimensions - 1);
	// values.add(coef);
	// }

	@Override
	public <M, N, T> void evaluate(ModelAdapter<M, N, T> adapter) {
		evaluateEveryNode(adapter, this);
	}

	// TODO delete later
	boolean exclusive = false;

	@Override
	public <M, N, T> void evaluate(ModelAdapter<M, N, T> adapter, N element) {
		TypedModelAdapter<M, N, T> typedAdapter = castAdapter(adapter);

		int numOfDimensions = 0;
		if (exclusive) {
			numOfDimensions = typedAdapter.getNumberOfTypes(element);
		} else {
			numOfDimensions = typedAdapter.getNumberOfTypes();
		}
		double coef = 0.0;
		for (T type : typedAdapter.getTypes(element)) {
			coef += Math.pow(typedAdapter.getDegree(element, type) / (double) typedAdapter.getDegree(element),
					2.0);
		}
		coef = 1 - coef;
		coef = coef * numOfDimensions / (numOfDimensions - 1);
		values.add(coef);
	}

}
