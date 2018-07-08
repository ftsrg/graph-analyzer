package hu.bme.mit.ga.metrics.impl.typed;

import hu.bme.mit.ga.base.data.MapData;
import hu.bme.mit.ga.adapters.GraphAdapter;
import hu.bme.mit.ga.metrics.AbstractGraphMetric;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EdgeTypedConnectivity extends AbstractGraphMetric<MapData<String, Double>> {

    public EdgeTypedConnectivity() {
        super("EdgeTypedConnectivity", new MapData<>());
    }

    // public void calculate(final String type, final Network<?> network) {
    // int sumOfEdges = 0;
    // for (Node<?> node : network.getNodes(type)) {
    // sumOfEdges += node.getNumberOfNeighbors(type);
    // }
    // sumOfEdges /= 2;
    // typedValues.put(type, (double) sumOfEdges /
    // network.getNumberOfEdges());
    // }

    // public void calculate(final Network<?> network) {
    // clear();
    // for (String type : network.getTypes()) {
    // calculate(type, network);
    // }
    // }

    // public void calculateExclusive(final String type, final Network<?>
    // network) {
    // throw new UnsupportedOperationException("calculateExclusive is not
    // implemented");
    // int sumOfEdges = 0;
    // Set<String> otherTypes;
    // for (Node<?> node : network.getNodes(type)) {
    // for (Node<?> neighbor : node.getNeighbors(type)) {
    // otherTypes = node.getTypesAsSet();
    // otherTypes.remove(type);
    // for (String type : otherTypes) {
    // if (node.getNeighbors(type).contains(neighbor)) {
    // continue;
    // }
    // sumOfEdges++;
    // }
    // }
    // }
    // sumOfEdges /= 2;
    // typedValues.put(type, (double) sumOfEdges /
    // network.getNumberOfEdges());
    // }

    // public void calculateExclusive(final Network<?> network) {
    // clear();
    // for (String type : network.getTypes()) {
    // calculateExclusive(type, network);
    // }
    // }

    @Override
    protected <N, T> void evaluateAll(GraphAdapter<N, T> adapter) {
        for (T type : adapter.getIndexer().getTypes()) {
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
            row.put(header[0], "EdgeTypedConnectivity");
            row.put(header[1], type);
            row.put(header[2], null);
            row.put(header[3], value);
            values.add(row);
        }
        return values;
    }
}
