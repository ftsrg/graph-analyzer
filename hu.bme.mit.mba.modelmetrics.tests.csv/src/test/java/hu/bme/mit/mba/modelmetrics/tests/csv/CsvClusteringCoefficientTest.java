package hu.bme.mit.mba.modelmetrics.tests.csv;

import hu.bme.mit.mba.modelmetrics.tests.ClusteringCoefficientTest;
import hu.bme.mit.mba.tests.model.TestModelTypes;

public class CsvClusteringCoefficientTest extends ClusteringCoefficientTest {

    @Override
    protected void initModel(TestModelTypes modelType) {
        testModel = modelType.init();
        adapter = CsvAdapterInitializer.getAdapter(testModel);
    }
}
