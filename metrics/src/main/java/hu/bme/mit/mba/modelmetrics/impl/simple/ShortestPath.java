package hu.bme.mit.mba.modelmetrics.impl.simple;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import hu.bme.mit.mba.base.data.ListData;
import hu.bme.mit.mba.modeladapters.ModelAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;


public class ShortestPath<N> extends ListData<Integer> {

    protected ListMultimap<N, N> visits;
    protected Map<N, Integer> depths;
    protected Map<N, Integer> results;

    public ShortestPath() {
        super();
    }

    public <T> void calculate(final ModelAdapter<N, T> adapter) {
        clear();
        for (N sourceNode : adapter.getIndexer().getNodes()) {
            for (N targetNode : adapter.getIndexer().getNodes()) {
                if (sourceNode != targetNode) {
                    addDepth(calculate(adapter, sourceNode, targetNode));
                }
            }
        }

    }

    public <T> void calculate(final ModelAdapter<N, T> adapter, final int numberOfRandomPairs) {
        clear();
        ListMultimap<Integer, Integer> pairs = determineRandomPairs(adapter, numberOfRandomPairs);
        ArrayList<N> nodeList = new ArrayList<>(adapter.getIndexer().getNodes());
        N sourceNode;
        N targetNode;
        for (Integer sourceIndex : pairs.keySet()) {
            sourceNode = nodeList.get(sourceIndex);
            for (Integer targetIndex : pairs.get(sourceIndex)) {
                targetNode = nodeList.get(targetIndex);
                addDepth(calculate(adapter, sourceNode, targetNode));
            }
        }
    }

    public <T> ListMultimap<Integer, Integer> determineRandomPairs(final ModelAdapter<N, T> adapter, final int numberOfRandomPairs) {
        int numberOfNodes = adapter.getIndexer().getNumberOfNodes();
        checkNumberOfRandomPairs(numberOfNodes, numberOfRandomPairs);
        ListMultimap<Integer, Integer> pairs = ArrayListMultimap.create();
        Random random = new Random();
        int iteration = 0;
        int firstIndex = 0;
        int secondIndex = 0;
        while (iteration < numberOfRandomPairs) {
            firstIndex = random.nextInt(numberOfNodes);
            secondIndex = random.nextInt(numberOfNodes);
            if (firstIndex == secondIndex) {
                continue;
            }
            if (!pairs.containsEntry(firstIndex, secondIndex)) {
                pairs.put(firstIndex, secondIndex);
                iteration++;
            }
        }
        return pairs;
    }

    protected  void addDepth(final List<Path<N>> paths) {
        if (!paths.isEmpty()) {
            values.add(paths.get(0).getDepth());
        }
    }

    protected void checkNumberOfRandomPairs(long numberOfNodes, long numberOfRandomPairs) {
        if (numberOfRandomPairs < 1) {
            throw new IllegalArgumentException("The numberOfRandomPairs parameter must be positive");
        }

        if (numberOfRandomPairs > numberOfNodes * (numberOfNodes - 1)) {
            throw new IllegalArgumentException(
                "The numberOfRandomPairs parameter is invalid, cannot find as many number of shortest paths as "
                    + numberOfRandomPairs);
        }

    }

    public <T> List<Path<N>> calculate(ModelAdapter<N, T> adapter, N sourceNode, N targetNode) {
        if (sourceNode == null) {
            throw new IllegalArgumentException("The sourceNode is null!");
        }
        if (targetNode == null) {
            throw new IllegalArgumentException("The targetNode is null!");
        }
        init();

        Queue<N> queue = new LinkedList<>();
        queue.add(sourceNode);
        visits.put(sourceNode, null);
        depths.put(sourceNode, 0);

        N currentNode;
        int depth = 0;
        while (!queue.isEmpty()) {
            currentNode = queue.remove();
            if (higherDepth(currentNode, depth)) {
                break;
            }
            for (N neighborNode : adapter.getIndexer().getOutgoingNeighbors(currentNode)) {
                if (neighborNode == targetNode) {
                    results.put(currentNode, 0);
                }
                if (!visits.containsKey(neighborNode)) {
                    visit(neighborNode, currentNode);
                    depth = depths.get(neighborNode);
                    queue.add(neighborNode);
                } else if (depths.get(currentNode) < depths.get(neighborNode)) {
                    visits.put(neighborNode, currentNode);
                }
            }
        }
        List<Path<N>> paths;
        if (!results.isEmpty()) {
            paths = resolveResults(targetNode);
        } else {
            paths = new ArrayList<>();
        }

        clearFields();
        return paths;
    }

    protected List<Path<N>> resolveResults(final N targetNode) {
        int depth = depths.get(targetNode);
        List<Path<N>> allPaths = new ArrayList<>();
        for (N node : results.keySet()) {
            Path<N> path = new Path<>(depth);
            path.add(targetNode);
            path.add(node);
            allPaths.add(path);
            resolvePaths(allPaths, path, node);
        }
        return allPaths;
    }

    private void resolvePaths(List<Path<N>> allPaths, Path<N> path, N node) {
        if (reachedSourceNode(node)) {
            return;
        }
        List<N> neighbors = visits.get(node);
        for (int i = 1; i < neighbors.size(); i++) {
            Path<N> newPath = new Path<>(path);
            newPath.add(neighbors.get(i));
            allPaths.add(newPath);
            resolvePaths(allPaths, newPath, neighbors.get(i));
        }
        path.add(neighbors.get(0));
        resolvePaths(allPaths, path, neighbors.get(0));
    }

    protected boolean reachedSourceNode(N node) {
        if (visits.get(node).get(0) == null) {
            return true;
        }
        return false;
    }

    /**
     * @param currentNode
     * @param expectedDepth
     * @return true if the results Map is not empty and the currentNode's depth
     * is higher or equal than the expectedDepth, false otherwise
     */
    protected boolean higherDepth(final N currentNode, final int
        expectedDepth) {
        if (!results.isEmpty()) {
            return depths.get(currentNode) >= expectedDepth;
        }
        return false;

    }

    protected void init() {
        if (visits == null) {
            visits = ArrayListMultimap.create();
        }
        if (depths == null) {
            depths = new HashMap<>();
        }
        if (results == null) {
            results = new HashMap<>();
        }
        clearFields();
    }

    protected void clearFields() {
        visits.clear();
        depths.clear();
        results.clear();
    }

    protected void visit(final N child, final N parent) {
        visits.put(child, parent);
        int depth = depths.get(parent);
        depth++;
        depths.put(child, depth);
    }
}
