package eu.mondo.map.modelmetrics;

public interface IncrementalCalculator {

	public <N> void newNode(final N node);

	public <N> void newEdge(final N sourceNode, final N targetNode);

}
