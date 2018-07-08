package hu.bme.mit.mba.modelmetrics.tests.csv;

import hu.bme.mit.mba.modelmetrics.tests.NumberOfEdgesTest;
import hu.bme.mit.mba.tests.model.TestGraphInstances;

import java.io.IOException;

public class CsvNumberOfEdgesTest extends NumberOfEdgesTest {

    @Override
    protected void initModel(TestGraphInstances modelType) throws IOException {
        testGraph = modelType.init();
        adapter = CsvAdapterInitializer.getAdapter(testGraph);
    }

}
