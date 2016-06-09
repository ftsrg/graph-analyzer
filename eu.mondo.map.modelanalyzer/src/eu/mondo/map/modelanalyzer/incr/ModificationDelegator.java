package eu.mondo.map.modelanalyzer.incr;

import eu.mondo.map.modelanalyzer.ModelAnalyzer;

public class ModificationDelegator {

	protected ModelAnalyzer modelAnalyzer;

	public void newObject(Object newObject) {

	}

	public void newEdge(Object newEdge) {

	}

	public ModelAnalyzer getModelAnalyzer() {
		return modelAnalyzer;
	}

	public void setModelAnalyzer(ModelAnalyzer modelAnalyzer) {
		this.modelAnalyzer = modelAnalyzer;
	}

}
