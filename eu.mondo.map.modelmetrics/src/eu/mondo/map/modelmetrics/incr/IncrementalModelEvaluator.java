package eu.mondo.map.modelmetrics.incr;

import eu.mondo.map.modelmetrics.ModelMetric;

public interface IncrementalModelEvaluator extends ModelMetric {

	public <N> void reevaluateAddedNode(final N node);

	public <N> void reevaluateDeletedNode(final N node);

	public <N, T> void reevaluateAddedEdge(final T type, final N sourceNode, final N targetNode);

	public <N, T> void reevaluateDeletedEdge(final T type, final N sourceNode, final N targetNode);

}
