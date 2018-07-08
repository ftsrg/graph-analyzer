package hu.bme.mit.ga.metrics.impl.typed;

import com.google.common.collect.Multimap;
import hu.bme.mit.ga.base.data.MapData;
import hu.bme.mit.ga.adapters.GraphAdapter;
import hu.bme.mit.ga.adapters.GraphIndexer;
import hu.bme.mit.ga.metrics.AbstractGraphMetric;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EdgeOverlap extends AbstractGraphMetric<MapData<String, Double>> {
    public EdgeOverlap() {
        super("EdgeOverlap", new MapData<String, Double>());
    }

    @Override
    protected <N, T> void evaluateAll(GraphAdapter<N, T> adapter) {
        for (T condType : adapter.getIndexer().getDimensions()) {
            for (T type : adapter.getIndexer().getDimensions()) {
                if (type != condType) {
                    evaluate(adapter, condType, type);
                }
            }
        }

    }

    private <T, N> void evaluate(GraphAdapter<N, T> adapter, T condType, T type) {
        GraphIndexer indexer = adapter.getIndexer();
        Multimap<N, N> edgesCondType = indexer.getOutgoing(condType);
        Double edges = (double) edgesCondType.entries().size();
        Multimap<N, N> typeOutgoing = indexer.getOutgoing(type);
        double intersection = 0.0;
        for (N n : edgesCondType.keySet()) {
            Collection<N> nodes = edgesCondType.get(n);
            for (N n2 : nodes) {
                if (typeOutgoing.containsEntry(n, n2) | typeOutgoing.containsEntry(n2, n)) {
                    intersection += 1;
                }
            }
        }
        data.put(getKey(type, condType), intersection / edges);
    }

    protected <T> String getKey(T firstType, T secondType) {
        return String.format("%s-%s", firstType.toString(), secondType.toString());
    }


    @Override
    public List<Map<String, Object>> getTsvMaps(String[] header) {
        final List<Map<String, Object>> values = new ArrayList<>();
        for (String type : data.getValues().keySet()) {
            Double value = data.getValues().get(type);
            Map<String, Object> row = new HashMap<>();
            row.put(header[0], "EdgeOverlap");
            row.put(header[1], type);
            row.put(header[2], null);
            row.put(header[3], value);
            values.add(row);
        }
        return values;
    }

}
