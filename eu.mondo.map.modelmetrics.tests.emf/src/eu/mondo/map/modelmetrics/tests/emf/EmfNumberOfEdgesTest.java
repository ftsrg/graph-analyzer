package eu.mondo.map.modelmetrics.tests.emf;

import eu.mondo.map.modelmetrics.tests.NumberOfEdgesTest;
import eu.mondo.map.tests.model.TestModelTypes;

public class EmfNumberOfEdgesTest extends NumberOfEdgesTest {

    @Override
    protected void initModel(TestModelTypes modelType) {
	testModel = modelType.init();
	adapter = EmfAdapterInitializer.getAdapter(testModel);
    }

}
