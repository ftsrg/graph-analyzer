package hu.bme.mit.mba.modelmetrics.impl.typed;

import hu.bme.mit.mba.base.data.ListData;
import hu.bme.mit.mba.modeladapters.ModelAdapter;
import hu.bme.mit.mba.modelmetrics.AbstractModelMetric;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DimensionalDegreeEntropy extends AbstractModelMetric<ListData<Double>> {
    public DimensionalDegreeEntropy() {
        super("DimensionalDegreeEntropy", new ListData<Double>());
    }

    @Override
    protected <N, T> void evaluateAll(ModelAdapter<N, T> adapter) {
        for (N element : adapter.getNodes()) {
            evaluate(adapter, element);
        }

    }

    @Override
    public <N, T> void evaluate(ModelAdapter<N, T> adapter, N element) {
        double dde = 0;
        double overallDegree = adapter.getDegree(element);
        for(T type: adapter.getTypes(element)){
            double ratio = adapter.getDegree(element, type) / overallDegree;
            if(ratio!=0){
            dde += ratio * Math.log(1/ratio);
            }
        }
        data.add(dde);
    }


    @Override
    public List<Map<String, Object>> getTsvMaps(String[] header) {
        final List<Map<String, Object>> values = new ArrayList<>();
        int index = 0;
        for (Double value : data.getValues()) {
            Map<String, Object> row = new HashMap<>();
            row.put(header[0], "DimensionalDegreeEntropy");
            row.put(header[1], null);
            row.put(header[2], index);
            row.put(header[3], value);
            values.add(row);
            index += 1;
        }
        return values;
    }

}