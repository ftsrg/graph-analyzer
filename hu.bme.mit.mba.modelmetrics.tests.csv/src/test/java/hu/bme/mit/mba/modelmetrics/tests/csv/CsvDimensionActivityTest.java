package hu.bme.mit.mba.modelmetrics.tests.csv;

import hu.bme.mit.mba.modelmetrics.tests.DimensionActivityTest;
import hu.bme.mit.mba.tests.model.TestModelTypes;

public class CsvDimensionActivityTest extends DimensionActivityTest {

    @Override
    protected void initModel(TestModelTypes modelType) {
        testModel = modelType.init();
        adapter = CsvAdapterInitializer.getAdapter(testModel);
    }

}
