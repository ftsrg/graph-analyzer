package eu.mondo.map.modelmetrics.impl.incr;

import java.util.HashMap;
import java.util.Map;

import eu.mondo.map.modelmetrics.impl.simple.ClusteringCoefficientList;
import eu.mondo.map.modelmetrics.incr.IncrementalModelEvaluator;

public class IncrementalClusteringCoefficient<N> extends ClusteringCoefficientList
		implements IncrementalModelEvaluator {

	protected Map<N, Integer> objectMapping;

	public IncrementalClusteringCoefficient() {
		super();
		objectMapping = new HashMap<>();
	}

	@Override
	public <N> void newNode(N node) {
		addNode(node);
		// TODO Auto-generated method stub

	}

	protected void addNode(N node) {
		Integer value = 1;
		objectMapping.put(node, value);
	}

	@Override
	public <N> void removeNode(N node) {
		// TODO Auto-generated method stub

	}

	@Override
	public <N, T> void newEdge(T type, N sourceNode, N targetNode) {
		// TODO Auto-generated method stub

	}

	@Override
	public <N, T> void removeEdge(T type, N sourceNode, N targetNode) {
		// TODO Auto-generated method stub

	}

}
