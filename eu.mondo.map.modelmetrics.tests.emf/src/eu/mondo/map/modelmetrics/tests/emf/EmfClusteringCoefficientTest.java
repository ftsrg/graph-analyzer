package eu.mondo.map.modelmetrics.tests.emf;

import eu.mondo.map.modelmetrics.tests.ClusteringCoefficientTest;
import eu.mondo.map.tests.model.TestModelTypes;

public class EmfClusteringCoefficientTest extends ClusteringCoefficientTest {

    @Override
    protected void initModel(TestModelTypes modelType) {
	testModel = modelType.init();
	adapter = EmfAdapterInitializer.getAdapter(testModel);
    }
}
