package hu.bme.mit.mba.modelmetrics.impl.simple;

import hu.bme.mit.mba.base.data.ListData;
import hu.bme.mit.mba.base.data.MapData;
import hu.bme.mit.mba.modeladapters.ModelAdapter;
import hu.bme.mit.mba.modelmetrics.AbstractModelMetric;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClusteringCoefficient extends AbstractModelMetric<ListData<Double>> {

    public ClusteringCoefficient() {
        super("ClusteringCoefficientList", new ListData<>());
    }

    @Override
    public <N, T> void trace() {
        tracing = new MapData<N, Double>();
    }

    @Override
    protected <N, T> void evaluateAll(ModelAdapter<N, T> adapter) {
        evaluateEveryNode(adapter);
    }

    @Override
    public <N, T> void evaluate(ModelAdapter<N, T> adapter, N element) {
        long interConnected = 0;
        long numberOfNeighbors = 0;
        double clusteringCoef = 0.0;
        for (N neighbor1 : adapter.getNeighbors(element)) {
            for (N neighbor2 : adapter.getNeighbors(element)) {
                if (neighbor1 != neighbor2) {
                    if (adapter.isAdjacent(neighbor1, neighbor2)) {
                        interConnected++;
                    }
                }
            }
        }

        numberOfNeighbors = adapter.getNeighbors(element).size();
        if (numberOfNeighbors < 2) {
            clusteringCoef = 0.0;
        } else {
            clusteringCoef = interConnected / (double) (numberOfNeighbors * (numberOfNeighbors - 1));
        }
        data.add(clusteringCoef);

        if (tracing != null) {
            getTracing().put(element, clusteringCoef);
        }
    }

    @Override
    public <N, T> MapData<N, Double> getTracing() {
        return (MapData<N, Double>) tracing;
    }

    @Override
    public List<Map<String, Object>> getTsvMaps(String[] header) {
        final List<Map<String, Object>> values = new ArrayList<>();
        int index = 0;
        for (Double v : data.getValues()) {
            Map<String, Object> value = new HashMap<>();
            value.put(header[0], "ClusteringCoefficientList");
            value.put(header[1], null);
            value.put(header[2], index);
            value.put(header[3], v);
            values.add(value);
            index++;
        }
        return values;
    }

}
