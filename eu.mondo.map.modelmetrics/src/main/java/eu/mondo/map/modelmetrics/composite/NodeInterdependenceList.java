package eu.mondo.map.modelmetrics.composite;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ListMultimap;

import eu.mondo.map.core.graph.Network;
import eu.mondo.map.core.graph.Node;
import eu.mondo.map.core.metrics.ListMetric;
import eu.mondo.map.modelmetrics.composite.ShortestPathList.Path;

public class NodeInterdependenceList extends ListMetric<Double> {

	ShortestPathList shortestPathList;

	public NodeInterdependenceList() {
		super("NodeInterdependenceList");
		shortestPathList = new ShortestPathList();
	}

	public <N> void calculate(final Network<N> network) {
		clear();
		for (Node<N> sourceNode : network.getNodes()) {
			for (Node<N> targetNode : network.getNodes()) {
				if (sourceNode != targetNode) {
					calculate(network, sourceNode, targetNode);
				}
			}
		}
		shortestPathList.calculate(network);
	}

	public <N> void calculate(final Network<N> network, final int numberOfRandomPairs) {
		clear();
		ListMultimap<Integer, Integer> pairs = shortestPathList.determineRandomPairs(network,
				numberOfRandomPairs);

		Node<N> sourceNode;
		Node<N> targetNode;
		for (Integer sourceIndex : pairs.keySet()) {
			sourceNode = network.getNodes().get(sourceIndex);
			for (Integer targetIndex : pairs.get(sourceIndex)) {
				targetNode = network.getNodes().get(targetIndex);
				calculate(network, sourceNode, targetNode);
			}
		}
	}

	public <N> void calculate(final Network<N> network, final Node<N> sourceNode, final Node<N> targetNode) {
		List<Path> paths = shortestPathList.calculate(sourceNode, targetNode);
		if (paths.isEmpty()) {
			return;
		}

		double interdependence = calculate(network, paths);
		values.add(interdependence);
	}

	public <N> double calculate(final Network<N> network, final List<Path> paths) {
		int allPossibleRoutes = 0;
		int allMultidimensionalRoutes = 0;
		int possibleRoutesInPath = 1;
		int multiDimensionalRoutesInPath = 0;
		Set<String> firstDimensions;

		for (Path path : paths) {
			List<Node<N>> nodesInPath = getCheckedNodes(path);
			Set<String> dimensions = getCheckedDimensions(network, nodesInPath, 1, 0);
			firstDimensions = new HashSet<String>();
			firstDimensions.addAll(dimensions);

			possibleRoutesInPath = 1;
			multiDimensionalRoutesInPath = 0;
			for (int i = 0; i < nodesInPath.size() - 1; i++) {
				dimensions = getCheckedDimensions(network, nodesInPath, i + 1, i);
				possibleRoutesInPath *= dimensions.size();
				firstDimensions.retainAll(dimensions);
			}
			multiDimensionalRoutesInPath = possibleRoutesInPath - firstDimensions.size();
			allPossibleRoutes += possibleRoutesInPath;
			allMultidimensionalRoutes += multiDimensionalRoutesInPath;
		}
		return allMultidimensionalRoutes / (double) allPossibleRoutes;
	}

	protected <N> Set<String> getCheckedDimensions(final Network<N> network, final List<Node<N>> nodesInPath,
			final int sourceIndex, final int targetIndex) {
		Set<String> dimensions = network.getAdjacency().get(nodesInPath.get(sourceIndex),
				nodesInPath.get(targetIndex));
		if (dimensions == null || dimensions.isEmpty()) {
//			System.out.println(network.getAdjacency());
			throw new RuntimeException("There is no dimension between the nodes: "
					+ nodesInPath.get(sourceIndex) + ", " + nodesInPath.get(targetIndex));
		}
		return dimensions;
	}

	protected <N> List<Node<N>> getCheckedNodes(final Path path) {
		List<Node<N>> nodesInPath = path.getPath();
		if (nodesInPath.size() < 2) {
			throw new IllegalArgumentException("The path size is not acceptable, it is lower than 2.");
		}
		return nodesInPath;
	}

}
