package eu.mondo.map.modelmetrics.incr;

public interface IncrementalModelEvaluator {

	public <N> void newNode(final N node);

	public <N> void removeNode(final N node);

	public <N, T> void newEdge(final T type, final N sourceNode, final N targetNode);

	public <N, T> void removeEdge(final T type, final N sourceNode, final N targetNode);

}
