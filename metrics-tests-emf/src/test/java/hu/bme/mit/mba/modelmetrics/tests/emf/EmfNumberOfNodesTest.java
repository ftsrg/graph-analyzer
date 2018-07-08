package hu.bme.mit.mba.modelmetrics.tests.emf;

import hu.bme.mit.mba.modelmetrics.tests.NumberOfNodesTest;
import hu.bme.mit.mba.tests.model.TestGraphInstances;

public class EmfNumberOfNodesTest extends NumberOfNodesTest {

    @Override
    protected void initModel(TestGraphInstances modelType) {
        testGraph = modelType.init();
        adapter = EmfAdapterInitializer.getAdapter(testGraph);
    }

}
