package hu.bme.mit.ga.metrics.impl.typed;

import hu.bme.mit.ga.base.data.MapData;
import hu.bme.mit.ga.adapters.GraphAdapter;
import hu.bme.mit.ga.metrics.AbstractGraphMetric;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberOfTypedEdges extends AbstractGraphMetric<MapData<String, Integer>> {

    public NumberOfTypedEdges() {
        super("NumberOfTypedEdges", new MapData<>());
    }

    // public void calculate(final Network<?> network) {
    // clear();
    // for (String dimension : network.getNodesOnDimensions().keySet()) {
    // int sumOfEdges = 0;
    // for (Node<?> node : network.getNodesOnDimensions().get(dimension)) {
    // sumOfEdges += node.getNumberOfNeighbors(dimension);
    // }
    // sumOfEdges /= 2;
    // typedValues.put(dimension, sumOfEdges);
    // }
    // }

    @Override
    protected <N, T> void evaluateAll(GraphAdapter<N, T> adapter) {
        for (T type : adapter.getIndexer().getDimensions()) {
            int sumOfEdges = 0;
            for (N node : adapter.getIndexer().getNodes(type)) {
                sumOfEdges += adapter.getIndexer().getDegree(node, type);
            }
            sumOfEdges /= 2;
            data.put(type.toString(), sumOfEdges);
        }
    }

    @Override
    public List<Map<String, Object>> getTsvMaps(String[] header) {
        final List<Map<String, Object>> values = new ArrayList<>();
        for (String type : data.getValues().keySet()) {
            Map<String, Object> row = new HashMap<>();
            Integer value = data.getValues().get(type);
            row.put(header[0], "NumberOfTypedEdges");
            row.put(header[1], type);
            row.put(header[2], null);
            row.put(header[3], value);
            values.add(row);
        }
        return values;
    }

}
