package eu.mondo.map.modelmetrics.impl.typed;

import static eu.mondo.map.modelmetrics.impl.typed.TypedModelMetric.castAdapter;
import static eu.mondo.map.modelmetrics.impl.typed.TypedModelMetric.evaluateEveryNode;

import eu.mondo.map.core.metrics.typed.TypedListMetric;
import eu.mondo.map.modeladapters.ModelAdapter;
import eu.mondo.map.modeladapters.TypedModelAdapter;
import eu.mondo.map.modelmetrics.ModelEvaluator;

public class DimensionalTypedClusteringCoefficientList<N> extends TypedListMetric<String, Double>
		implements ModelEvaluator {

	public DimensionalTypedClusteringCoefficientList() {
		super("DimensionalTypedClusteringCoefficientList");
	}

	// public void calculate(final Network<N> network) {
	// clear();
	// for (Node<N> node : network.getNodes()) {
	// calculate(network, node, false, 0);
	// }
	// }
	//
	// public void calculate(final Network<N> network, final int
	// maxNumberOfNeighbors) {
	// clear();
	// for (Node<N> node : network.getNodes()) {
	// calculate(network, node, true, maxNumberOfNeighbors);
	// }
	// }
	//
	// public void calculate(final Network<N> network, final Node<N> node, final
	// int maxNumberOfNeighbors) {
	// calculate(network, node, true, maxNumberOfNeighbors);
	// }
	//
	// public void calculate(final Network<N> network, final Node<N> node) {
	// calculate(network, node, false, 0);
	// }
	//
	// protected void calculate(final Network<N> network, final Node<N> node,
	// final boolean bounded,
	// final int maxNumberOfNeighbors) {
	// long interConnected = 0;
	// long numberOfNeighbors = 0;
	// for (String dimension : node.getDimensionsAsSet()) {
	// interConnected = 0;
	// numberOfNeighbors = 0;
	// numberOfNeighbors = node.getNumberOfDisjunctNeighbors(dimension);
	// if (bounded && numberOfNeighbors > maxNumberOfNeighbors) {
	// typedValues.put(dimension, 0.0);
	// continue;
	// }
	// for (Node<N> neighbor1 : node.getNeighbors(dimension)) {
	// for (Node<N> neighbor2 : node.getNeighbors(dimension)) {
	// if (neighbor1 != neighbor2) {
	// if (network.isAdjacentUndirected(neighbor1, neighbor2, dimension)) {
	// interConnected++;
	// }
	// }
	//
	// }
	// }
	// double clusteringCoef = 0.0;
	// if (numberOfNeighbors < 2) {
	// clusteringCoef = 0.0;
	// } else {
	// clusteringCoef = interConnected / (double) (numberOfNeighbors *
	// (numberOfNeighbors - 1));
	// }
	// typedValues.put(dimension, clusteringCoef);
	// }
	// }

	@Override
	protected boolean isSkippable(Double value) {
		return value.equals(0.0);
	}

	@Override
	public <M> void evaluate(ModelAdapter<M> adapter) {
		evaluateEveryNode(adapter, this);
	}

	// TODO delete later
	boolean bounded = false;
	int maxNumberOfNeighbors = 100;

	@Override
	public <M> void evaluate(ModelAdapter<M> adapter, Object element) {
		TypedModelAdapter<M> typedAdapter = castAdapter(adapter);
		long interConnected = 0;
		long numberOfNeighbors = 0;
		for (Object type : typedAdapter.getTypes(element)) {
			interConnected = 0;
			numberOfNeighbors = 0;
			numberOfNeighbors = typedAdapter.getDegree(element, type);
			if (bounded && numberOfNeighbors > maxNumberOfNeighbors) {
				typedValues.put(type.toString(), 0.0);
				continue;
			}
			for (Object neighbor1 : typedAdapter.getNeighbors(element, type)) {
				for (Object neighbor2 : typedAdapter.getNeighbors(element, type)) {
					if (neighbor1 != neighbor2) {
						if (typedAdapter.isAdjacent(neighbor1, neighbor2, type)) {
							interConnected++;
						}
					}

				}
			}
			double clusteringCoef = 0.0;
			if (numberOfNeighbors < 2) {
				clusteringCoef = 0.0;
			} else {
				clusteringCoef = interConnected / (double) (numberOfNeighbors * (numberOfNeighbors - 1));
			}
			typedValues.put(type.toString(), clusteringCoef);
		}
	}

}
