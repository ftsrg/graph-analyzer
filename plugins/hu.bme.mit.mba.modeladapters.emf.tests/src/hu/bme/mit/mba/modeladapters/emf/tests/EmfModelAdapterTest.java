package hu.bme.mit.mba.modeladapters.emf.tests;

import java.util.HashMap;
import java.util.Map;

import hu.bme.mit.mba.modeladapters.ModelAdapter;
import hu.bme.mit.mba.modeladapters.TypedModelAdapter;
import hu.bme.mit.mba.modeladapters.emf.EmfModelAdapter;
import hu.bme.mit.mba.modeladapters.tests.ModelAdapterTest;
import hu.bme.mit.mba.tests.model.TestModel;
import hu.bme.mit.mba.tests.model.TestModelTypes;
import hu.bme.mit.mba.tests.model.emf.TestModelToNetworkConverter;
import hu.bme.mit.mba.tests.model.emf.network.Node;
import hu.bme.mit.mba.tests.model.emf.network.NodeContainer;

public class EmfModelAdapterTest extends ModelAdapterTest {

    EmfModelAdapter emfAdapter;
    private Map<String, Node> nodeMapping = new HashMap<>();
    private NodeContainer container;

    @Override
    public void runTests(TestModelTypes modelType, Runnable checker) {
        TestModel testModel = modelType.init();
        TestModelToNetworkConverter converter = new TestModelToNetworkConverter();
        container = converter.convert(testModel);
        nodeMapping = converter.getNodeMapping();

        emfAdapter = new EmfModelAdapter();
        emfAdapter.init(container);
        checker.run();
    }

    @Override
    protected <N, T> void degree(ModelAdapter<N, T> adapter, N element, int indegree, int outdegree) {
        super.degree(emfAdapter, nodeMapping.get(element.toString()), indegree, outdegree);
    }

    @Override
    protected <N, T> void nodes(ModelAdapter<N, T> adapter, int expected) {
        super.nodes(emfAdapter, expected);
    }

    @Override
    protected <N, T> void edges(ModelAdapter<N, T> adapter, int expected) {
        super.edges(emfAdapter, expected);
    }

    @Override
    protected <N, T> void neighbor(ModelAdapter<N, T> adapter, N source, N target) {
        super.neighbor(emfAdapter, nodeMapping.get(source.toString()), nodeMapping.get(target.toString()));
    }

    @Override
    protected <N, T> void degree(TypedModelAdapter<N, T> adapter, N element, T type, int indegree, int outdegree) {
        super.degree(emfAdapter, nodeMapping.get(element), type.toString(), indegree, outdegree);
    }

    @Override
    protected <N, T> void neighbor(TypedModelAdapter<N, T> adapter, T type, N source, N target) {
        super.neighbor(emfAdapter, type.toString(), nodeMapping.get(source.toString()),
                nodeMapping.get(target.toString()));
    }

    @Override
    protected <N, T> void notNeighbor(TypedModelAdapter<N, T> adapter, T type, N source, N target) {
        super.notNeighbor(emfAdapter, type.toString(), nodeMapping.get(source.toString()),
                nodeMapping.get(target.toString()));
    }

    @Override
    protected <N, T> void types(TypedModelAdapter<N, T> adapter, int expected, N node) {
        super.types(emfAdapter, expected, nodeMapping.get(node));
    }

    @Override
    protected <N, T> void types(TypedModelAdapter<N, T> adapter, int expected) {
        super.types(emfAdapter, expected);
    }

    @Override
    protected <N, T> void nodes(TypedModelAdapter<N, T> adapter, T type, int expected) {
        super.nodes(emfAdapter, type.toString(), expected);
    }
}
