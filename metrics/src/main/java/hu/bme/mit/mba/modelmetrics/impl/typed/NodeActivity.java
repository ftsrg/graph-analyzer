package hu.bme.mit.mba.modelmetrics.impl.typed;

import hu.bme.mit.mba.base.data.ListData;
import hu.bme.mit.mba.modeladapters.ModelAdapter;
import hu.bme.mit.mba.modelmetrics.AbstractModelMetric;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NodeActivity extends AbstractModelMetric<ListData<Integer>> {

    public NodeActivity() {
        super("NodeActivityList", new ListData<>());
    }

    @Override
    protected <N, T> void evaluateAll(ModelAdapter<N, T> adapter) {
        evaluateEveryNode(adapter);
    }

    public <N, T> void evaluate(ModelAdapter<N, T> adapter, N element) {
        int numberOfTypes = adapter.getIndexer().getNumberOfTypes(element);
        data.add(numberOfTypes);
    }

    @Override
    public List<Map<String, Object>> getTsvMaps(String[] header) {
        final List<Map<String, Object>> values = new ArrayList<>();
        int i = 0;
        for (Integer value : data.getValues()) {
            Map<String, Object> row = new HashMap<>();
            row.put(header[0], "NodeActivityList");
            row.put(header[1], null);
            row.put(header[2], i);
            row.put(header[3], value);
            values.add(row);
            i++;
        }
        return values;
    }
}
