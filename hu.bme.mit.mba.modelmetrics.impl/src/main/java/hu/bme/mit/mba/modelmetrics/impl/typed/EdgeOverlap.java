package hu.bme.mit.mba.modelmetrics.impl.typed;

import com.google.common.collect.Multimap;
import hu.bme.mit.mba.base.data.MapData;
import hu.bme.mit.mba.base.data.MappedListData;
import hu.bme.mit.mba.modeladapters.ModelAdapter;
import hu.bme.mit.mba.modeladapters.ModelIndexer;
import hu.bme.mit.mba.modelmetrics.AbstractModelMetric;

import java.util.*;

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
        return null;
    }
}
