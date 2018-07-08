package hu.bme.mit.mba.modelmetrics.impl.typed;

import hu.bme.mit.mba.base.data.MapData;
import hu.bme.mit.mba.modeladapters.GraphAdapter;
import hu.bme.mit.mba.modelmetrics.AbstractGraphMetric;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EdgeDimensionConnectivity extends AbstractGraphMetric<MapData<String, Double>> {

    public EdgeDimensionConnectivity() {
        super("EdgeDimensionConnectivity", new MapData<>());
    }

    // public void calculate(final String dimension, final Network<?> network) {
    // int sumOfEdges = 0;
    // for (Node<?> node : network.getNodes(dimension)) {
    // sumOfEdges += node.getNumberOfNeighbors(dimension);
    // }
    // sumOfEdges /= 2;
    // typedValues.put(dimension, (double) sumOfEdges /
    // network.getNumberOfEdges());
    // }

    // public void calculate(final Network<?> network) {
    // clear();
    // for (String dimension : network.getDimensions()) {
    // calculate(dimension, network);
    // }
    // }

    // public void calculateExclusive(final String dimension, final Network<?>
    // network) {
    // throw new UnsupportedOperationException("calculateExclusive is not
    // implemented");
    // int sumOfEdges = 0;
    // Set<String> otherDimensions;
    // for (Node<?> node : network.getNodes(dimension)) {
    // for (Node<?> neighbor : node.getNeighbors(dimension)) {
    // otherDimensions = node.getDimensionsAsSet();
    // otherDimensions.remove(dimension);
    // for (String dim : otherDimensions) {
    // if (node.getNeighbors(dimension).contains(neighbor)) {
    // continue;
    // }
    // sumOfEdges++;
    // }
    // }
    // }
    // sumOfEdges /= 2;
    // typedValues.put(dimension, (double) sumOfEdges /
    // network.getNumberOfEdges());
    // }

    // public void calculateExclusive(final Network<?> network) {
    // clear();
    // for (String dimension : network.getDimensions()) {
    // calculateExclusive(dimension, network);
    // }
    // }

    @Override
    protected <N, T> void evaluateAll(GraphAdapter<N, T> adapter) {
        for (T type : adapter.getIndexer().getDimensions()) {
            evaluateT(adapter, type);
        }
    }

    protected <N, T> void evaluateT(GraphAdapter<N, T> adapter, T type) {
        int sumOfEdges = 0;
        for (N node : adapter.getIndexer().getNodes(type)) {
            sumOfEdges += adapter.getIndexer().getDegree(node, type);
        }
        sumOfEdges /= 2;
        data.put(type.toString(), (double) sumOfEdges / adapter.getIndexer().getNumberOfEdges());
    }

    @Override
    public List<Map<String, Object>> getTsvMaps(String[] header) {
        final List<Map<String, Object>> values = new ArrayList<>();
        for (String type : data.getValues().keySet()) {
            Double value = data.getValues().get(type);
            Map<String, Object> row = new HashMap<>();
            row.put(header[0], "EdgeDimensionConnectivity");
            row.put(header[1], type);
            row.put(header[2], null);
            row.put(header[3], value);
            values.add(row);
        }
        return values;
    }
}
