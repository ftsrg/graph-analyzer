package hu.bme.mit.ga.metrics.tests.csv;

import hu.bme.mit.ga.metrics.tests.NumberOfEdgesTest;
import hu.bme.mit.ga.tests.graph.TestGraphInstances;

import java.io.IOException;

public class CsvNumberOfEdgesTest extends NumberOfEdgesTest {

    @Override
    protected void initModel(TestGraphInstances modelType) throws IOException {
        testGraph = modelType.init();
        adapter = CsvAdapterInitializer.getAdapter(testGraph);
    }

}
