package hu.bme.mit.ga.metrics.tests.csv;

import hu.bme.mit.ga.metrics.tests.EdgeOverlapTest;
import hu.bme.mit.ga.tests.graph.TestGraphInstances;

import java.io.IOException;

public class CsvEdgeOverlapTest extends EdgeOverlapTest {

    @Override
    protected void initModel(TestGraphInstances modelType) throws IOException {
        testGraph = modelType.init();
        adapter = CsvAdapterInitializer.getAdapter(testGraph);
    }

}
