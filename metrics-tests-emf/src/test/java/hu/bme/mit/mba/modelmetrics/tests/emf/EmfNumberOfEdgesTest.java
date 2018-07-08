package hu.bme.mit.mba.modelmetrics.tests.emf;

import hu.bme.mit.mba.modelmetrics.tests.NumberOfEdgesTest;
import hu.bme.mit.mba.tests.model.TestGraphInstances;

public class EmfNumberOfEdgesTest extends NumberOfEdgesTest {

    @Override
    protected void initModel(TestGraphInstances modelType) {
        testGraph = modelType.init();
        adapter = EmfAdapterInitializer.getAdapter(testGraph);
    }

}
