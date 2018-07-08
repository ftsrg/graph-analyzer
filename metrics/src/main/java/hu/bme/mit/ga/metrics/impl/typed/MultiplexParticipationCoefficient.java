package hu.bme.mit.ga.metrics.impl.typed;

import hu.bme.mit.ga.base.data.ListData;
import hu.bme.mit.ga.adapters.GraphAdapter;
import hu.bme.mit.ga.metrics.AbstractGraphMetric;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiplexParticipationCoefficient extends AbstractGraphMetric<ListData<Double>> {

    public MultiplexParticipationCoefficient() {
        super("MultiplexParticipationCoefficient", new ListData<>());
    }

    @Override
    protected <N, T> void evaluateAll(GraphAdapter<N, T> adapter) {
        evaluateEveryNode(adapter);
    }

    @Override
    public <N, T> void evaluate(GraphAdapter<N, T> adapter, N element) {
        int numOfTypes = 0;
        numOfTypes = adapter.getIndexer().getNumberOfTypes();

        double coef = 0.0;
        if (numOfTypes == 1) {
            coef = 0.0;
        } else {
            for (T type : adapter.getIndexer().getTypes(element)) {
                int typeDegree = adapter.getIndexer().getDegree(element, type);
                int degree = adapter.getIndexer().getDegree(element);
                coef += Math.pow(typeDegree / (double) degree, 2.0);
            }
            coef = 1 - coef;
            coef = coef * numOfTypes / (numOfTypes - 1);
        }

        data.add(coef);
    }

    @Override
    public List<Map<String, Object>> getTsvMaps(String[] header) {
        final List<Map<String, Object>> values = new ArrayList<>();
        int i = 0;
        for (Double value : data.getValues()) {
            Map<String, Object> row = new HashMap<>();
            row.put(header[0], "MultiplexParticipationCoefficient");
            row.put(header[1], null);
            row.put(header[2], i);
            row.put(header[3], value);
            values.add(row);
            i++;
        }
        return values;
    }

}
