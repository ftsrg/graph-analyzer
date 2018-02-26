package hu.bme.mit.mba.modelmetrics.tests.neo4j;

import org.neo4j.graphdb.GraphDatabaseService;

import hu.bme.mit.mba.modeladapters.ModelAdapter;
import hu.bme.mit.mba.modeladapters.neo4j.Neo4jModelProvider;
import hu.bme.mit.mba.tests.model.TestModel;
import hu.bme.mit.mba.tests.model.neo4j.Neo4jTestModelToNetworkConverter;

public class Neo4jAdapterInitializer {

    public static ModelAdapter getAdapter(TestModel testModel) {
        GraphDatabaseService graph = new Neo4jTestModelToNetworkConverter().convert(testModel);
        ModelAdapter adapter = new ModelAdapter();
        Neo4jModelProvider provider = new Neo4jModelProvider(adapter);
        provider.init(graph);
        return adapter;
    }

}
