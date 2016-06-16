package eu.mondo.map.modeladapters.tests;

import java.util.Iterator;

import eu.mondo.map.modeladapters.ModelIndexer;
import eu.mondo.map.modeladapters.TypedModelAdapter;

public class CustomTypedModelAdapter extends TypedModelAdapter<String, String, String> {

	@Override
	public Iterator<String> getModelIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(String model) {
		indexer = new ModelIndexer<String, String>();
	}

}
