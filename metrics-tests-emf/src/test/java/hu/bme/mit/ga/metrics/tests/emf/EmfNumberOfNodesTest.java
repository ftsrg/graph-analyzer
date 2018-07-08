package hu.bme.mit.ga.metrics.tests.emf;

import hu.bme.mit.ga.metrics.tests.NumberOfNodesTest;
import hu.bme.mit.ga.tests.graph.TestGraphInstances;

public class EmfNumberOfNodesTest extends NumberOfNodesTest {

    @Override
    protected void initModel(TestGraphInstances modelType) {
        testGraph = modelType.init();
        adapter = EmfAdapterInitializer.getAdapter(testGraph);
    }

}
