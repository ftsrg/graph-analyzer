package eu.mondo.map.rdf.analyzer;

import java.io.File;
import java.io.IOException;

import org.openrdf.model.Resource;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFParseException;
import org.openrdf.sail.memory.MemoryStore;

import eu.mondo.map.common.Constants;
import eu.mondo.map.modelanalyzer.ModelAnalyzer;
import eu.mondo.map.rdf.RDFNetworkFactory;

public class RDFModelAnalyzer extends ModelAnalyzer<Resource> {

	public static final int SAMPLE_SIZE = 100;
	public static final String TRAINBENCHMARK_MODEL_DIR = Constants.MODEL_DIR;


	public RDFModelAnalyzer(String modelFile) {
		super(SAMPLE_SIZE);
		modelName = TRAINBENCHMARK_MODEL_DIR + modelFile;
	}
	
	public RDFModelAnalyzer(int sampleCount) {
		super(sampleCount);
	}

	protected RepositoryConnection connection;

	@Override
	protected void initModel() throws RDFParseException, RepositoryException, IOException {
		final SailRepository repository = new SailRepository(new MemoryStore());
		repository.initialize();
		connection = repository.getConnection();
		connection.add(new File(modelName), null, RDFFormat.TURTLE);
	}
	
	@Override
	public void initNetwork() throws RepositoryException {
		network = RDFNetworkFactory.createNetwork(connection);
	}
	

}
