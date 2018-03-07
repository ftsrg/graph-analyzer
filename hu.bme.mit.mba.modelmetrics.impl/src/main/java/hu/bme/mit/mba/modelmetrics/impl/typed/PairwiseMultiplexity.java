package hu.bme.mit.mba.modelmetrics.impl.typed;

import hu.bme.mit.mba.base.data.MapData;
import hu.bme.mit.mba.modeladapters.ModelAdapter;
import hu.bme.mit.mba.modelmetrics.AbstractModelMetric;

public class PairwiseMultiplexity extends AbstractModelMetric<MapData<String, Double>> {

    public PairwiseMultiplexity() {
        super("PairwiseMultiplexity", new MapData<>());
    }

    @Override
    protected <N, T> void evaluateAll(ModelAdapter<N, T> adapter) {
        for (T firstType : adapter.getTypes()) {
            for (T secondType : adapter.getTypes()) {
                if (firstType != secondType) {
                    if (newPair(firstType, secondType)) {
                        evaluate(adapter, firstType, secondType);
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

    protected <N, T> void evaluate(ModelAdapter<N, T> adapter, T firstType, T secondType) {
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

    protected <T, N> int getIntersection(ModelAdapter<N, T> adapter, T firstType, T secondType) {
        int nodesInIntersection = 0;
        for (N node : adapter.getNodes(firstType)) {
            if (adapter.getTypes(node).contains(secondType)) {
                nodesInIntersection++;
            }
        }
        return nodesInIntersection;
    }

}
