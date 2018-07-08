package hu.bme.mit.mba.modelmetrics.impl.typed;

import hu.bme.mit.mba.base.data.MapData;
import hu.bme.mit.mba.modeladapters.GraphAdapter;
import hu.bme.mit.mba.modelmetrics.AbstractGraphMetric;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PairwiseMultiplexity extends AbstractGraphMetric<MapData<String, Double>> {

    public PairwiseMultiplexity() {
        super("PairwiseMultiplexity", new MapData<>());
    }

    @Override
    protected <N, T> void evaluateAll(GraphAdapter<N, T> adapter) {
        for (T firstType : adapter.getIndexer().getDimensions()) {
            for (T secondType : adapter.getIndexer().getDimensions()) {
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

    protected <N, T> void evaluate(GraphAdapter<N, T> adapter, T firstType, T secondType) {
        int firstSizeofNodes = adapter.getIndexer().getNumberOfNodes(firstType);
        int secondSizeofNodes = adapter.getIndexer().getNumberOfNodes(secondType);
        int nodesInIntersection = 0;

        if (firstSizeofNodes < secondSizeofNodes) {
            nodesInIntersection += getIntersection(adapter, firstType, secondType);
        } else {
            nodesInIntersection += getIntersection(adapter, secondType, firstType);
        }

        double multiplexity = 0.0;

        multiplexity = nodesInIntersection / (double) adapter.getIndexer().getNumberOfNodes();
        data.put(getKey(firstType, secondType), multiplexity);
    }

    protected <T> String getKey(T firstType, T secondType) {
        return String.format("%s-%s", firstType.toString(), secondType.toString());
    }

    protected <T, N> int getIntersection(GraphAdapter<N, T> adapter, T firstType, T secondType) {
        int nodesInIntersection = 0;
        for (N node : adapter.getIndexer().getNodes(firstType)) {
            if (adapter.getIndexer().getDimensions(node).contains(secondType)) {
                nodesInIntersection++;
            }
        }
        return nodesInIntersection;
    }

    @Override
    public List<Map<String, Object>> getTsvMaps(String[] header) {
        final List<Map<String, Object>> values = new ArrayList<>();
        for (String type : data.getValues().keySet()) {
            Double value = data.getValues().get(type);
            Map<String, Object> row = new HashMap<>();
            row.put(header[0], "PairwiseMultiplexity");
            row.put(header[1], type);
            row.put(header[2], null);
            row.put(header[3], value);
            values.add(row);
        }
        return values;
    }

}
