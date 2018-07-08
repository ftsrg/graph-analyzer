package hu.bme.mit.mba.modelmetrics.tests.emf;

import hu.bme.mit.mba.modelmetrics.tests.NodeActivityTest;
import hu.bme.mit.mba.tests.model.TestGraphInstances;

public class EmfNodeActivityTest extends NodeActivityTest {

    @Override
    protected void initModel(TestGraphInstances modelType) {
        testGraph = modelType.init();
        adapter = EmfAdapterInitializer.getAdapter(testGraph);
    }

}
