package eu.mondo.map.modelmetrics.tests.emf;

import eu.mondo.map.modeladapters.emf.EmfModelAdapter;
import eu.mondo.map.tests.model.TestModel;
import eu.mondo.map.tests.model.emf.TestModelToNetworkConverter;
import eu.mondo.map.tests.model.emf.network.NodeContainer;

public class EmfAdapterInitializer {

    public static EmfModelAdapter getAdapter(TestModel testModel) {
	NodeContainer container = new TestModelToNetworkConverter().convert(testModel);
	EmfModelAdapter emfAdapter = new EmfModelAdapter();
	emfAdapter.init(container);

	return emfAdapter;
    }

}
