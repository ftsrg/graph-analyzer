package hu.bme.mit.mba.modelmetrics.impl.typed;

import hu.bme.mit.mba.base.data.MappedListData;
import hu.bme.mit.mba.modeladapters.GraphAdapter;
import hu.bme.mit.mba.modelmetrics.AbstractGraphMetric;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OneTypedClusteringCoefficient extends AbstractGraphMetric<MappedListData<String, Double>> {

    // TODO delete later
    boolean bounded = false;

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
    int maxNumberOfNeighbors = 100;

    public OneTypedClusteringCoefficient() {
        super("DimensionalTypedClusteringCoefficientList", new MappedListData<>());
    }

    @Override
    protected <N, T> void evaluateAll(GraphAdapter<N, T> adapter) {
        evaluateEveryNode(adapter);
    }

    @Override
    public <N, T> void evaluate(GraphAdapter<N, T> adapter, N element) {
        // long interConnected = 0;
        // long numberOfNeighbors = 0;
        for (T type : adapter.getIndexer().getDimensions(element)) {
            evaluate(adapter, element, type);
        }
    }

    protected <T, N, M> void evaluate(GraphAdapter<N, T> typedAdapter, N element, T type) {
        long interConnected = 0;
        long numberOfNeighbors = 0;
        numberOfNeighbors = typedAdapter.getIndexer().getDegree(element, type);
        if (bounded && numberOfNeighbors > maxNumberOfNeighbors) {
            data.put(type.toString(), 0.0);
            return;
        }
        for (N neighbor1 : typedAdapter.getIndexer().getNeighbors(element, type)) {
            for (N neighbor2 : typedAdapter.getIndexer().getNeighbors(element, type)) {
                if (neighbor1 != neighbor2) {
                    if (typedAdapter.getIndexer().isAdjacentUndirected(neighbor1, neighbor2, type)) {
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
    }

    @Override
    public List<Map<String, Object>> getTsvMaps(String[] header) {
        final List<Map<String, Object>> values = new ArrayList<>();
        for (String type : data.getValues().keySet()) {
            int i = 0;
            for (Double value : data.getValues().get(type)) {
                Map<String, Object> row = new HashMap<>();
                row.put(header[0], "OneTypedClusteringCoefficient");
                row.put(header[1], type);
                row.put(header[2], i);
                row.put(header[3], value);
                values.add(row);
                i++;
            }
        }
        return values;
    }
}
