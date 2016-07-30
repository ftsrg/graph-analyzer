package eu.mondo.map.modelmetrics.impl.typed;

import eu.mondo.map.base.data.MapData;
import eu.mondo.map.modeladapters.ModelAdapter;
import eu.mondo.map.modeladapters.TypedModelAdapter;
import eu.mondo.map.modelmetrics.AbstractModelMetric;

public class EdgeDimensionConnectivity extends AbstractModelMetric<MapData<String, Double>> {

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
    protected <N, T> void evaluateAll(ModelAdapter<N, T> adapter) {
	TypedModelAdapter<N, T> typedAdapter = castAdapter(adapter);
	for (T type : typedAdapter.getTypes()) {
	    evaluate(typedAdapter, type);
	}
    }

    protected <N, T> void evaluate(TypedModelAdapter<N, T> adapter, T type) {
	int sumOfEdges = 0;
	for (N node : adapter.getNodes(type)) {
	    sumOfEdges += adapter.getDegree(node, type);
	}
	sumOfEdges /= 2;
	data.put(type.toString(), (double) sumOfEdges / adapter.getNumberOfEdges());
    }

}
