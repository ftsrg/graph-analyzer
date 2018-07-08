package hu.bme.mit.mba.modelmetrics.impl.typed;

import hu.bme.mit.mba.base.data.ListData;
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

    @Override
    public <N, T> void evaluate(ModelAdapter<N, T> adapter, N element) {
        int numOfDimensions = 0;
        numOfDimensions = adapter.getIndexer().getNumberOfTypes();

        double coef = 0.0;
        if (numOfDimensions == 1) {
            coef = 0.0;
        } else {
            for (T type : adapter.getIndexer().getDimensions(element)) {
                int dimDegree = adapter.getIndexer().getDegree(element, type);
                int degree = adapter.getIndexer().getDegree(element);
                coef += Math.pow(dimDegree / (double) degree, 2.0);
            }
            coef = 1 - coef;
            coef = coef * numOfDimensions / (numOfDimensions - 1);
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
