package hu.bme.mit.mba.modelmetrics.impl.typed;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ListMultimap;

import hu.bme.mit.mba.base.data.ListData;
import hu.bme.mit.mba.modeladapters.ModelAdapter;
import hu.bme.mit.mba.modelmetrics.AbstractModelMetric;
import hu.bme.mit.mba.modelmetrics.impl.simple.Path;
import hu.bme.mit.mba.modelmetrics.impl.simple.ShortestPath;

public class NodeInterdependence extends AbstractModelMetric<ListData<Double>> {

    private ShortestPath shortestPathList;

    public <N> NodeInterdependence() {
        super("NodeInterdependenceList", new ListData<>());
        shortestPathList = new ShortestPath<N>();
    }


    public <N,T> void calculate(final ModelAdapter<N, T> adapter, final int numberOfRandomPairs) {
        clear();
        ListMultimap<Integer, Integer> pairs =
            shortestPathList.determineRandomPairs(adapter, numberOfRandomPairs);

        N sourceNode;
        N targetNode;
        ArrayList<N> nodeList = new ArrayList<N>(adapter.getNodes());
        for (Integer sourceIndex : pairs.keySet()) {
            sourceNode = nodeList.get(sourceIndex);
            for (Integer targetIndex : pairs.get(sourceIndex)) {
                targetNode = nodeList.get(targetIndex);
                calculate(adapter, sourceNode, targetNode);
            }
        }
    }

    public <N,T> void calculate(final ModelAdapter<N, T> adapter, final N sourceNode, final N targetNode) {
        List<Path<N>> paths = shortestPathList.calculate(adapter, sourceNode, targetNode);
        if (!paths.isEmpty()) {
            double interdependence = calculate(adapter, paths);
            data.add(interdependence);
        }
    }

    public <N,T> double calculate(final ModelAdapter<N, T> adapter, final List<Path<N>> paths) {
        int allPossibleRoutes = 0;
        int allMultidimensionalRoutes = 0;
        int possibleRoutesInPath = 1;
        int multiDimensionalRoutesInPath = 0;
        Set<T> firstDimensions;

        for (Path<N> path : paths) {
            List<N> nodesInPath = getCheckedNodes(path);
            Set<T> dimensions = getCheckedDimensions(adapter, nodesInPath, 1, 0);
            firstDimensions = new HashSet<>();
            firstDimensions.addAll(dimensions);

            possibleRoutesInPath = 1;
            multiDimensionalRoutesInPath = 0;
            for (int i = 0; i < nodesInPath.size() - 1; i++) {
                dimensions = getCheckedDimensions(adapter, nodesInPath, i + 1, i);
                possibleRoutesInPath *= dimensions.size();
                firstDimensions.retainAll(dimensions);
            }
            multiDimensionalRoutesInPath = possibleRoutesInPath - firstDimensions.size();
            allPossibleRoutes += possibleRoutesInPath;
            allMultidimensionalRoutes += multiDimensionalRoutesInPath;
        }
        return allMultidimensionalRoutes / (double) allPossibleRoutes;
    }

    protected <N,T> Set<T> getCheckedDimensions(final ModelAdapter<N, T> adapter, final List<N> nodesInPath, final int sourceIndex, final int targetIndex) {
        Set<T> dimensions = new HashSet<>();
        for(T dim : adapter.getTypes(nodesInPath.get(sourceIndex))){
            if (adapter.isAdjacent(nodesInPath.get(sourceIndex),nodesInPath.get(targetIndex),dim)){
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

    protected <N,T> List<N> getCheckedNodes(final Path<N> path) {
        List<N> nodesInPath = path.getPath();
        if (nodesInPath.size() < 2) {
            throw new IllegalArgumentException("The path size is not acceptable, it is lower than 2.");
        }
        return nodesInPath;
    }

    @Override
    public <N, T> void evaluate(ModelAdapter<N, T> adapter) {
        super.evaluate(adapter);
    }

    @Override
    protected <N ,T> void evaluateAll(ModelAdapter<N, T> adapter) {
        clear();
        for (N sourceNode : adapter.getNodes()) {
            for (N targetNode : adapter.getNodes()) {
                if (sourceNode != targetNode) {
                    calculate(adapter, sourceNode, targetNode);
                }
            }
        }
        shortestPathList.calculate(adapter);

    }
}
