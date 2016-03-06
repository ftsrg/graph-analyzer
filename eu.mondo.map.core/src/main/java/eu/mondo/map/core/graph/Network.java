package eu.mondo.map.core.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Table;

public class Network<N> {

	/**
	 * Stores the dimensions as keys and the corresponding nodes as values.
	 * Every dimension, so every key has a list of such nodes that have at
	 * least one edge labeled with that dimension. The possible duplication
	 * among nodes in a list is prevented.
	 */
	protected ListMultimap<String, Node<N>> nodesOnDimensions;
	protected List<Node<N>> nodes;
	protected BiMap<N, Node<N>> nodesOnObjects;

	/**
	 * Represents an adjacency matrix between nodes. A pair of nodes appear
	 * in the Table if exists a connection among them. The dimension of the
	 * connection is stored in Set<String> parameter.
	 */
	protected Table<Node<N>, Node<N>, Set<String>> adjacency;

	public Network() {
		nodesOnDimensions = ArrayListMultimap.create();
		nodes = new ArrayList<Node<N>>();
		nodesOnObjects = HashBiMap.create();
		adjacency = HashBasedTable.create();
	}

	public Network(final int expectedNumberOfNodes) {
		nodesOnDimensions = ArrayListMultimap.create();
		nodes = new ArrayList<Node<N>>();
		nodesOnObjects = HashBiMap.create(expectedNumberOfNodes);
	}

	public void addEdge(final String dimension, final N sourceObject, final N targetObject) {
		Node<N> sourceNode = findOrCreateNode(sourceObject);
		Node<N> targetNode = findOrCreateNode(targetObject);

		if (!sourceNode.hasDimension(dimension)) {
			nodesOnDimensions.put(dimension, sourceNode);
		}
		if (!targetNode.hasDimension(dimension)) {
			nodesOnDimensions.put(dimension, targetNode);
		}

		if (isAdjacent(sourceNode, targetNode, dimension)) {
			throw new RuntimeException("A connection in " + dimension
					+ " does already exist between the nodes: " + sourceNode + ", " + targetNode);
		}
		setAdjacent(dimension, sourceNode, targetNode);
		sourceNode.addOutgoingNeighbor(targetNode, dimension);
		targetNode.addIncomingNeighbor(sourceNode, dimension);
	}

	public void setAdjacent(final String dimension, final Node<N> sourceNode, final Node<N> targetNode) {
		if (!adjacency.contains(sourceNode, targetNode)) {
			Set<String> dimSet = new HashSet<String>();
			dimSet.add(dimension);
			adjacency.put(sourceNode, targetNode, dimSet);
		} else {
			Set<String> dimSet = adjacency.get(sourceNode, targetNode);
			dimSet.add(dimension);
			adjacency.put(sourceNode, targetNode, dimSet);
		}
	}

	public Node<N> addNode(final N object) {
		if (nodesOnObjects.containsKey(object)) {
			return nodesOnObjects.get(object);
		}
		return newNode(object);
	}

	public Node<N> addNode(final N object, final boolean replace) {
		if (nodesOnObjects.containsKey(object) && !replace) {
			return nodesOnObjects.get(object);
		}
		return newNode(object);
	}

	protected void checkDimension(final String dimension) {
		if (!nodesOnDimensions.containsKey(dimension)) {
			throw new IllegalArgumentException(
					"Dimension does not exist in the map as a key: " + dimension);
		}
	}

	public void clear() {
		nodesOnDimensions.clear();
		nodes.clear();
		nodesOnObjects.clear();
	}

	protected Node<N> findOrCreateNode(final N object) {
		Node<N> node;
		node = nodesOnObjects.get(object);
		if (node == null) {
			node = newNode(object);
		}
		return node;
	}

	public Table<Node<N>, Node<N>, Set<String>> getAdjacency() {
		return adjacency;
	}

	public List<Node<N>> getNodes() {
		return nodes;
	}

	public Set<String> getDimensions() {
		return nodesOnDimensions.keySet();
	}

	public Node<N> getNode(final N object) {
		return nodesOnObjects.get(object);
	}

	public List<Node<N>> getNodes(final String dimension) {
		checkDimension(dimension);
		return nodesOnDimensions.get(dimension);
	}

	public ListMultimap<String, Node<N>> getNodesOnDimensions() {
		return nodesOnDimensions;
	}

	public BiMap<N, Node<N>> getNodesOnObjects() {
		return nodesOnObjects;
	}

	public int getNumberOfDimensions() {
		return nodesOnDimensions.keySet().size();
	}

	public int getNumberOfEdges() {
		int sumOfEdges = 0;
		for (Node<N> node : nodes) {
			sumOfEdges += node.getDegree();
		}
		sumOfEdges /= 2;
		return sumOfEdges;
	}

	public int getNumberOfNodes() {
		return nodes.size();
	}

	public int getNumberOfNodes(final String dimension) {
		checkDimension(dimension);
		return nodesOnDimensions.get(dimension).size();
	}

	public boolean isAdjacent(final Node<?> sourceNode, final Node<?> targetNode) {
		return adjacency.contains(sourceNode, targetNode);
	}

	public boolean isAdjacent(final Node<?> sourceNode, final Node<?> targetNode, final String dimension) {
		if (adjacency.contains(sourceNode, targetNode)) {
			return adjacency.get(sourceNode, targetNode).contains(dimension);
		}
		return false;
	}

	public boolean isAdjacentUndirected(final Node<?> sourceNode, final Node<?> targetNode) {
		if (isAdjacent(sourceNode, targetNode)) {
			return true;
		} else {
			return isAdjacent(targetNode, sourceNode);
		}
	}

	public boolean isAdjacentUndirected(final Node<?> sourceNode, final Node<?> targetNode,
			final String dimension) {
		if (isAdjacent(sourceNode, targetNode, dimension)) {
			return true;
		} else {
			return isAdjacent(targetNode, sourceNode, dimension);
		}
	}

	protected Node<N> newNode(final N object) {
		Node<N> newNode = new Node<N>(object);
		nodes.add(newNode);
		nodesOnObjects.put(object, newNode);
		return newNode;
	}

}
