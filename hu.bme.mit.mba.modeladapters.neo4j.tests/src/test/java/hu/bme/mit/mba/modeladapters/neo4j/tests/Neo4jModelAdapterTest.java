package hu.bme.mit.mba.modeladapters.neo4j.tests;

import java.util.HashMap;
import java.util.Map;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.test.TestGraphDatabaseFactory;

import hu.bme.mit.mba.modeladapters.ModelAdapter;
import hu.bme.mit.mba.modeladapters.TypedModelAdapter;
import hu.bme.mit.mba.modeladapters.neo4j.Neo4jModelAdapter;
import hu.bme.mit.mba.modeladapters.tests.ModelAdapterTest;
import hu.bme.mit.mba.tests.model.TestModel;
import hu.bme.mit.mba.tests.model.TestModelTypes;

public class Neo4jModelAdapterTest extends ModelAdapterTest {

    Neo4jModelAdapter neo4jAdapter;
    private final Map<String, Node> nodeMapping = new HashMap<>();

    @Override
    public void runTests(final TestModelTypes modelType, final Runnable checker) {
        GraphDatabaseService graph = new TestGraphDatabaseFactory().newImpermanentDatabase();

        final TestModel testModel = modelType.init();
//        final TestModelToNetworkConverter converter = new TestModelToNetworkConverter();
//        container = converter.convert(testModel);
//        nodeMapping = converter.getNodeMapping();
//
        neo4jAdapter = new Neo4jModelAdapter();
        neo4jAdapter.init(graph);
        checker.run();
    }

    @Override
    protected <N, T> void degree(final ModelAdapter<N, T> adapter, final N element, final int indegree, final int outdegree) {
        super.degree(neo4jAdapter, nodeMapping.get(element.toString()), indegree, outdegree);
    }

    @Override
    protected <N, T> void nodes(final ModelAdapter<N, T> adapter, final int expected) {
        super.nodes(neo4jAdapter, expected);
    }

    @Override
    protected <N, T> void edges(final ModelAdapter<N, T> adapter, final int expected) {
        super.edges(neo4jAdapter, expected);
    }

    @Override
    protected <N, T> void neighbor(final ModelAdapter<N, T> adapter, final N source, final N target) {
        super.neighbor(neo4jAdapter, nodeMapping.get(source.toString()), nodeMapping.get(target.toString()));
    }

    @Override
    protected <N, T> void degree(final TypedModelAdapter<N, T> adapter, final N element, final T type, final int indegree, final int outdegree) {
        super.degree(neo4jAdapter, nodeMapping.get(element), type.toString(), indegree, outdegree);
    }

    @Override
    protected <N, T> void neighbor(final TypedModelAdapter<N, T> adapter, final T type, final N source, final N target) {
        super.neighbor(neo4jAdapter, type.toString(), nodeMapping.get(source.toString()),
                nodeMapping.get(target.toString()));
    }

    @Override
    protected <N, T> void notNeighbor(final TypedModelAdapter<N, T> adapter, final T type, final N source, final N target) {
        super.notNeighbor(neo4jAdapter, type.toString(), nodeMapping.get(source.toString()),
                nodeMapping.get(target.toString()));
    }

    @Override
    protected <N, T> void types(final TypedModelAdapter<N, T> adapter, final int expected, final N node) {
        super.types(neo4jAdapter, expected, nodeMapping.get(node));
    }

    @Override
    protected <N, T> void types(final TypedModelAdapter<N, T> adapter, final int expected) {
        super.types(neo4jAdapter, expected);
    }

    @Override
    protected <N, T> void nodes(final TypedModelAdapter<N, T> adapter, final T type, final int expected) {
        super.nodes(neo4jAdapter, type.toString(), expected);
    }
}
