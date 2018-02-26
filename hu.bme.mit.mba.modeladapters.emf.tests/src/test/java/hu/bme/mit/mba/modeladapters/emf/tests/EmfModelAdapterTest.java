package hu.bme.mit.mba.modeladapters.emf.tests;

import java.util.HashMap;
import java.util.Map;

import hu.bme.mit.mba.modeladapters.ModelAdapter;
import hu.bme.mit.mba.modeladapters.emf.EmfModelProvider;
import hu.bme.mit.mba.modeladapters.tests.ModelAdapterTest;
import hu.bme.mit.mba.tests.model.TestModel;
import hu.bme.mit.mba.tests.model.TestModelTypes;
import hu.bme.mit.mba.tests.model.emf.EmfTestModelToNetworkConverter;
import hu.bme.mit.mba.tests.model.emf.network.Node;
import hu.bme.mit.mba.tests.model.emf.network.NodeContainer;

public class EmfModelAdapterTest extends ModelAdapterTest {

    private Map<String, Node> nodeMapping = new HashMap<>();
    private NodeContainer container;

    @Override
    public void runTests(TestModelTypes modelType, Runnable checker) {
        TestModel testModel = modelType.init();
        EmfTestModelToNetworkConverter converter = new EmfTestModelToNetworkConverter();
        container = converter.convert(testModel);
        nodeMapping = converter.getNodeMapping();

        adapter = new ModelAdapter();
        EmfModelProvider emfProvider = new EmfModelProvider(adapter);
        emfProvider.init(container);
        checker.run();
    }

    @Override
    protected <N, T> void degree(ModelAdapter adapter, N element, int indegree, int outdegree) {
        super.degree(adapter, nodeMapping.get(element.toString()), indegree, outdegree);
    }

    @Override
    protected <N, T> void nodes(ModelAdapter adapter, int expected) {
        super.nodes(adapter, expected);
    }

    @Override
    protected <N, T> void edges(ModelAdapter adapter, int expected) {
        super.edges(adapter, expected);
    }

    @Override
    protected <N, T> void neighbor(ModelAdapter adapter, N source, N target) {
        super.neighbor(adapter, nodeMapping.get(source.toString()), nodeMapping.get(target.toString()));
    }

    @Override
    protected <N, T> void degree(ModelAdapter adapter, N element, T type, int indegree, int outdegree) {
        super.degree(adapter, nodeMapping.get(element), type.toString(), indegree, outdegree);
    }

    @Override
    protected <N, T> void neighbor(ModelAdapter adapter, T type, N source, N target) {
        super.neighbor(adapter, type.toString(), nodeMapping.get(source.toString()),
                nodeMapping.get(target.toString()));
    }

    @Override
    protected <N, T> void notNeighbor(ModelAdapter adapter, T type, N source, N target) {
        super.notNeighbor(adapter, type.toString(), nodeMapping.get(source.toString()),
                nodeMapping.get(target.toString()));
    }

    @Override
    protected <N, T> void types(ModelAdapter adapter, int expected, N node) {
        super.types(adapter, expected, nodeMapping.get(node));
    }

    @Override
    protected <N, T> void types(ModelAdapter adapter, int expected) {
        super.types(adapter, expected);
    }

    @Override
    protected <N, T> void nodes(ModelAdapter adapter, T type, int expected) {
        super.nodes(adapter, type.toString(), expected);
    }
}
