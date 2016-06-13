package eu.mondo.map.modelmetrics.tests;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class TestModel {

	protected Table<String, String, Set<String>> adjacency;
	protected List<String> nodes;

	public TestModel() {
		adjacency = HashBasedTable.create();
		nodes = new ArrayList<>();
	}

	public void addEdge(final String type, final String sourceNode, final String targetNode) {
		if (!adjacency.contains(sourceNode, targetNode)) {
			Set<String> dimSet = new HashSet<String>();
			dimSet.add(type);
			adjacency.put(sourceNode, targetNode, dimSet);
		} else {
			if (!nodes.contains(sourceNode)) {
				nodes.add(sourceNode);
			}
			if (!nodes.contains(targetNode)) {
				nodes.add(targetNode);
			}
			Set<String> dimSet = adjacency.get(sourceNode, targetNode);
			dimSet.add(type);
			adjacency.put(sourceNode, targetNode, dimSet);
		}
	}

	public Table<String, String, Set<String>> getAdjacency() {
		return adjacency;
	}

	public List<String> getNodes() {
		return nodes;
	}

	public boolean isAdjacent(final String sourceNode, final String targetNode) {
		return adjacency.contains(sourceNode, targetNode);
	}

	public boolean isAdjacent(final String sourceNode, final String targetNode, final String dimension) {
		if (adjacency.contains(sourceNode, targetNode)) {
			return adjacency.get(sourceNode, targetNode).contains(dimension);
		}
		return false;
	}

	public void clear() {
		adjacency.clear();
	}

}
