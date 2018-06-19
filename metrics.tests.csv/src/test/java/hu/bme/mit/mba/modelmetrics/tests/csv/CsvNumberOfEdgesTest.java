package hu.bme.mit.mba.modelmetrics.tests.csv;

import hu.bme.mit.mba.modelmetrics.tests.NumberOfEdgesTest;
import hu.bme.mit.mba.tests.model.TestModelTypes;

import java.io.IOException;

public class CsvNumberOfEdgesTest extends NumberOfEdgesTest {

    @Override
    protected void initModel(TestModelTypes modelType) throws IOException {
        testModel = modelType.init();
        adapter = CsvAdapterInitializer.getAdapter(testModel);
    }

}