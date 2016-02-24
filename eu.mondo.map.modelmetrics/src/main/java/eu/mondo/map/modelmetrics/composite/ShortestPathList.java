package eu.mondo.map.modelmetrics.composite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import eu.mondo.map.core.graph.Node;
import eu.mondo.map.core.metrics.ListMetric;

public class ShortestPathList extends ListMetric<Integer> {

	public ShortestPathList() {
		super("ShortestPathList");
	}

	protected Map<Node<?>, Node<?>> visits;
	protected Map<Node<?>, Integer> depths;
	protected Map<Node<?>, Integer> results;

	public List<Path> calculate(final Node<?> sourceNode, final Node<?> targetNode) {
		if (sourceNode == null) {
			throw new IllegalArgumentException("The sourceNode is null!");
		}
		if (targetNode == null) {
			throw new IllegalArgumentException("The targetNode is null!");
		}
		init();

		Queue<Node<?>> queue = new LinkedList<Node<?>>();
		queue.add(sourceNode);
		visits.put(sourceNode, null);
		depths.put(sourceNode, 0);

		Node<?> currentNode;
		int depth = 0;
		while (!queue.isEmpty()) {
			currentNode = queue.remove();
			if (higherDepth(currentNode, depth)) {
				break;
			}
			for (Node<?> neighborNode : currentNode.getOutgoingNeighbors().keySet()) {
				if (neighborNode == targetNode) {
					results.put(currentNode, 0);
				}
				if (!visits.containsKey(neighborNode)) {
					visit(neighborNode, currentNode);
					depth = depths.get(neighborNode);
					queue.add(neighborNode);
				}
			}
		}
		List<Path> paths;
		if (!results.isEmpty()) {
			paths = resolveResults(targetNode);
		} else {
			paths = new ArrayList<Path>();
		}

		clearFields();
		return paths;
	}

	protected List<Path> resolveResults(final Node<?> targetNode) {
		int depth = depths.get(targetNode);
		List<Path> paths = new ArrayList<Path>();
		for (Node<?> node : results.keySet()) {
			Path path = new Path(depth);
			path.add(targetNode);
			path.add(node);
			Node<?> parent;
			while ((parent = visits.get(node)) != null) {
				path.add(parent);
				node = parent;
			}
			paths.add(path);
		}
		return paths;
	}

	/**
	 * 
	 * @param currentNode
	 * @param expectedDepth
	 * 
	 * @return true if the results Map is not empty and the currentNode's depth is higher or equal than
	 *         the expectedDepth, false otherwise
	 */
	private boolean higherDepth(final Node<?> currentNode, final int expectedDepth) {
		if (!results.isEmpty()) {
			return depths.get(currentNode) >= expectedDepth;
		}
		return false;

	}

	protected void init() {
		if (visits == null) {
			visits = new HashMap<Node<?>, Node<?>>();
		}
		if (depths == null) {
			depths = new HashMap<Node<?>, Integer>();
		}
		if (results == null) {
			results = new HashMap<Node<?>, Integer>();
		}
		clearFields();
	}

	protected void clearFields() {
		visits.clear();
		depths.clear();
		results.clear();
	}

	protected void visit(final Node<?> child, final Node<?> parent) {
		visits.put(child, parent);
		int depth = depths.get(parent);
		depth++;
		depths.put(child, depth);
	}

	public static class Path {

		protected List<Node<?>> path;
		protected int depth;

		public Path() {
			path = new ArrayList<Node<?>>();
		}

		public Path(int depth) {
			path = new ArrayList<Node<?>>();
			this.depth = depth;
		}

		public boolean isEmpty() {
			return path.isEmpty();
		}

		public List<Node<?>> getPath() {
			return path;
		}

		public int getDepth() {
			return depth;
		}

		public void setDepth(int depth) {
			this.depth = depth;
		}

		public int size() {
			return path.size();
		}

		public boolean add(Node<?> e) {
			return path.add(e);
		}

		public boolean addAll(Collection<? extends Node<?>> c) {
			return path.addAll(c);
		}

		@Override
		public String toString() {
			return "Path [path=" + path + ", depth=" + depth + "]";
		}

	}
}
