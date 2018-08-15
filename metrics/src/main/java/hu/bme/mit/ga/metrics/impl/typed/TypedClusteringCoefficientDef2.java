package hu.bme.mit.ga.metrics.impl.typed;

import hu.bme.mit.ga.adapters.GraphAdapter;

import java.util.*;

public class TypedClusteringCoefficientDef2 extends TypedClusteringCoefficient {

    public TypedClusteringCoefficientDef2() {
        super("TypedClusteringCoefficientDef2E");
    }

    @Override
    public <N, T> void evaluate(final GraphAdapter<N, T> adapter, final N node) {
        long interConnected = 0;
        long numberOfPossibleConnections = 0;
        double coef = 0.0;
        int numberOfNeighbors = 0;

        for (T type1 : adapter.getIndexer().getTypes(node)) {
            for (T type2 : adapter.getIndexer().getTypes(node)) {
                if (!type1.equals(type2)) {
                    numberOfNeighbors = adapter.getIndexer().getNeighbors(node, type1).size();
                    if (useHeuristic && numberOfNeighbors > maxNeighbours) {
                        coef = 0.0;
                        data.add(coef);
                        return;
                    }
                    numberOfNeighbors = adapter.getIndexer().getNeighbors(node, type2).size();
                    if (useHeuristic && numberOfNeighbors > maxNeighbours) {
                        coef = 0.0;
                        data.add(coef);
                        return;
                    }
                    for (N neighbor1 : adapter.getIndexer().getNeighbors(node, type1)) {
                        for (N neighbor2 : adapter.getIndexer().getNeighbors(node, type2)) {
                            if (neighbor1 != neighbor2) {
                                Set<T> types = new HashSet<>();
                                types.addAll(adapter.getIndexer().getTypes(neighbor1));
                                types.addAll(adapter.getIndexer().getTypes(neighbor2));

                                for (T type3 : types) {
                                    if (!type1.equals(type3) && !type2.equals(type3)) {
                                        numberOfPossibleConnections++;
                                        if (adapter.getIndexer().isAdjacentUndirected(neighbor1, neighbor2, type3)) {
                                            interConnected++;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (numberOfPossibleConnections == 0) {
            coef = 0.0;
        } else {
            coef = interConnected / (double) numberOfPossibleConnections;
        }
        data.add(coef);
    }

    @Override
    public List<Map<String, Object>> getTsvMaps(String[] header) {
        final List<Map<String, Object>> values = new ArrayList<>();
        int index = 0;
        for (Double v : data.getValues()) {
            Map<String, Object> value = new HashMap<>();
            value.put(header[0], "TypedClusteringCoefficientDef2E");
            value.put(header[1], null);
            value.put(header[2], index);
            value.put(header[3], v);
            values.add(value);
            index++;
        }
        return values;
    }

}
