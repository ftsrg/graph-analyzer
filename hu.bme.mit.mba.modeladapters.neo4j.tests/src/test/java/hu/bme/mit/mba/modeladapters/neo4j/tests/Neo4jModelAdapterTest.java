package hu.bme.mit.mba.modeladapters.neo4j.tests;

import java.util.Map;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;

import hu.bme.mit.mba.modeladapters.ModelAdapter;
import hu.bme.mit.mba.modeladapters.neo4j.Neo4jModelProvider;
import hu.bme.mit.mba.modeladapters.tests.ModelAdapterTest;
import hu.bme.mit.mba.tests.model.TestModel;
import hu.bme.mit.mba.tests.model.TestModelTypes;
import hu.bme.mit.mba.tests.model.neo4j.Neo4jTestModelToNetworkConverter;

public class Neo4jModelAdapterTest extends ModelAdapterTest {

    private Map<String, Node> nodeMapping;

    @Override
    public void runTests(final TestModelTypes modelType, final Runnable checker) {
        final TestModel testModel = modelType.init();
        final Neo4jTestModelToNetworkConverter converter = new Neo4jTestModelToNetworkConverter();
        GraphDatabaseService graph = converter.convert(testModel);
        nodeMapping = converter.getNodeMapping();

        adapter = new ModelAdapter();
        Neo4jModelProvider modelProvider = new Neo4jModelProvider(adapter);
        modelProvider.init(graph);
        checker.run();
    }

    @Override
    protected <N, T> void degree(final ModelAdapter adapter, final N element, final int indegree, final int outdegree) {
        super.degree(adapter, nodeMapping.get(element.toString()), indegree, outdegree);
    }

    @Override
    protected <N, T> void nodes(final ModelAdapter adapter, final int expected) {
        super.nodes(adapter, expected);
    }

    @Override
    protected <N, T> void edges(final ModelAdapter adapter, final int expected) {
        super.edges(adapter, expected);
    }

    @Override
    protected <N, T> void neighbor(final ModelAdapter adapter, final N source, final N target) {
        super.neighbor(adapter, nodeMapping.get(source.toString()), nodeMapping.get(target.toString()));
    }

    @Override
    protected <N, T> void degree(final ModelAdapter adapter, final N element, final T type, final int indegree,
            final int outdegree) {
        super.degree(adapter, nodeMapping.get(element), type.toString(), indegree, outdegree);
    }

    @Override
    protected <N, T> void neighbor(final ModelAdapter adapter, final T type, final N source, final N target) {
        super.neighbor(adapter, type.toString(), nodeMapping.get(source.toString()),
                nodeMapping.get(target.toString()));
    }

    @Override
    protected <N, T> void notNeighbor(final ModelAdapter adapter, final T type, final N source, final N target) {
        super.notNeighbor(adapter, type.toString(), nodeMapping.get(source.toString()),
                nodeMapping.get(target.toString()));
    }

    @Override
    protected <N, T> void types(final ModelAdapter adapter, final int expected, final N node) {
        super.types(adapter, expected, nodeMapping.get(node));
    }

    @Override
    protected <N, T> void types(final ModelAdapter adapter, final int expected) {
        super.types(adapter, expected);
    }

    @Override
    protected <N, T> void nodes(final ModelAdapter adapter, final T type, final int expected) {
        super.nodes(adapter, type.toString(), expected);
    }
}
