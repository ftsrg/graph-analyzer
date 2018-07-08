package hu.bme.mit.ga.metrics.tests.emf;

import hu.bme.mit.ga.metrics.tests.PairwiseMultiplexityTest;
import hu.bme.mit.ga.tests.graph.TestGraphInstances;

public class EmfPairwiseMultiplexityTest extends PairwiseMultiplexityTest {

    @Override
    protected void initModel(TestGraphInstances modelType) {
        testGraph = modelType.init();
        adapter = EmfAdapterInitializer.getAdapter(testGraph);
    }

}
