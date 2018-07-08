package hu.bme.mit.ga.metrics.tests.emf;

import hu.bme.mit.ga.metrics.tests.TypedActivityTest;
import hu.bme.mit.ga.tests.graph.TestGraphInstances;

public class EmfTypedActivityTest extends TypedActivityTest {

    @Override
    protected void initModel(TestGraphInstances modelType) {
        testGraph = modelType.init();
        adapter = EmfAdapterInitializer.getAdapter(testGraph);
    }

}
