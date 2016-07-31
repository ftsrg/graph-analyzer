package eu.mondo.map.modelmetrics.tests.emf;

import eu.mondo.map.modeladapters.emf.EmfModelAdapter;
import eu.mondo.map.modelmetrics.tests.ClusteringCoefficientTest;
import eu.mondo.map.tests.model.TestModelTypes;
import eu.mondo.map.tests.model.emf.TestModelToNetworkConverter;
import eu.mondo.map.tests.model.emf.network.NodeContainer;

public class EmfClusteringCoefficientTests extends ClusteringCoefficientTest {

    protected EmfModelAdapter emfAdapter;

    @Override
    protected void initModel(TestModelTypes modelType) {
	testModel = modelType.init();
	NodeContainer container = new TestModelToNetworkConverter().convert(testModel);

	emfAdapter = new EmfModelAdapter();
	emfAdapter.init(container);

	adapter = emfAdapter;
    }
}
