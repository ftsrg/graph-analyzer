package eu.mondo.map.rdf.analyzer;

public class RDFAnalyzerMain {

	public static void main(String[] args) throws Exception {
		for (final String modelName : args) {
			final RDFModelAnalyzer analyzer = new RDFModelAnalyzer(modelName);
			analyzer.run();	
		}
	}

}
