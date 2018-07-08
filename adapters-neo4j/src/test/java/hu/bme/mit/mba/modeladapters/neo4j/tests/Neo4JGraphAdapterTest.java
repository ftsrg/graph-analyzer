package hu.bme.mit.mba.modeladapters.neo4j.tests;

import hu.bme.mit.mba.modeladapters.GraphAdapter;
import hu.bme.mit.mba.modeladapters.neo4j.Neo4JGraphAdapter;
import hu.bme.mit.mba.modeladapters.tests.GraphAdapterTest;
import hu.bme.mit.mba.tests.model.TestGraph;
import hu.bme.mit.mba.tests.model.TestGraphInstances;
import hu.bme.mit.mba.tests.model.neo4j.TestGraphToNeo4jConverter;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;

import java.util.Map;

public class Neo4JGraphAdapterTest extends GraphAdapterTest {

    private Neo4JGraphAdapter neo4jAdapter;
    private Map<String, Node> nodeMapping;

    @Override
    public void runTests(final TestGraphInstances modelType, final Runnable checker) {
        final TestGraph testGraph = modelType.init();
        final TestGraphToNeo4jConverter converter = new TestGraphToNeo4jConverter();
        GraphDatabaseService graph = converter.convert(testGraph);
        nodeMapping = converter.getNodeMapping();

        neo4jAdapter = new Neo4JGraphAdapter();
        neo4jAdapter.init(graph);
        checker.run();
    }

    @Override
    protected <N, T> void degree(final GraphAdapter<N, T> adapter, final N element, final int indegree, final int outdegree) {
        super.degree(neo4jAdapter, nodeMapping.get(element.toString()), indegree, outdegree);
    }

    @Override
    protected <N, T> void nodes(final GraphAdapter<N, T> adapter, final int expected) {
        super.nodes(neo4jAdapter, expected);
    }

    @Override
    protected <N, T> void edges(final GraphAdapter<N, T> adapter, final int expected) {
        super.edges(neo4jAdapter, expected);
    }

    @Override
    protected <N, T> void neighbor(final GraphAdapter<N, T> adapter, final N source, final N target) {
        super.neighbor(neo4jAdapter, nodeMapping.get(source.toString()), nodeMapping.get(target.toString()));
    }

    @Override
    protected <N, T> void degree(final GraphAdapter<N, T> adapter, final N element, final T type, final int indegree, final int outdegree) {
        super.degree(neo4jAdapter, nodeMapping.get(element), type.toString(), indegree, outdegree);
    }

    @Override
    protected <N, T> void neighbor(final GraphAdapter<N, T> adapter, final T type, final N source, final N target) {
        super.neighbor(neo4jAdapter, type.toString(), nodeMapping.get(source.toString()),
            nodeMapping.get(target.toString()));
    }

    @Override
    protected <N, T> void notNeighbor(final GraphAdapter<N, T> adapter, final T type, final N source, final N target) {
        super.notNeighbor(neo4jAdapter, type.toString(), nodeMapping.get(source.toString()),
            nodeMapping.get(target.toString()));
    }

    @Override
    protected <N, T> void types(final GraphAdapter<N, T> adapter, final int expected, final N node) {
        super.types(neo4jAdapter, expected, nodeMapping.get(node));
    }

    @Override
    protected <N, T> void types(final GraphAdapter<N, T> adapter, final int expected) {
        super.types(neo4jAdapter, expected);
    }

    @Override
    protected <N, T> void nodes(final GraphAdapter<N, T> adapter, final T type, final int expected) {
        super.nodes(neo4jAdapter, type.toString(), expected);
    }
}
