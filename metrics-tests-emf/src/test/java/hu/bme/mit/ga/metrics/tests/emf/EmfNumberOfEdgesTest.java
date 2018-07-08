package hu.bme.mit.ga.metrics.tests.emf;

import hu.bme.mit.ga.metrics.tests.NumberOfEdgesTest;
import hu.bme.mit.ga.tests.graph.TestGraphInstances;

public class EmfNumberOfEdgesTest extends NumberOfEdgesTest {

    @Override
    protected void initModel(TestGraphInstances modelType) {
        testGraph = modelType.init();
        adapter = EmfAdapterInitializer.getAdapter(testGraph);
    }

}
