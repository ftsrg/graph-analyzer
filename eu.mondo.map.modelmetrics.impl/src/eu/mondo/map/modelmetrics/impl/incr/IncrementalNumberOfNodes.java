package eu.mondo.map.modelmetrics.impl.incr;

import eu.mondo.map.modelmetrics.impl.simple.NumberOfNodes;
import eu.mondo.map.modelmetrics.incr.IncrementalModelEvaluator;

public class IncrementalNumberOfNodes extends NumberOfNodes implements IncrementalModelEvaluator {

	@Override
	public <N> void newNode(N node) {
		value++;
	}

	@Override
	public <N> void removeNode(N node) {
		value--;
	}

	@Override
	public <N, T> void newEdge(T type, N sourceNode, N targetNode) {
	}

	@Override
	public <N, T> void removeEdge(T type, N sourceNode, N targetNode) {
	}

}
