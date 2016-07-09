package eu.mondo.map.modelmetrics.impl.typed;

import eu.mondo.map.base.data.ListData;
import eu.mondo.map.modeladapters.ModelAdapter;
import eu.mondo.map.modeladapters.TypedModelAdapter;
import eu.mondo.map.modelmetrics.AbstractModelMetric;

public class MultiplexParticipationCoefficient extends AbstractModelMetric<ListData<Double>> {

	public MultiplexParticipationCoefficient() {
		super("MultiplexParticipationCoefficient", new ListData<>());
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
		evaluateEveryNode(adapter);
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
			coef += Math.pow(typedAdapter.getDegree(element, type) / (double) typedAdapter.getDegree(element), 2.0);
		}
		coef = 1 - coef;
		coef = coef * numOfDimensions / (numOfDimensions - 1);
		data.add(coef);
	}

}
