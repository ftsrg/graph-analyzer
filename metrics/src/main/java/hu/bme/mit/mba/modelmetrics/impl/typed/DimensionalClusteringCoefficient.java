package hu.bme.mit.mba.modelmetrics.impl.typed;

import hu.bme.mit.mba.base.data.ListData;
import hu.bme.mit.mba.modeladapters.ModelAdapter;
import hu.bme.mit.mba.modelmetrics.AbstractModelMetric;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DimensionalClusteringCoefficient extends AbstractModelMetric<ListData<Double>> {

    protected int maxNeighbours = 1000;
    protected boolean useHeuristic = false;

    public DimensionalClusteringCoefficient() {
        super("DimensionalClusteringCoefficient", new ListData<>());
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
    public <N, T> void evaluate(final ModelAdapter<N, T> adapter) {
        for (N node : adapter.getIndexer().getNodes()) {
            calculateFirstDefinition(adapter, node);
        }
    }

    //    public <N, T> void calculateSecondDefinition(final ModelAdapter<N, T> adapter) {
//        clear();
//        for (N node : adapter.getNodes()) {
//            calculateSecondDefinition(adapter, node);
//        }
//    }
    public <N, T> double calculateFirstDefinition(final ModelAdapter<N, T> adapter, final
    N node) {
        long interConnected = 0;
        long numberOfNeighbors = 0;
        long numberOfPossibleConnections = 0;
        double coef = 0.0;

        for (T dimension1 : adapter.getIndexer().getDimensions(node)) {
            numberOfNeighbors = adapter.getIndexer().getNeighbors(node, dimension1).size();
            if (useHeuristic && numberOfNeighbors > maxNeighbours) {
                coef = 0.0;
                data.add(coef);
                return coef;
            }
            for (N neighbor1 : adapter.getIndexer().getNeighbors(node, dimension1)) {
                for (N neighbor2 : adapter.getIndexer().getNeighbors(node, dimension1)) {
                    if (neighbor1 != neighbor2) {
                        Set<T> dimensions = new HashSet<>();
                        dimensions.addAll(adapter.getIndexer().getDimensions(neighbor1));
                        dimensions.addAll(adapter.getIndexer().getDimensions(neighbor1));
                        for (T dimension2 : dimensions) {
                            if (!dimension1.equals(dimension2)) {
                                numberOfPossibleConnections++;
                                if (adapter.getIndexer().isAdjacentUndirected(neighbor1, neighbor2, dimension2)) {
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

//    public <N, T> double calculateSecondDefinition(final ModelAdapter<N, T> adapter, final
//    N node) {
//        long interConnected = 0;
//        long numberOfPossibleConnections = 0;
//        double coef = 0.0;
//        int numberOfNeighbors = 0;
//        ArrayList<Double> values = new ArrayList<>();
//
//
//        for (T dimension1 : adapter.getTypes(node)) {
//            for (T dimension2 : adapter.getTypes(node)) {
//                if (!dimension1.equals(dimension2)) {
//                    numberOfNeighbors = adapter.getNeighbors(node, dimension1).size();
//                    if (useHeuristic && numberOfNeighbors > maxNeighbours) {
//                        coef = 0.0;
//                        values.add(coef);
//                        return coef;
//                    }
//                    numberOfNeighbors = node.getNumberOfDisjunctNeighbors(dimension2);
//                    if (useHeuristic && numberOfNeighbors > maxNeighbours) {
//                        coef = 0.0;
//                        values.add(coef);
//                        return coef;
//                    }
//                    for (N neighbor1 : node.getNeighbors(dimension1)) {
//                        for (N neighbor2 : node.getNeighbors(dimension2)) {
//                            if (neighbor1 != neighbor2) {
//                                Set<String> dimensions = new HashSet<String>();
//                                dimensions.addAll(neighbor1.getDimensionsAsSet());
//                                dimensions.addAll(neighbor2.getDimensionsAsSet());
//                                // System.out.println(dimensions);
//
//                                for (String dimension3 : dimensions) {
//                                    // System.out.println("-----");
//                                    // System.out.println(dimension1);
//                                    // System.out.println(dimension2);
//                                    // System.out.println(dimension3);
//                                    if (!dimension1.equals(dimension3) && !dimension2.equals(dimension3)) {
//                                        numberOfPossibleConnections++;
//                                        if (neighbor1.hasNeighbor(neighbor2, dimension3)) {
//                                            // System.out.println(neighbor1);
//                                            // System.out.println(neighbor2);
//                                            // System.out.println(dimension3);
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
//        for (String dimension1 : node.getDimensionsAsSet()) {
//            for (String dimension2 : node.getDimensionsAsSet()) {
//                if (!dimension1.equals(dimension2)) {
//                    numberOfNeighbors = node.getNumberOfDisjunctNeighbors(dimension1);
//                    if (useHeuristic && numberOfNeighbors > maxNeighbours) {
//                        coef = 0.0;
//                        values.add(coef);
//                        return coef;
//                    }
//                    numberOfNeighbors = node.getNumberOfDisjunctNeighbors(dimension2);
//                    if (useHeuristic && numberOfNeighbors > maxNeighbours) {
//                        coef = 0.0;
//                        values.add(coef);
//                        return coef;
//                    }
//                    for (N neighbor1 : node.getNeighbors(dimension1)) {
//                        for (N neighbor2 : node.getNeighbors(dimension2)) {
//                            if (neighbor1 != neighbor2) {
//                                Set<String> dimensions = new HashSet<String>();
//                                dimensions.addAll(neighbor1.getDimensionsAsSet());
//                                dimensions.addAll(neighbor2.getDimensionsAsSet());
//                                for (String dimension3 : dimensions) {
//                                    // System.out.println(neighbor1);
//                                    // System.out.println(neighbor2);
//                                    // System.out.println(dimension3);
//                                    numberOfPossibleConnections++;
//                                    if (neighbor1.hasNeighbor(neighbor2, dimension3)) {
//                                        interConnected++;
//                                        // System.out.println(neighbor1);
//                                        // System.out.println(neighbor2);
//                                        // System.out.println(dimension3);
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
    protected <N, T> void evaluateAll(ModelAdapter<N, T> adapter) {
        evaluateEveryNode(adapter);
        evaluate(adapter);
    }

    @Override
    public List<Map<String, Object>> getTsvMaps(String[] header) {
        final List<Map<String, Object>> values = new ArrayList<>();
        int index = 0;
        for (Double v : data.getValues()) {
            Map<String, Object> value = new HashMap<>();
            value.put(header[0], "DimensionalClusteringCoefficient");
            value.put(header[1], null);
            value.put(header[2], index);
            value.put(header[3], v);
            values.add(value);
            index++;
        }
        return values;
    }
}
