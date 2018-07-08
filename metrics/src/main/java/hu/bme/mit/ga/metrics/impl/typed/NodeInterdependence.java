package hu.bme.mit.ga.metrics.impl.typed;

import com.google.common.collect.ListMultimap;
import hu.bme.mit.ga.metrics.impl.simple.Path;
import hu.bme.mit.ga.base.data.ListData;
import hu.bme.mit.ga.adapters.GraphAdapter;
import hu.bme.mit.ga.metrics.AbstractGraphMetric;
import hu.bme.mit.ga.metrics.impl.simple.ShortestPath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NodeInterdependence extends AbstractGraphMetric<ListData<Double>> {

    private ShortestPath shortestPathList;

    public <N> NodeInterdependence() {
        super("NodeInterdependenceList", new ListData<>());
        shortestPathList = new ShortestPath<N>();
    }


    public <N, T> void calculate(final GraphAdapter<N, T> adapter, final int numberOfRandomPairs) {
        ListMultimap<Integer, Integer> pairs =
            shortestPathList.determineRandomPairs(adapter, numberOfRandomPairs);

        N sourceNode;
        N targetNode;
        ArrayList<N> nodeList = new ArrayList<N>(adapter.getIndexer().getNodes());
        for (Integer sourceIndex : pairs.keySet()) {
            sourceNode = nodeList.get(sourceIndex);
            for (Integer targetIndex : pairs.get(sourceIndex)) {
                targetNode = nodeList.get(targetIndex);
                calculate(adapter, sourceNode, targetNode);
            }
        }
    }

    public <N, T> void calculate(final GraphAdapter<N, T> adapter, final N sourceNode, final N targetNode) {
        List<Path<N>> paths = shortestPathList.calculate(adapter, sourceNode, targetNode);
        if (!paths.isEmpty()) {
            double interdependence = calculate(adapter, paths);
            data.add(interdependence);
        }
    }

    public <N, T> double calculate(final GraphAdapter<N, T> adapter, final List<Path<N>> paths) {
        int allPossibleRoutes = 0;
        int allmultitypedRoutes = 0;
        int possibleRoutesInPath = 1;
        int multitypedRoutesInPath = 0;
        Set<T> firstDimensions;

        for (Path<N> path : paths) {
            List<N> nodesInPath = getCheckedNodes(path);
            Set<T> dimensions = getCheckedDimensions(adapter, nodesInPath, 1, 0);
            firstDimensions = new HashSet<>();
            firstDimensions.addAll(dimensions);

            possibleRoutesInPath = 1;
            multitypedRoutesInPath = 0;
            for (int i = 0; i < nodesInPath.size() - 1; i++) {
                dimensions = getCheckedDimensions(adapter, nodesInPath, i + 1, i);
                possibleRoutesInPath *= dimensions.size();
                firstDimensions.retainAll(dimensions);
            }
            multitypedRoutesInPath = possibleRoutesInPath - firstDimensions.size();
            allPossibleRoutes += possibleRoutesInPath;
            allmultitypedRoutes += multitypedRoutesInPath;
        }
        return allmultitypedRoutes / (double) allPossibleRoutes;
    }

    protected <N, T> Set<T> getCheckedDimensions(final GraphAdapter<N, T> adapter, final List<N> nodesInPath, final int sourceIndex, final int targetIndex) {
        Set<T> dimensions = new HashSet<>();
        for (T dim : adapter.getIndexer().getDimensions(nodesInPath.get(sourceIndex))) {
            if (adapter.getIndexer().isAdjacentDirected(nodesInPath.get(sourceIndex), nodesInPath.get(targetIndex), dim)) {
                dimensions.add(dim);
            }
        }
        if (dimensions.isEmpty()) {
            throw new RuntimeException("There is no dimension between the nodes: " +
                nodesInPath.get(sourceIndex) + ", "
                + nodesInPath.get(targetIndex));
        }
        return dimensions;
    }

    protected <N, T> List<N> getCheckedNodes(final Path<N> path) {
        List<N> nodesInPath = path.getPath();
        if (nodesInPath.size() < 2) {
            throw new IllegalArgumentException("The path size is not acceptable, it is lower than 2.");
        }
        return nodesInPath;
    }

    @Override
    public <N, T> void evaluate(GraphAdapter<N, T> adapter) {
        super.evaluate(adapter);
    }

    @Override
    protected <N, T> void evaluateAll(GraphAdapter<N, T> adapter) {
        for (N sourceNode : adapter.getIndexer().getNodes()) {
            for (N targetNode : adapter.getIndexer().getNodes()) {
                if (sourceNode != targetNode) {
                    calculate(adapter, sourceNode, targetNode);
                }
            }
        }
        shortestPathList.calculate(adapter);
    }

    @Override
    public List<Map<String, Object>> getTsvMaps(String[] header) {
        final List<Map<String, Object>> values = new ArrayList<>();
        int i = 0;
        for (Double value : data.getValues()) {
            Map<String, Object> row = new HashMap<>();
            row.put(header[0], "NodeInterDependence");
            row.put(header[1], null);
            row.put(header[2], i);
            row.put(header[3], value);
            values.add(row);
            i++;
        }
        return values;
    }

}
