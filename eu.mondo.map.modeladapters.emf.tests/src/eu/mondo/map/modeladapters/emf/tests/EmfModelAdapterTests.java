package eu.mondo.map.modeladapters.emf.tests;

import java.util.HashMap;
import java.util.Map;

import eu.mondo.map.modeladapters.ModelAdapter;
import eu.mondo.map.modeladapters.TypedModelAdapter;
import eu.mondo.map.modeladapters.emf.EmfModelAdapter;
import eu.mondo.map.modeladapters.tests.ModelAdapterTests;
import eu.mondo.map.tests.model.TestModel;
import eu.mondo.map.tests.model.TestModelTypes;
import eu.mondo.map.tests.model.emf.TestModelToNetworkConverter;
import eu.mondo.map.tests.model.emf.network.Node;
import eu.mondo.map.tests.model.emf.network.NodeContainer;

public class EmfModelAdapterTests extends ModelAdapterTests {

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
	emfAdapter.init(container.eAllContents());
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

}
