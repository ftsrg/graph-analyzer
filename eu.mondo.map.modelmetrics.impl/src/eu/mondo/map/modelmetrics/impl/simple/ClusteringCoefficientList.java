package eu.mondo.map.modelmetrics.impl.simple;

import static eu.mondo.map.modelmetrics.impl.typed.TypedModelMetric.evaluateEveryNode;

import eu.mondo.map.base.metrics.ListMetric;
import eu.mondo.map.modeladapters.ModelAdapter;
import eu.mondo.map.modelmetrics.ModelEvaluator;

public class ClusteringCoefficientList extends ListMetric<Double> implements ModelEvaluator {

	protected int maxNeighbours = 1000;
	protected boolean useHeuristic = false;

	public ClusteringCoefficientList() {
		super("ClusteringCoefficientList");
	}

	public int getMaxNeighbours() {
		return maxNeighbours;
	}

	public void setMaxNeighbours(int maxNeighbours) {
		this.maxNeighbours = maxNeighbours;
	}

	public boolean isUseHeuristic() {
		return useHeuristic;
	}

	public void setUseHeuristic(boolean useHeuristic) {
		this.useHeuristic = useHeuristic;
	}

	// public void calculate(final Network<N> network) {
	// clear();
	// int i = 0;
	// for (Node<N> node : network.getNodes()) {
	// calculate(node);
	// i++;
	// if (i % 100000 == 0) {
	// System.out.println(i);
	// }
	// }
	// }
	//
	// public double calculate(final Node<N> node) {
	// long interConnected = 0;
	// long numberOfNeighbors = 0;
	// double clusteringCoef = 0.0;
	// if (useHeuristic && node.getNumberOfDisjunctNeighbors() > maxNeighbours)
	// {
	// values.add(clusteringCoef);
	// return clusteringCoef;
	// }
	// for (Node<N> neighbor1 : node.getDisjunctNeighbors()) {
	// for (Node<N> neighbor2 : node.getDisjunctNeighbors()) {
	// if (neighbor1 != neighbor2) {
	// if (neighbor1.hasNeighbor(neighbor2)) {
	// interConnected++;
	// }
	// }
	// }
	// }
	//
	// numberOfNeighbors = node.getNumberOfDisjunctNeighbors();
	// if (numberOfNeighbors < 2) {
	// clusteringCoef = 0.0;
	// } else {
	// clusteringCoef = interConnected / (double) (numberOfNeighbors *
	// (numberOfNeighbors - 1));
	//
	// }
	// values.add(clusteringCoef);
	// return clusteringCoef;
	// }

	@Override
	public <M, N, T> void evaluate(ModelAdapter<M, N, T> adapter) {
		evaluateEveryNode(adapter, this);
	}

	@Override
	public <M, N, T> void evaluate(ModelAdapter<M, N, T> adapter, N element) {
		long interConnected = 0;
		long numberOfNeighbors = 0;
		double clusteringCoef = 0.0;
		if (useHeuristic && adapter.getDegree(element) > maxNeighbours) {
			values.add(clusteringCoef);
			// return clusteringCoef;
		}
		for (N neighbor1 : adapter.getNeighbors(element)) {
			for (N neighbor2 : adapter.getNeighbors(element)) {
				if (neighbor1 != neighbor2) {
					if (adapter.isAdjacent(neighbor1, neighbor2)) {
						interConnected++;
					}
				}
			}
		}

		numberOfNeighbors = adapter.getDegree(element);
		if (numberOfNeighbors < 2) {
			clusteringCoef = 0.0;
		} else {
			clusteringCoef = interConnected / (double) (numberOfNeighbors * (numberOfNeighbors - 1));

		}
		values.add(clusteringCoef);
		// return clusteringCoef;
	}

}
