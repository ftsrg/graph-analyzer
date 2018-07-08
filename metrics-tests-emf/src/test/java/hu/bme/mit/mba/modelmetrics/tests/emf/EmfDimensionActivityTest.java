package hu.bme.mit.mba.modelmetrics.tests.emf;

import hu.bme.mit.mba.modelmetrics.tests.DimensionActivityTest;
import hu.bme.mit.mba.tests.model.TestGraphInstances;

public class EmfDimensionActivityTest extends DimensionActivityTest {

    @Override
    protected void initModel(TestGraphInstances modelType) {
        testGraph = modelType.init();
        adapter = EmfAdapterInitializer.getAdapter(testGraph);
    }

}
