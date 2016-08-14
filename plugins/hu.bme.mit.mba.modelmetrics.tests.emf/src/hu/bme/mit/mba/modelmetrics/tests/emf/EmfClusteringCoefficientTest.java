package hu.bme.mit.mba.modelmetrics.tests.emf;

import hu.bme.mit.mba.modelmetrics.tests.ClusteringCoefficientTest;
import hu.bme.mit.mba.tests.model.TestModelTypes;

public class EmfClusteringCoefficientTest extends ClusteringCoefficientTest {

    @Override
    protected void initModel(TestModelTypes modelType) {
        testModel = modelType.init();
        adapter = EmfAdapterInitializer.getAdapter(testModel);
    }
}
