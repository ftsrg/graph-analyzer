package hu.bme.mit.mba.modeladapters.tests;

import java.util.List;
import java.util.Map;
import java.util.Set;

import hu.bme.mit.mba.modeladapters.Triple;
import hu.bme.mit.mba.modeladapters.ModelIndexBuilder;
import hu.bme.mit.mba.modeladapters.ModelProvider;
import hu.bme.mit.mba.tests.model.TestModel;

public class CustomModelProvider implements ModelProvider<String, String> {

    private ModelIndexBuilder builder;

    public CustomModelProvider(ModelIndexBuilder builder) {
        this.builder = builder;
    }

    public void init(TestModel model) {
        Map<String, Map<String, Set<String>>> values = model.getAdjacency().rowMap();

        for (String source : values.keySet()) {
            for (String target : values.get(source).keySet()) {
                for (String type : values.get(source).get(target)) {
                    builder.build(new Triple<>(source, target, type));
                }
            }
        }
    }

    @Override
    public List<Triple<String, String>> getOperations() {
        // TODO Auto-generated method stub
        return null;
    }

}
