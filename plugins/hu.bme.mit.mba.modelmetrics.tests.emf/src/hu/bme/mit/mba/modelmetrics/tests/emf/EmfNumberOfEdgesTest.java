package hu.bme.mit.mba.modelmetrics.tests.emf;

import hu.bme.mit.mba.modelmetrics.tests.NumberOfEdgesTest;
import hu.bme.mit.mba.tests.model.TestModelTypes;

public class EmfNumberOfEdgesTest extends NumberOfEdgesTest {

    @Override
    protected void initModel(TestModelTypes modelType) {
        testModel = modelType.init();
        adapter = EmfAdapterInitializer.getAdapter(testModel);
    }

}
