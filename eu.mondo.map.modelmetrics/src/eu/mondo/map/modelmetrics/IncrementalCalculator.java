package eu.mondo.map.modelmetrics;

public interface IncrementalCalculator {

	public void newNode(final Object node);

	public void newEdge(final Object edge);

}
