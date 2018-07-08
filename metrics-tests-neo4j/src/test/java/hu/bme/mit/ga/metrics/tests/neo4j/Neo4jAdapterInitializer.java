package hu.bme.mit.ga.metrics.tests.neo4j;

import hu.bme.mit.ga.adapters.neo4j.Neo4jGraphAdapter;
import hu.bme.mit.ga.tests.graph.TestGraph;
import hu.bme.mit.ga.tests.graph.neo4j.TestGraphToNeo4jConverter;
import org.neo4j.graphdb.GraphDatabaseService;

public class Neo4jAdapterInitializer {

    public static Neo4jGraphAdapter getAdapter(TestGraph testGraph) {
        GraphDatabaseService graph = new TestGraphToNeo4jConverter().convert(testGraph);
        Neo4jGraphAdapter adapter = new Neo4jGraphAdapter();
        adapter.init(graph);
        return adapter;
    }

}
