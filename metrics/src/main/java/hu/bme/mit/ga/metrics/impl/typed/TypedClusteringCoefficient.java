package hu.bme.mit.ga.metrics.impl.typed;

import hu.bme.mit.ga.adapters.GraphAdapter;
import hu.bme.mit.ga.base.data.ListData;
import hu.bme.mit.ga.metrics.AbstractGraphMetric;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TypedClusteringCoefficient extends AbstractGraphMetric<ListData<Double>> {

    protected int maxNeighbours = 1000;
    protected boolean useHeuristic = false;

    public TypedClusteringCoefficient() {
        super("TypedClusteringCoefficient", new ListData<>());
    }

    public int getMaxNeighbours() {
        return maxNeighbours;
    }

    public void setMaxNeighbours(int maxNeighbours) {
        this.maxNeighbours = maxNeighbours;
    }

    public boolean isUseHeuristic() {
        return useHeuristic;
    }

    public void setUseHeuristic(boolean useHeuristic) {
        this.useHeuristic = useHeuristic;
    }

    @Override
    public <N, T> void evaluate(final GraphAdapter<N, T> adapter) {
        for (N node : adapter.getIndexer().getNodes()) {
            calculateFirstDefinition(adapter, node);
        }
    }

    public <N, T> double calculateFirstDefinition(final GraphAdapter<N, T> adapter, final N node) {
        long interConnected = 0;
        long numberOfNeighbors = 0;
        long numberOfPossibleConnections = 0;
        double coef = 0.0;

        for (T type1 : adapter.getIndexer().getTypes(node)) {
            numberOfNeighbors = adapter.getIndexer().getNeighbors(node, type1).size();
            if (useHeuristic && numberOfNeighbors > maxNeighbours) {
                coef = 0.0;
                data.add(coef);
                return coef;
            }
            for (N neighbor1 : adapter.getIndexer().getNeighbors(node, type1)) {
                for (N neighbor2 : adapter.getIndexer().getNeighbors(node, type1)) {
                    if (neighbor1 != neighbor2) {
                        Set<T> types = new HashSet<>();
                        types.addAll(adapter.getIndexer().getTypes(neighbor1));
                        types.addAll(adapter.getIndexer().getTypes(neighbor1));
                        for (T type2 : types) {
                            if (!type1.equals(type2)) {
                                numberOfPossibleConnections++;
                                if (adapter.getIndexer().isAdjacentUndirected(neighbor1, neighbor2, type2)) {
                                    interConnected++;
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
        return coef;
    }


//    public <N, T> void calculateSecondDefinition(final GraphAdapter<N, T> adapter) {
//        clear();
//        for (N node : adapter.getNodes()) {
//            calculateSecondDefinition(adapter, node);
//        }
//    }

//    public <N, T> double calculateSecondDefinition(final GraphAdapter<N, T> adapter, final N node) {
//        long interConnected = 0;
//        long numberOfPossibleConnections = 0;
//        double coef = 0.0;
//        int numberOfNeighbors = 0;
//        ArrayList<Double> values = new ArrayList<>();
//
//
//        for (T type1 : adapter.getTypes(node)) {
//            for (T type2 : adapter.getTypes(node)) {
//                if (!type1.equals(type2)) {
//                    numberOfNeighbors = adapter.getNeighbors(node, type1).size();
//                    if (useHeuristic && numberOfNeighbors > maxNeighbours) {
//                        coef = 0.0;
//                        values.add(coef);
//                        return coef;
//                    }
//                    numberOfNeighbors = node.getNumberOfDisjunctNeighbors(type2);
//                    if (useHeuristic && numberOfNeighbors > maxNeighbours) {
//                        coef = 0.0;
//                        values.add(coef);
//                        return coef;
//                    }
//                    for (N neighbor1 : node.getNeighbors(type1)) {
//                        for (N neighbor2 : node.getNeighbors(type2)) {
//                            if (neighbor1 != neighbor2) {
//                                Set<String> types = new HashSet<String>();
//                                types.addAll(neighbor1.getTypesAsSet());
//                                types.addAll(neighbor2.getTypesAsSet());
//                                // System.out.println(types);
//
//                                for (String type3 : types) {
//                                    // System.out.println("-----");
//                                    // System.out.println(type1);
//                                    // System.out.println(type2);
//                                    // System.out.println(type3);
//                                    if (!type1.equals(type3) && !type2.equals(type3)) {
//                                        numberOfPossibleConnections++;
//                                        if (neighbor1.hasNeighbor(neighbor2, type3)) {
//                                            // System.out.println(neighbor1);
//                                            // System.out.println(neighbor2);
//                                            // System.out.println(type3);
//                                            interConnected++;
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        if (numberOfPossibleConnections == 0) {
//            coef = 0.0;
//        } else {
//            coef = interConnected / (double) numberOfPossibleConnections;
//        }
//        values.add(coef);
//
//        return coef;
//    }
//
//    public <N> double calculateThirdDefinition(final Network<N> network, final
//    N node) {
//        long interConnected = 0;
//        long numberOfPossibleConnections = 0;
//        double coef = 0.0;
//        int numberOfNeighbors = 0;
//
//        for (String type1 : node.getTypesAsSet()) {
//            for (String type2 : node.getTypesAsSet()) {
//                if (!type1.equals(type2)) {
//                    numberOfNeighbors = node.getNumberOfDisjunctNeighbors(type1);
//                    if (useHeuristic && numberOfNeighbors > maxNeighbours) {
//                        coef = 0.0;
//                        values.add(coef);
//                        return coef;
//                    }
//                    numberOfNeighbors = node.getNumberOfDisjunctNeighbors(type2);
//                    if (useHeuristic && numberOfNeighbors > maxNeighbours) {
//                        coef = 0.0;
//                        values.add(coef);
//                        return coef;
//                    }
//                    for (N neighbor1 : node.getNeighbors(type1)) {
//                        for (N neighbor2 : node.getNeighbors(type2)) {
//                            if (neighbor1 != neighbor2) {
//                                Set<String> types = new HashSet<String>();
//                                types.addAll(neighbor1.getTypesAsSet());
//                                types.addAll(neighbor2.getTypesAsSet());
//                                for (String type3 : types) {
//                                    // System.out.println(neighbor1);
//                                    // System.out.println(neighbor2);
//                                    // System.out.println(type3);
//                                    numberOfPossibleConnections++;
//                                    if (neighbor1.hasNeighbor(neighbor2, type3)) {
//                                        interConnected++;
//                                        // System.out.println(neighbor1);
//                                        // System.out.println(neighbor2);
//                                        // System.out.println(type3);
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        if (numberOfPossibleConnections == 0) {
//            coef = 0.0;
//        } else {
//            coef = interConnected / (double) numberOfPossibleConnections;
//        }
//        values.add(coef);
//
//        return coef;
//    }

    @Override
    protected <N, T> void evaluateAll(GraphAdapter<N, T> adapter) {
        evaluateEveryNode(adapter);
        evaluate(adapter);
    }

    @Override
    public List<Map<String, Object>> getTsvMaps(String[] header) {
        final List<Map<String, Object>> values = new ArrayList<>();
        int index = 0;
        for (Double v : data.getValues()) {
            Map<String, Object> value = new HashMap<>();
            value.put(header[0], "TypedClusteringCoefficient");
            value.put(header[1], null);
            value.put(header[2], index);
            value.put(header[3], v);
            values.add(value);
            index++;
        }
        return values;
    }
}
