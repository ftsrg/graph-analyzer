package hu.bme.mit.ga.metrics.tests.emf;

import hu.bme.mit.ga.adapters.emf.EmfGraphAdapter;
import hu.bme.mit.ga.tests.graph.TestGraph;
import hu.bme.mit.ga.tests.graph.emf.TestGraphToEmfConverter;
import hu.bme.mit.ga.tests.graph.emf.network.NodeContainer;

public class EmfAdapterInitializer {

    public static EmfGraphAdapter getAdapter(TestGraph testGraph) {
        NodeContainer container = new TestGraphToEmfConverter().convert(testGraph);
        EmfGraphAdapter emfAdapter = new EmfGraphAdapter();
        emfAdapter.init(container);

        return emfAdapter;
    }

}
