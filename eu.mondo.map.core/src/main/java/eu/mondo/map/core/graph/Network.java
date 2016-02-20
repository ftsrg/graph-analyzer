package eu.mondo.map.core.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ListMultimap;

public class Network<N> {

	protected ListMultimap<String, Node<N>> nodesOnDimensions;
	protected List<Node<N>> nodes;
	protected BiMap<N, Node<N>> nodesOnObjects;

	public Network() {
		nodesOnDimensions = ArrayListMultimap.create();
		nodes = new ArrayList<Node<N>>();
		nodesOnObjects = HashBiMap.create();
	}

	public Network(int expectedNumberOfNodes) {
		nodesOnDimensions = ArrayListMultimap.create();
		nodes = new ArrayList<Node<N>>();
		nodesOnObjects = HashBiMap.create(expectedNumberOfNodes);
	}

	public Node<N> addNode(final N object) {
		if (nodesOnObjects.containsKey(object)) {
			return nodesOnObjects.get(object);
		}
		return newNode(object);
	}

	public Node<N> addNode(final N object, boolean replace) {
		if (nodesOnObjects.containsKey(object) && !replace) {
			return nodesOnObjects.get(object);
		}
		return newNode(object);
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
		sourceNode.addOutgoingNeighbor(targetNode, dimension);
		targetNode.addIncomingNeighbor(sourceNode, dimension);
	}

	protected Node<N> newNode(final N object) {
		Node<N> newNode = new Node<N>(object);
		nodes.add(newNode);
		nodesOnObjects.put(object, newNode);
		return newNode;
	}

	protected Node<N> findOrCreateNode(final N object) {
		Node<N> node;
		node = nodesOnObjects.get(object);
		if (node == null) {
			node = newNode(object);
		}
		return node;
	}

	public int getNumberOfNodes() {
		return nodes.size();
	}

	public Set<String> getDimensions() {
		return nodesOnDimensions.keySet();
	}

	public int getNumberOfNodes(final String dimension) {
		checkDimension(dimension);
		return nodesOnDimensions.get(dimension).size();
	}

	public int getNumberOfEdges() {
		int sumOfEdges = 0;
		for (Node<N> node : nodes) {
			sumOfEdges += node.getDegree();
		}
		sumOfEdges /= 2;
		return sumOfEdges;
	}

	public int getNumberOfDimensions() {
		return nodesOnDimensions.keySet().size();
	}

	protected void checkDimension(final String dimension) {
		if (!nodesOnDimensions.containsKey(dimension)) {
			throw new IllegalArgumentException("Dimension does not exist in the map as a key: "
					+ dimension);
		}
	}

	public ListMultimap<String, Node<N>> getNodesOnDimensions() {
		return nodesOnDimensions;
	}

	public Node<N> getNode(final N object) {
		return nodesOnObjects.get(object);
	}

	public List<Node<N>> getNodes(final String dimension) {
		checkDimension(dimension);
		return nodesOnDimensions.get(dimension);
	}

	public List<Node<N>> getAllNodes() {
		return nodes;
	}

	public BiMap<N, Node<N>> getNodesOnObjects() {
		return nodesOnObjects;
	}

}
