package hu.bme.mit.mba.modelmetrics.tests.emf;

import hu.bme.mit.mba.modeladapters.emf.EmfGraphAdapter;
import hu.bme.mit.mba.tests.model.TestGraph;
import hu.bme.mit.mba.tests.model.emf.TestGraphToEmfConverter;
import hu.bme.mit.mba.tests.model.emf.network.NodeContainer;

public class EmfAdapterInitializer {

    public static EmfGraphAdapter getAdapter(TestGraph testGraph) {
        NodeContainer container = new TestGraphToEmfConverter().convert(testGraph);
        EmfGraphAdapter emfAdapter = new EmfGraphAdapter();
        emfAdapter.init(container);

        return emfAdapter;
    }

}
