package eu.mondo.map.modelmetrics.impl.simple;

import eu.mondo.map.base.data.ListData;
import eu.mondo.map.base.data.MapData;
import eu.mondo.map.modeladapters.ModelAdapter;
import eu.mondo.map.modelmetrics.AbstractModelMetric;
import eu.mondo.map.modelmetrics.incr.IncrementalModelEvaluator;

public class ClusteringCoefficient extends AbstractModelMetric<ListData<Double>> implements IncrementalModelEvaluator {

	@Deprecated
	protected int maxNeighbours = 1000;
	@Deprecated
	protected boolean useHeuristic = false;

	public ClusteringCoefficient() {
		super("ClusteringCoefficientList", new ListData<>());
	}

	public int getMaxNeighbours() {
		return maxNeighbours;
	}

	@Override
	public <N, T> void trace() {
		tracing = new MapData<N, Double>();
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

	@Override
	public <M, N, T> void evaluate(ModelAdapter<M, N, T> adapter) {
		evaluateEveryNode(adapter);
	}

	@Override
	public <M, N, T> void evaluate(ModelAdapter<M, N, T> adapter, N element) {
		long interConnected = 0;
		long numberOfNeighbors = 0;
		double clusteringCoef = 0.0;
		if (useHeuristic && adapter.getDegree(element) > maxNeighbours) {
			data.add(clusteringCoef);
			if (tracing != null) {
				((MapData<N, Double>) tracing).put(element, clusteringCoef);
			}
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
		data.add(clusteringCoef);
		if (tracing != null) {
			((MapData<N, Double>) tracing).put(element, clusteringCoef);
		}
		// return clusteringCoef;
	}

	@Override
	public <N> void reevaluateAddedNode(N node) {
		// TODO Auto-generated method stub

	}

	@Override
	public <N> void reevaluateDeletedNode(N node) {
		// TODO Auto-generated method stub

	}

	@Override
	public <N, T> void reevaluateAddedEdge(T type, N sourceNode, N targetNode) {
		// TODO Auto-generated method stub

	}

	@Override
	public <N, T> void reevaluateDeletedEdge(T type, N sourceNode, N targetNode) {
		// TODO Auto-generated method stub

	}

	@Override
	public <N, T> MapData<N, Double> getTracing() {
		return (MapData<N, Double>) tracing;
	}

}
