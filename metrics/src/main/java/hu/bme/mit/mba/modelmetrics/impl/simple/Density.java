package hu.bme.mit.mba.modelmetrics.impl.simple;

import hu.bme.mit.mba.base.data.ScalarData;
import hu.bme.mit.mba.modeladapters.GraphAdapter;
import hu.bme.mit.mba.modelmetrics.AbstractGraphMetric;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Density extends AbstractGraphMetric<ScalarData<Double>> {

    public Density() {
        super("Density", new ScalarData<>());
    }

    @Override
    protected <N, T> void evaluateAll(GraphAdapter<N, T> adapter) {
        int numOfNodes = adapter.getIndexer().getNumberOfNodes();
        Double value = adapter.getIndexer().getNumberOfEdges() / (double) numOfNodes;
        value /= numOfNodes - 1;

        data.setValue(value);
    }

    @Override
    public List<Map<String, Object>> getTsvMaps(String[] header) {
        final List<Map<String, Object>> values = new ArrayList<>();
        Map<String, Object> value = new HashMap<>();
        value.put(header[0], "Density");
        value.put(header[1], null);
        value.put(header[2], null);
        value.put(header[3], data.getValue());
        values.add(value);
        return values;
    }

}
