package hu.bme.mit.mba.modelmetrics.tests.emf;

import hu.bme.mit.mba.modelmetrics.tests.NumberOfNodesTest;
import hu.bme.mit.mba.tests.model.TestModelTypes;

public class EmfNumberOfNodesTest extends NumberOfNodesTest {

    @Override
    protected void initModel(TestModelTypes modelType) {
	testModel = modelType.init();
	adapter = EmfAdapterInitializer.getAdapter(testModel);
    }

}
