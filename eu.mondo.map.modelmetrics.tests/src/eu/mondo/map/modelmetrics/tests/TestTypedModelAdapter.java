package eu.mondo.map.modelmetrics.tests;

import java.util.Iterator;

import eu.mondo.map.modeladapters.ModelIndexer;
import eu.mondo.map.modeladapters.TypedModelAdapter;

public class TestTypedModelAdapter extends TypedModelAdapter<TestModel, String, String> {

	// @Override
	// public Iterator<String> getModelIterator() {
	// return model.getNodes().iterator();
	// }

	@Override
	public void init(TestModel model) {
		this.model = model;
		indexer = new ModelIndexer<String, String>();
		indexer.setAdjacency(model.getAdjacency());
	}

	@Override
	public Iterator<String> getModelIterator() {
		return model.getNodes().iterator();
	}

}
