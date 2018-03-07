package hu.bme.mit.mba.modelmetrics.tests.neo4j;

import org.neo4j.graphdb.GraphDatabaseService;

import hu.bme.mit.mba.modeladapters.neo4j.Neo4jModelAdapter;
import hu.bme.mit.mba.tests.model.TestModel;
import hu.bme.mit.mba.tests.model.neo4j.Neo4jTestModelToNetworkConverter;

public class Neo4jAdapterInitializer {

	public static Neo4jModelAdapter getAdapter(TestModel testModel) {
		GraphDatabaseService graph = new Neo4jTestModelToNetworkConverter().convert(testModel);
		Neo4jModelAdapter adapter = new Neo4jModelAdapter(testModel.getDimensions());
		adapter.init(graph);
		return adapter;
	}

}
