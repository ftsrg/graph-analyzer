package eu.mondo.map.modelanalyzer;

import java.io.IOException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import eu.mondo.map.emf.EMFNetworkFactory;

public class EMFModelAnalyzer extends ModelAnalyzer<EObject> {

	protected Resource resource;

	public void initNetwork() {
		network = EMFNetworkFactory.createNetwork(resource.getAllContents());
	}

	@Override
	protected void initModel() throws IOException {
				
	}

}
