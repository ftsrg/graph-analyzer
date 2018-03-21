package hu.bme.mit.mba.modelmetrics.impl.typed;

import hu.bme.mit.mba.base.data.MappedListData;
import hu.bme.mit.mba.base.data.MatrixData;
import hu.bme.mit.mba.modeladapters.ModelAdapter;
import hu.bme.mit.mba.modelmetrics.AbstractModelMetric;
import hu.bme.mit.mba.modelmetrics.incr.IncrementalModelEvaluator;

public class OneTypedClusteringCoefficient extends AbstractModelMetric<MappedListData<String, Double>>
        implements IncrementalModelEvaluator {

    public OneTypedClusteringCoefficient() {
        super("DimensionalTypedClusteringCoefficientList", new MappedListData<>());
    }

    // public void calculate(final Network<N> network) {
    // clear();
    // for (N node : network.getNodes()) {
    // calculate(network, node, false, 0);
    // }
    // }
    //
    // public void calculate(final Network<N> network, final int
    // maxNumberOfNeighbors) {
    // clear();
    // for (N node : network.getNodes()) {
    // calculate(network, node, true, maxNumberOfNeighbors);
    // }
    // }
    //
    // public void calculate(final Network<N> network, final N node, final
    // int maxNumberOfNeighbors) {
    // calculate(network, node, true, maxNumberOfNeighbors);
    // }
    //
    // public void calculate(final Network<N> network, final N node) {
    // calculate(network, node, false, 0);
    // }
    //
    // protected void calculate(final Network<N> network, final N node,
    // final boolean bounded,
    // final int maxNumberOfNeighbors) {
    // long interConnected = 0;
    // long numberOfNeighbors = 0;
    // for (String dimension : node.getDimensionsAsSet()) {
    // interConnected = 0;
    // numberOfNeighbors = 0;
    // numberOfNeighbors = node.getNumberOfDisjunctNeighbors(dimension);
    // if (bounded && numberOfNeighbors > maxNumberOfNeighbors) {
    // typedValues.put(dimension, 0.0);
    // continue;
    // }
    // for (N neighbor1 : node.getNeighbors(dimension)) {
    // for (N neighbor2 : node.getNeighbors(dimension)) {
    // if (neighbor1 != neighbor2) {
    // if (network.isAdjacentUndirected(neighbor1, neighbor2, dimension)) {
    // interConnected++;
    // }
    // }
    //
    // }
    // }
    // double clusteringCoef = 0.0;
    // if (numberOfNeighbors < 2) {
    // clusteringCoef = 0.0;
    // } else {
    // clusteringCoef = interConnected / (double) (numberOfNeighbors *
    // (numberOfNeighbors - 1));
    // }
    // typedValues.put(dimension, clusteringCoef);
    // }
    // }

    // @Override
    // protected boolean isSkippable(Double value) {
    // return value.equals(0.0);
    // }

    @Override
    protected <N, T> void evaluateAll(ModelAdapter<N, T> adapter) {
        evaluateEveryNode(adapter);
    }

    // TODO delete later
    boolean bounded = false;
    int maxNumberOfNeighbors = 100;

    @Override
    public <N, T> void evaluate(ModelAdapter<N, T> adapter, N element) {
        // long interConnected = 0;
        // long numberOfNeighbors = 0;
        for (T type : adapter.getTypes(element)) {
            evaluate(adapter, element, type);
        }
    }

    protected <T, N, M> void evaluate(ModelAdapter<N, T> typedAdapter, N element, T type) {
        long interConnected = 0;
        long numberOfNeighbors = 0;
        numberOfNeighbors = typedAdapter.getDegree(element, type);
        if (bounded && numberOfNeighbors > maxNumberOfNeighbors) {
            data.put(type.toString(), 0.0);
            putToTracing(element, type, 0.0);
            return;
        }
        for (N neighbor1 : typedAdapter.getNeighbors(element, type)) {
            for (N neighbor2 : typedAdapter.getNeighbors(element, type)) {
                if (neighbor1 != neighbor2) {
                    if (typedAdapter.isAdjacentUndirected(neighbor1, neighbor2, type)) {
                        interConnected++;
                    }
                }

            }
        }
        double clusteringCoef = 0.0;
        if (numberOfNeighbors < 2) {
            clusteringCoef = 0.0;
        } else {
            clusteringCoef = interConnected / (double) (numberOfNeighbors * (numberOfNeighbors - 1));
        }
        data.put(type.toString(), clusteringCoef);
        putToTracing(element, type, clusteringCoef);
    }

    protected <N, T> void putToTracing(N element, T type, double value) {
        if (tracing != null) {
            ((MatrixData<N, T, Double>) tracing).put(element, type, value);
        }
    }

    @Override
    public <N, T> void trace() {
        tracing = new MatrixData<N, T, Double>();
    }

    @Override
    public <N, T> MatrixData<N, T, Double> getTracing() {
        return (MatrixData<N, T, Double>) tracing;
    }

    @Override
    public <N, T> void reevaluateNewEdge(ModelAdapter<N, T> adapter, T type, N sourceNode, N targetNode) {
        reevaluate(adapter, sourceNode, type);
        reevaluate(adapter, targetNode, type);
    }

    protected <N, T> void reevaluate(ModelAdapter<N, T> adapter, N node, T type) {
        evaluate(adapter, node, type);
        for (N neighbor : adapter.getNeighbors(node, type)) {
            evaluate(adapter, neighbor, type);
        }
    }

}
