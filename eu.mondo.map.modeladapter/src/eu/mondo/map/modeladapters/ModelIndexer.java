package eu.mondo.map.modeladapters;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Table;

public class ModelIndexer<N, T> {

	protected Table<N, N, Set<T>> adjacency;
	protected List<N> nodes;
	protected ListMultimap<T, N> nodesOnTypes;

	public ModelIndexer() {
		adjacency = HashBasedTable.create();
		nodes = new ArrayList<>();
		nodesOnTypes = ArrayListMultimap.create();
	}

	public Table<N, N, Set<T>> getAdjacency() {
		return adjacency;
	}

	public void setAdjacency(Table<N, N, Set<T>> adjacency) {
		this.adjacency = adjacency;
	}

	public boolean isAdjacent(final N source, final N target) {
		return adjacency.contains(source, target);
	}

	public boolean isAdjacentBidirectional(final N source, final N target) {
		return adjacency.contains(source, target) || adjacency.contains(target, source);
	}

	public void addNode(final N node) {
		if (adjacency.containsColumn(node) || adjacency.containsRow(node)) {
			return;
		}
		adjacency.put(node, null, null);
		nodes.add(node);
	}

	public void addEdge(final T type, final N sourceNode, final N targetNode) {
		if (!adjacency.contains(sourceNode, targetNode)) {
			Set<T> dimSet = new HashSet<T>();
			dimSet.add(type);
			adjacency.put(sourceNode, targetNode, dimSet);
		} else {
			if (!nodes.contains(sourceNode)) {
				nodes.add(sourceNode);
			}
			if (!nodes.contains(targetNode)) {
				nodes.add(targetNode);
			}
			Set<T> dimSet = adjacency.get(sourceNode, targetNode);
			dimSet.add(type);
			adjacency.put(sourceNode, targetNode, dimSet);
		}
	}

}
