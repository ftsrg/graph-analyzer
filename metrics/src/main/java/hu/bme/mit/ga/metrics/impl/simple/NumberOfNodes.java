package hu.bme.mit.ga.metrics.impl.simple;

import hu.bme.mit.ga.base.data.ScalarData;
import hu.bme.mit.ga.adapters.GraphAdapter;
import hu.bme.mit.ga.metrics.AbstractGraphMetric;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberOfNodes extends AbstractGraphMetric<ScalarData<Integer>> {

    public NumberOfNodes() {
        super("NumberOfNodes", new ScalarData<>());
    }

    @Override
    protected <N, T> void evaluateAll(GraphAdapter<N, T> adapter) {
        data.setValue(adapter.getIndexer().getNumberOfNodes());
    }

    @Override
    public List<Map<String, Object>> getTsvMaps(String[] header) {
        final List<Map<String, Object>> values = new ArrayList<>();
        Map<String, Object> row = new HashMap<>();
        row.put(header[0], "NumberOfNodes");
        row.put(header[1], null);
        row.put(header[2], null);
        row.put(header[3], data.getValue());
        values.add(row);
        return values;
    }

}
