package eu.mondo.map.modelmetrics.tests.emf;

import eu.mondo.map.modelmetrics.tests.NumberOfNodesTest;
import eu.mondo.map.tests.model.TestModelTypes;

public class EmfNumberOfNodesTest extends NumberOfNodesTest {

    @Override
    protected void initModel(TestModelTypes modelType) {
	testModel = modelType.init();
	adapter = EmfAdapterInitializer.getAdapter(testModel);
    }

}
