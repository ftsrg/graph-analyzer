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

	public void calculate(final Network<?> network) {
		clear();
		for (Node<?> sourceNode : network.getNodes()) {
			for (Node<?> targetNode : network.getNodes()) {
				if (sourceNode != targetNode) {
					calculate(network, sourceNode, targetNode);
				}
			}
		}
		shortestPathList.calculate(network);
	}

	public void calculate(final Network<?> network, final int numberOfRandomPairs) {
		clear();
		ListMultimap<Integer, Integer> pairs = shortestPathList.determineRandomPairs(network,
				numberOfRandomPairs);

		Node<?> sourceNode;
		Node<?> targetNode;
		for (Integer sourceIndex : pairs.keySet()) {
			sourceNode = network.getNodes().get(sourceIndex);
			for (Integer targetIndex : pairs.get(sourceIndex)) {
				targetNode = network.getNodes().get(targetIndex);
				calculate(network, sourceNode, targetNode);
			}
		}
	}

	public void calculate(final Network<?> network, final Node<?> sourceNode, final Node<?> targetNode) {
		List<Path> paths = shortestPathList.calculate(sourceNode, targetNode);
		if (paths.isEmpty()) {
			return;
		}

		int mixedPath = 0;
		for (Path p : paths) {
			if (numberOfDimensions(network, p) >= 2) {
				mixedPath++;
			}
		}
		double interdependence = mixedPath / (double) paths.size();
		values.add(interdependence);
	}

	protected int numberOfDimensions(final Network<?> network, final Path path) {
		List<Node<?>> nodesPaths = path.getPath();
		Set<String> dimensions;
		Set<String> allDimensions = new HashSet<String>();

		for (int i = 0; i < nodesPaths.size() - 1; i++) {
			dimensions = network.getAdjacency().get(nodesPaths.get(i), nodesPaths.get(i + 1));
			allDimensions.addAll(dimensions);
		}
		return allDimensions.size();
	}

}
