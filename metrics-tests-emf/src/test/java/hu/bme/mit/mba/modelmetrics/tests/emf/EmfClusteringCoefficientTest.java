package hu.bme.mit.mba.modelmetrics.tests.emf;

import hu.bme.mit.mba.modelmetrics.tests.ClusteringCoefficientTest;
import hu.bme.mit.mba.tests.model.TestGraphInstances;

public class EmfClusteringCoefficientTest extends ClusteringCoefficientTest {

    @Override
    protected void initModel(TestGraphInstances modelType) {
        testGraph = modelType.init();
        adapter = EmfAdapterInitializer.getAdapter(testGraph);
    }
}
