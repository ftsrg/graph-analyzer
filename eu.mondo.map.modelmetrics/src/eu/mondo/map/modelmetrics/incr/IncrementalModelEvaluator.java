package eu.mondo.map.modelmetrics.incr;

import eu.mondo.map.modeladapters.ModelAdapter;
import eu.mondo.map.modelmetrics.ModelMetric;

public interface IncrementalModelEvaluator extends ModelMetric {

	// public <N> void reevaluateDeletedNode(final N node);

	public <M, N, T> void reevaluateNewEdge(final ModelAdapter<M, N, T> adapter, final T type, final N sourceNode,
			final N targetNode);

	// public <N, T> void reevaluateDeletedEdge(final T type, final N
	// sourceNode, final N targetNode);

}
