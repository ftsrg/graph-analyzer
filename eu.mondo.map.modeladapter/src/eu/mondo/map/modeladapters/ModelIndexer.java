package eu.mondo.map.modeladapters;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class ModelIndexer<N, T> {

	protected Table<N, N, Set<T>> nodeIndex;
	protected List<N> nodes;
	protected Table<T, N, Integer> typeIndex;

	public ModelIndexer() {
		nodeIndex = HashBasedTable.create();
		nodes = new ArrayList<>();
		typeIndex = HashBasedTable.create();
	}

	public Table<N, N, Set<T>> getAdjacency() {
		return nodeIndex;
	}

	public void setAdjacency(Table<N, N, Set<T>> adjacency) {
		this.nodeIndex = adjacency;
	}

	public boolean isAdjacent(final N source, final N target) {
		return nodeIndex.contains(source, target);
	}

	public boolean isAdjacentBidirectional(final N source, final N target) {
		return nodeIndex.contains(source, target) || nodeIndex.contains(target, source);
	}

	public void addNode(final N node) {
		if (nodeIndex.containsColumn(node) || nodeIndex.containsRow(node)) {
			return;
		}
		nodeIndex.put(node, null, null);
		nodes.add(node);
	}

	public void addEdge(final T type, final N sourceNode, final N targetNode) {
		addNode(sourceNode);
		addNode(targetNode);
		if (!nodeIndex.contains(sourceNode, targetNode)) {
			Set<T> dimSet = new HashSet<T>();
			dimSet.add(type);
			nodeIndex.put(sourceNode, targetNode, dimSet);
		} else {
			// if (!nodes.contains(sourceNode)) {
			// nodes.add(sourceNode);
			// }
			// if (!nodes.contains(targetNode)) {
			// nodes.add(targetNode);
			// }
			Set<T> dimSet = nodeIndex.get(sourceNode, targetNode);
			dimSet.add(type);
			nodeIndex.put(sourceNode, targetNode, dimSet);
		}

		putNodeOnType(type, sourceNode);
		putNodeOnType(type, targetNode);
	}

	protected void putNodeOnType(final T type, final N node) {
		if (!typeIndex.contains(type, node)) {
			typeIndex.put(type, node, 1);
		} else {
			int occurrence = typeIndex.get(type, node);
			occurrence++;
			typeIndex.put(type, node, occurrence);
		}
	}

	public Table<N, N, Set<T>> getNodeIndex() {
		return nodeIndex;
	}

	public void setNodeIndex(Table<N, N, Set<T>> nodeIndex) {
		this.nodeIndex = nodeIndex;
	}

	public List<N> getNodes() {
		return nodes;
	}

	public void setNodes(List<N> nodes) {
		this.nodes = nodes;
	}

	public Table<T, N, Integer> getTypeIndex() {
		return typeIndex;
	}

	public void setTypeIndex(Table<T, N, Integer> typeIndex) {
		this.typeIndex = typeIndex;
	}

}
