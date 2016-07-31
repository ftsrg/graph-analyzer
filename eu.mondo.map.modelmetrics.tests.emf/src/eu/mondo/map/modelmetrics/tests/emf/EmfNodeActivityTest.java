package eu.mondo.map.modelmetrics.tests.emf;

import eu.mondo.map.modelmetrics.tests.NodeActivityTest;
import eu.mondo.map.tests.model.TestModelTypes;

public class EmfNodeActivityTest extends NodeActivityTest {

    @Override
    protected void initModel(TestModelTypes modelType) {
	testModel = modelType.init();
	adapter = EmfAdapterInitializer.getAdapter(testModel);
    }

}
