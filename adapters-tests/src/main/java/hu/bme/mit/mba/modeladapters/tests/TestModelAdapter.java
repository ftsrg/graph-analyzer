package hu.bme.mit.mba.modeladapters.tests;

import hu.bme.mit.mba.modeladapters.ModelAdapter;
import hu.bme.mit.mba.tests.model.TestModel;

import java.util.Map;
import java.util.Set;

public class TestModelAdapter extends ModelAdapter<String, String> {

    public void init(TestModel model) {
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
