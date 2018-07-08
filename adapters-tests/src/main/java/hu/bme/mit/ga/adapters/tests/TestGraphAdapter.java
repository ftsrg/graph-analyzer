package hu.bme.mit.ga.adapters.tests;

import hu.bme.mit.ga.adapters.GraphAdapter;
import hu.bme.mit.ga.tests.graph.TestGraph;

import java.util.Map;
import java.util.Set;

public class TestGraphAdapter extends GraphAdapter<String, String> {

    public void init(TestGraph model) {
        Map<String, Map<String, Set<String>>> values = model.getAdjacency().rowMap();
        for (String source : values.keySet()) {
            for (String target : values.get(source).keySet()) {
                for (String type : values.get(source).get(target)) {
                    indexer.addEdge(type, source, target);
                }
            }
        }
    }

}
