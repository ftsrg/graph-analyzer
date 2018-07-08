package hu.bme.mit.ga.metrics.impl.typed;

import hu.bme.mit.ga.base.data.MapData;
import hu.bme.mit.ga.adapters.GraphAdapter;
import hu.bme.mit.ga.metrics.AbstractGraphMetric;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Takes values in [0,1] and computes the ratio of nodes of the network that
 * belong to a particular type.
 */
public class NodeTypedConnectivity extends AbstractGraphMetric<MapData<String, Double>> {

    public NodeTypedConnectivity() {
        super("NodeTypedConnectivity", new MapData<>());
    }

    // /**
    // * Calculates the Node Type Connectivity for a particular type.
    // *
    // * @param type
    // * String representing the type
    // * @param typedEdges
    // * NumberOfTypedEdges object
    // * @param numberOfNodes
    // * NumberOfNodes object
    // */
    // public void calculate(final String type, final NumberOfTypedEdges
    // typedEdges,
    // final NumberOfNodes numberOfNodes) {
    // if (!typedEdges.getValues().containsKey(type)) {
    // throw new IllegalArgumentException("The type does not exist in the
    // map:" + type);
    // }
    // putRatio(type, numberOfNodes.getValue(), typedEdges.getValues());
    // }

    // /**
    // * Calculates the Node Type Connectivity for every possible type
    // * that can be found in the given parameter.
    // *
    // * @param typedEdges
    // * NumberOfTypedEdges object
    // */
    // public void calculate(final NumberOfTypedEdges typedEdges, final
    // NumberOfNodes numberOfNodes) {
    // clear();
    // for (String key : typedEdges.getValues().keySet()) {
    // putRatio(key, numberOfNodes.getValue(), typedEdges.getValues());
    // }
    // }

    // public void calculate(final String type, final Network<?> network) {
    // typedValues.put(type, (double) network.getNumberOfNodes(type) /
    // network.getNumberOfNodes());
    // }

    // public void calculateExclusive(final String type, final Network<?>
    // network) {
    // if (!network.getNodesOnTypes().containsKey(type)) {
    // throw new IllegalArgumentException("Type does not exist: " +
    // type);
    // }
    // int numOfNodes = 0;
    // for (Node<?> node : network.getNodesOnTypes().get(type)) {
    // if (node.getTypes().keySet().size() == 1) {
    // numOfNodes++;
    // }
    // }
    // typedValues.put(type, (double) numOfNodes /
    // network.getNumberOfNodes());
    // }
    //
    // public void calculate(final Network<?> network) {
    // clear();
    // for (String type : network.getTypes()) {
    // calculate(type, network);
    // }
    // }

    // public void calculateExclusive(final Network<?> network) {
    // clear();
    // for (String type : network.getTypes()) {
    // calculateExclusive(type, network);
    // }
    // }
    //
    // protected void putRatio(final String type, final double allNodes,
    // final Map<String, Integer> edges) {
    // typedValues.put(type, edges.get(type) / allNodes);
    // }

    @Override
    protected <N, T> void evaluateAll(GraphAdapter<N, T> adapter) {
        for (T type : adapter.getIndexer().getTypes()) {
            evaluateT(adapter, type);
        }
    }

    protected <N, T> void evaluateT(final GraphAdapter<N, T> adapter, final T type) {
        data.put(type.toString(), (double) adapter.getIndexer().getNumberOfNodes(type) / adapter.getIndexer().getNumberOfNodes());
    }

    @Override
    public List<Map<String, Object>> getTsvMaps(String[] header) {
        final List<Map<String, Object>> values = new ArrayList<>();
        for (String type : data.getValues().keySet()) {
            Double value = data.getValues().get(type);
            Map<String, Object> row = new HashMap<>();
            row.put(header[0], "NodeTypedConnectivity");
            row.put(header[1], type);
            row.put(header[2], null);
            row.put(header[3], value);
            values.add(row);
        }
        return values;
    }
}
