package hu.bme.mit.mba.modelmetrics.impl.typed;

import hu.bme.mit.mba.base.data.ListData;
import hu.bme.mit.mba.base.data.MapData;
import hu.bme.mit.mba.modeladapters.ModelAdapter;
import hu.bme.mit.mba.modelmetrics.AbstractModelMetric;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiplexParticipationCoefficient extends AbstractModelMetric<ListData<Double>> {

    public MultiplexParticipationCoefficient() {
        super("MultiplexParticipationCoefficient", new ListData<>());
    }

    @Override
    protected <N, T> void evaluateAll(ModelAdapter<N, T> adapter) {
        evaluateEveryNode(adapter);
    }

    // TODO delete later
    @Deprecated
    boolean exclusive = false;

    @Override
    public <N, T> void evaluate(ModelAdapter<N, T> adapter, N element) {
        int numOfDimensions = 0;
        if (exclusive) {
            numOfDimensions = adapter.getNumberOfTypes(element);
        } else {
            numOfDimensions = adapter.getNumberOfTypes();
        }

        double coef = 0.0;
        if (numOfDimensions == 1) {
            coef = 0.0;
        } else {
            for (T type : adapter.getTypes(element)) {
                int degree = adapter.getDegree(element, type);
                coef += Math.pow(adapter.getDegree(element, type) / (double) adapter.getDegree(element), 2.0);
            }
            coef = 1 - coef;
            coef = coef * numOfDimensions / (numOfDimensions - 1);
        }

        data.add(coef);
        updateTracing(element, coef);
    }

    @Override
    public <N, T> void trace() {
        tracing = new MapData<N, Double>();
    }

    @Override
    public <N, T> MapData<N, Double> getTracing() {
        return (MapData<N, Double>) tracing;
    }

    protected <N, T> void updateTracing(N node, Double value) {
        if (notNullTracing()) {
            getTracing().put(node, value);
        }
    }

    protected <N, T> void reevaluate(ModelAdapter<N, T> adapter, T type, N node) {
        if (!getTracing().containsKey(node)) {
            updateTracing(node, 0.0);
        } else {
            evaluate(adapter, node);
        }
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
