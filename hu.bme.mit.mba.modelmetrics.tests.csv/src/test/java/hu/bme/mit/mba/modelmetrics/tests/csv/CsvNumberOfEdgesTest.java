package hu.bme.mit.mba.modelmetrics.tests.csv;

import hu.bme.mit.mba.modelmetrics.tests.NumberOfEdgesTest;
import hu.bme.mit.mba.tests.model.TestModelTypes;

public class CsvNumberOfEdgesTest extends NumberOfEdgesTest {

    @Override
    protected void initModel(TestModelTypes modelType) {
        testModel = modelType.init();
        adapter = CsvAdapterInitializer.getAdapter(testModel);
    }

}
