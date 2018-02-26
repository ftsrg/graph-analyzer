package hu.bme.mit.mba.modelmetrics.tests.emf;

import hu.bme.mit.mba.modeladapters.ModelAdapter;
import hu.bme.mit.mba.modeladapters.emf.EmfModelProvider;
import hu.bme.mit.mba.tests.model.TestModel;
import hu.bme.mit.mba.tests.model.emf.EmfTestModelToNetworkConverter;
import hu.bme.mit.mba.tests.model.emf.network.NodeContainer;

public class EmfAdapterInitializer {

    public static ModelAdapter getAdapter(TestModel testModel) {
        NodeContainer container = new EmfTestModelToNetworkConverter().convert(testModel);
        ModelAdapter adapter = new ModelAdapter();
        EmfModelProvider emfProvider = new EmfModelProvider(adapter);
        emfProvider.init(container);

        return adapter;
    }

}
