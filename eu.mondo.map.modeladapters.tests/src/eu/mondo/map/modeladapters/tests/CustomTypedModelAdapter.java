package eu.mondo.map.modeladapters.tests;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import eu.mondo.map.modeladapters.ModelIndexer;
import eu.mondo.map.modeladapters.TypedModelAdapter;
import eu.mondo.map.tests.model.TestModel;

public class CustomTypedModelAdapter extends TypedModelAdapter<String, String> {

    private TestModel model;

    public void init(TestModel model) {
	this.model = model;
	indexer = new ModelIndexer<String, String>();
	Map<String, Map<String, Set<String>>> values = model.getAdjacency().rowMap();
	for (String source : values.keySet()) {
	    for (String target : values.get(source).keySet()) {
		for (String type : values.get(source).get(target)) {
		    indexer.addEdge(type, source, target);
		}
	    }
	}
    }

    @Override
    public Iterator<String> getModelIterator() {
	return model.getNodeIterator();
    }

}
