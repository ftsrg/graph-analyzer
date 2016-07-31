package eu.mondo.map.modelmetrics.impl.typed;

import eu.mondo.map.base.data.MapData;
import eu.mondo.map.modeladapters.ModelAdapter;
import eu.mondo.map.modeladapters.TypedModelAdapter;
import eu.mondo.map.modelmetrics.AbstractModelMetric;

public class PairwiseMultiplexity extends AbstractModelMetric<MapData<String, Double>> {

    public PairwiseMultiplexity() {
	super("PairwiseMultiplexity", new MapData<>());
    }

    @Override
    protected <N, T> void evaluateAll(ModelAdapter<N, T> adapter) {
	TypedModelAdapter<N, T> typedAdapter = castAdapter(adapter);

	for (T firstType : typedAdapter.getTypes()) {
	    for (T secondType : typedAdapter.getTypes()) {
		if (firstType != secondType) {
		    if (newPair(firstType, secondType)) {
			evaluate(typedAdapter, firstType, secondType);
		    }
		}
	    }
	}
    }

    protected <T> boolean newPair(T firstType, T secondType) {
	String key = getKey(firstType, secondType);
	String reversedKey = getKey(secondType, firstType);

	return !(data.getValues().containsKey(key) || data.getValues().containsKey(reversedKey));
    }

    protected <N, T> void evaluate(TypedModelAdapter<N, T> adapter, T firstType, T secondType) {
	int firstSizeofNodes = adapter.getNumberOfNodes(firstType);
	int secondSizeofNodes = adapter.getNumberOfNodes(secondType);
	int nodesInIntersection = 0;

	if (firstSizeofNodes < secondSizeofNodes) {
	    nodesInIntersection += getIntersection(adapter, firstType, secondType);
	} else {
	    nodesInIntersection += getIntersection(adapter, secondType, firstType);
	}

	double multiplexity = 0.0;

	multiplexity = nodesInIntersection / (double) adapter.getNumberOfNodes();
	data.put(getKey(firstType, secondType), multiplexity);
    }

    protected <T> String getKey(T firstType, T secondType) {
	return String.format("%s-%s", firstType.toString(), secondType.toString());
    }

    protected <T, N> int getIntersection(TypedModelAdapter<N, T> adapter, T firstType, T secondType) {
	int nodesInIntersection = 0;
	for (N node : adapter.getNodes(firstType)) {
	    if (adapter.getTypes(node).contains(secondType)) {
		nodesInIntersection++;
	    }
	}
	return nodesInIntersection;
    }

}
