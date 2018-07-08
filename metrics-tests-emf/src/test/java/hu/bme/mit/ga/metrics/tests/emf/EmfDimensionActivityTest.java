package hu.bme.mit.ga.metrics.tests.emf;

import hu.bme.mit.ga.metrics.tests.DimensionActivityTest;
import hu.bme.mit.ga.tests.graph.TestGraphInstances;

public class EmfDimensionActivityTest extends DimensionActivityTest {

    @Override
    protected void initModel(TestGraphInstances modelType) {
        testGraph = modelType.init();
        adapter = EmfAdapterInitializer.getAdapter(testGraph);
    }

}
