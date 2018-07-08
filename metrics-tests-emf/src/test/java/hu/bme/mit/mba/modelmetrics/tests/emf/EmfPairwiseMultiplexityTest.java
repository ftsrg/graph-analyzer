package hu.bme.mit.mba.modelmetrics.tests.emf;

import hu.bme.mit.mba.modelmetrics.tests.PairwiseMultiplexityTest;
import hu.bme.mit.mba.tests.model.TestGraphInstances;

public class EmfPairwiseMultiplexityTest extends PairwiseMultiplexityTest {

    @Override
    protected void initModel(TestGraphInstances modelType) {
        testGraph = modelType.init();
        adapter = EmfAdapterInitializer.getAdapter(testGraph);
    }

}
