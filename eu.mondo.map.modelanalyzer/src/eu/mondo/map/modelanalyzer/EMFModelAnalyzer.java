package eu.mondo.map.modelanalyzer;

import java.io.IOException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import eu.mondo.map.emf.EMFNetworkFactory;

public abstract class EMFModelAnalyzer extends ModelAnalyzer<EObject> {

	protected boolean containmentOnly;

	public EMFModelAnalyzer(final String modelName, final int sampleSize, final boolean containmentOnly) {
		super(modelName, sampleSize);
		this.containmentOnly = containmentOnly;
	}

	protected Resource resource;

	public void initNetwork() {
		network = EMFNetworkFactory.createNetwork(resource.getAllContents(), containmentOnly);
	}

	@Override
	protected void initModel() throws IOException {

	}

	@Override
	protected String getPostfix() {
		return containmentOnly ? "-2d" : "-nd";
	}

}
