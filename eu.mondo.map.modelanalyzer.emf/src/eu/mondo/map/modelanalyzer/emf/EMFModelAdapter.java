package eu.mondo.map.modelanalyzer.emf;

import java.util.Iterator;

import org.eclipse.emf.ecore.resource.Resource;

import eu.mondo.map.modelanalyzer.adapters.ModelAdapter;

public class EMFModelAdapter extends ModelAdapter<Resource> {

	@Override
	public Iterator<Object> getModelIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initModel(Resource model) {
		// TODO Auto-generated method stub

	}

	// @Override
	// protected void initModel() throws IOException {
	//
	// }

	// @Override
	// protected String getPostfix() {
	// return containmentOnly ? "-2d" : "-nd";
	// }

}
