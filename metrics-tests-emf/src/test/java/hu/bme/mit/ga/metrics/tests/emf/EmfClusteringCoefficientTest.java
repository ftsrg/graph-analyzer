package hu.bme.mit.ga.metrics.tests.emf;

import hu.bme.mit.ga.metrics.tests.ClusteringCoefficientTest;
import hu.bme.mit.ga.tests.graph.TestGraphInstances;

public class EmfClusteringCoefficientTest extends ClusteringCoefficientTest {

    @Override
    protected void initModel(TestGraphInstances modelType) {
        testGraph = modelType.init();
        adapter = EmfAdapterInitializer.getAdapter(testGraph);
    }
}
