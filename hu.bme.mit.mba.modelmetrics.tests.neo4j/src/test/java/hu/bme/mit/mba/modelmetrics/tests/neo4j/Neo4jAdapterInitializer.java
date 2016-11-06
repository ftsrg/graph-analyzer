package hu.bme.mit.mba.modelmetrics.tests.neo4j;

import hu.bme.mit.mba.modeladapters.emf.EmfModelAdapter;
import hu.bme.mit.mba.tests.model.TestModel;
import hu.bme.mit.mba.tests.model.emf.TestModelToNetworkConverter;
import hu.bme.mit.mba.tests.model.emf.network.NodeContainer;

public class Neo4jAdapterInitializer {

    public static EmfModelAdapter getAdapter(TestModel testModel) {
        NodeContainer container = new TestModelToNetworkConverter().convert(testModel);
        EmfModelAdapter emfAdapter = new EmfModelAdapter();
        emfAdapter.init(container);

        return emfAdapter;
    }

}
