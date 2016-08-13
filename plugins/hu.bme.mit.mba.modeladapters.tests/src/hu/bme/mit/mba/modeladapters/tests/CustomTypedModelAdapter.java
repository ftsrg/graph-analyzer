package hu.bme.mit.mba.modeladapters.tests;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import hu.bme.mit.mba.modeladapters.ModelIndexer;
import hu.bme.mit.mba.modeladapters.TypedModelAdapter;
import hu.bme.mit.mba.tests.model.TestModel;

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
