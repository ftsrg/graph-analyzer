package hu.bme.mit.mba.modelmetrics.impl.typed;

import com.google.common.collect.Multimap;
import hu.bme.mit.mba.base.data.MapData;
import hu.bme.mit.mba.modeladapters.ModelAdapter;
import hu.bme.mit.mba.modeladapters.ModelIndexer;
import hu.bme.mit.mba.modelmetrics.AbstractModelMetric;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EdgeOverlap extends AbstractModelMetric<MapData<String, Double>> {
    public EdgeOverlap() {
        super("EdgeOverlap", new MapData<String, Double>());
    }

    @Override
    protected <N, T> void evaluateAll(ModelAdapter<N, T> adapter) {
        for(T condType: adapter.getTypes()){
            for(T type: adapter.getTypes()){
                if(type != condType){
                evaluate(adapter, condType, type);
                }
            }
        }

    }
    private <T, N> void evaluate(ModelAdapter<N, T> adapter, T condType, T type) {
        ModelIndexer indexer = adapter.getIndexer();
        Multimap<N,N> edgesCondType = indexer.getOutgoing(condType);
        Double edges = (double) edgesCondType.entries().size();
        Multimap<N,N> typeOutgoing = indexer.getOutgoing(type);
        double intersection= 0.0;
        for(N n: edgesCondType.keySet()){
                Collection<N> nodes = edgesCondType.get(n);
                for(N n2: nodes){
                    if(typeOutgoing.containsEntry(n,n2)|typeOutgoing.containsEntry(n2,n)){
                        intersection += 1;
                    }

            }
        }
        data.put(getKey(type, condType), intersection / edges);
    }
    protected <T> String getKey(T firstType, T secondType) {
        return String.format("%s-%s", firstType.toString(), secondType.toString());
    }


    @Override
    public List<Map<String, Object>> getTsvMaps(String[] header) {
        final List<Map<String, Object>> values = new ArrayList<>();
        for (String type : data.getValues().keySet()) {
            Double value = data.getValues().get(type);
            Map<String, Object> row = new HashMap<>();
            row.put(header[0], "EdgeOverlap");
            row.put(header[1], type);
            row.put(header[2], null);
            row.put(header[3], value);
            values.add(row);
        }
        return values;
    }

}
