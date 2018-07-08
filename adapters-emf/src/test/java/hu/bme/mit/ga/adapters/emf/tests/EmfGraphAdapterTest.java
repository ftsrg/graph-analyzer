package hu.bme.mit.ga.adapters.emf.tests;

import hu.bme.mit.ga.adapters.GraphAdapter;
import hu.bme.mit.ga.adapters.emf.EmfGraphAdapter;
import hu.bme.mit.ga.adapters.tests.GraphAdapterTest;
import hu.bme.mit.ga.tests.graph.TestGraph;
import hu.bme.mit.ga.tests.graph.TestGraphInstances;
import hu.bme.mit.ga.tests.graph.emf.TestGraphToEmfConverter;
import hu.bme.mit.ga.tests.graph.emf.network.Node;
import hu.bme.mit.ga.tests.graph.emf.network.NodeContainer;

import java.util.HashMap;
import java.util.Map;

public class EmfGraphAdapterTest extends GraphAdapterTest {

    private EmfGraphAdapter emfAdapter;
    private Map<String, Node> nodeMapping = new HashMap<>();
    private NodeContainer container;

    @Override
    public void runTests(TestGraphInstances modelType, Runnable checker) {
        TestGraph testGraph = modelType.init();
        TestGraphToEmfConverter converter = new TestGraphToEmfConverter();
        container = converter.convert(testGraph);
        nodeMapping = converter.getNodeMapping();

        emfAdapter = new EmfGraphAdapter();
        emfAdapter.init(container);
        checker.run();
    }

    @Override
    protected <N, T> void degree(GraphAdapter<N, T> adapter, N element, int indegree, int outdegree) {
        super.degree(emfAdapter, nodeMapping.get(element.toString()), indegree, outdegree);
    }

    @Override
    protected <N, T> void nodes(GraphAdapter<N, T> adapter, int expected) {
        super.nodes(emfAdapter, expected);
    }

    @Override
    protected <N, T> void edges(GraphAdapter<N, T> adapter, int expected) {
        super.edges(emfAdapter, expected);
    }

    @Override
    protected <N, T> void neighbor(GraphAdapter<N, T> adapter, N source, N target) {
        super.neighbor(emfAdapter, nodeMapping.get(source.toString()), nodeMapping.get(target.toString()));
    }

    @Override
    protected <N, T> void degree(GraphAdapter<N, T> adapter, N element, T type, int indegree, int outdegree) {
        super.degree(emfAdapter, nodeMapping.get(element), type.toString(), indegree, outdegree);
    }

    @Override
    protected <N, T> void neighbor(GraphAdapter<N, T> adapter, T type, N source, N target) {
        super.neighbor(emfAdapter, type.toString(), nodeMapping.get(source.toString()),
            nodeMapping.get(target.toString()));
    }

    @Override
    protected <N, T> void notNeighbor(GraphAdapter<N, T> adapter, T type, N source, N target) {
        super.notNeighbor(emfAdapter, type.toString(), nodeMapping.get(source.toString()),
            nodeMapping.get(target.toString()));
    }

    @Override
    protected <N, T> void types(GraphAdapter<N, T> adapter, int expected, N node) {
        super.types(emfAdapter, expected, nodeMapping.get(node));
    }

    @Override
    protected <N, T> void types(GraphAdapter<N, T> adapter, int expected) {
        super.types(emfAdapter, expected);
    }

    @Override
    protected <N, T> void nodes(GraphAdapter<N, T> adapter, T type, int expected) {
        super.nodes(emfAdapter, type.toString(), expected);
    }

}
