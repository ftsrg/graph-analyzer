package eu.mondo.map.modelanalyzer;

import java.io.IOException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import eu.mondo.map.emf.EMFNetworkFactory;

public abstract class EMFModelAnalyzer extends ModelAnalyzer<EObject> {

	public EMFModelAnalyzer(final String modelName, final int sampleSize) {
		super(modelName, sampleSize);
	}

	protected Resource resource;

	public void initNetwork() {
		network = EMFNetworkFactory.createNetwork(resource.getAllContents());
	}

	@Override
	protected void initModel() throws IOException {

	}

}
