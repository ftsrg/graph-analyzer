package hu.bme.mit.mba.modelmetrics.tests.emf;

import hu.bme.mit.mba.modeladapters.emf.EmfModelAdapter;
import hu.bme.mit.mba.tests.model.TestModel;
import hu.bme.mit.mba.tests.model.emf.EmfTestModelToNetworkConverter;
import hu.bme.mit.mba.tests.model.emf.network.NodeContainer;

public class EmfAdapterInitializer {

    public static EmfModelAdapter getAdapter(TestModel testModel) {
        NodeContainer container = new EmfTestModelToNetworkConverter().convert(testModel);
        EmfModelAdapter emfAdapter = new EmfModelAdapter(testModel.getDimensions());
        emfAdapter.init(container);

        return emfAdapter;
    }

}
