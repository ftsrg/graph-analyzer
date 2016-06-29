package eu.mondo.map.modelmetrics.impl.incr;

import eu.mondo.map.modelmetrics.impl.simple.NumberOfEdges;
import eu.mondo.map.modelmetrics.incr.IncrementalModelEvaluator;

public class IncrementalNumberOfEdges extends NumberOfEdges implements IncrementalModelEvaluator {

	@Override
	public <N> void newNode(N node) {
	}

	@Override
	public <N> void removeNode(N node) {
	}

	@Override
	public <N, T> void newEdge(T type, N sourceNode, N targetNode) {
		value++;
	}

	@Override
	public <N, T> void removeEdge(T type, N sourceNode, N targetNode) {
		value--;
	}

}
