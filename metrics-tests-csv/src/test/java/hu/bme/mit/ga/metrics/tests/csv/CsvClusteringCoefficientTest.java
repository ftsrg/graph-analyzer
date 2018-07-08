package hu.bme.mit.ga.metrics.tests.csv;

import hu.bme.mit.ga.metrics.tests.ClusteringCoefficientTest;
import hu.bme.mit.ga.tests.graph.TestGraphInstances;

import java.io.IOException;

public class CsvClusteringCoefficientTest extends ClusteringCoefficientTest {

    @Override
    protected void initModel(TestGraphInstances modelType) throws IOException {
        testGraph = modelType.init();
        adapter = CsvAdapterInitializer.getAdapter(testGraph);
    }
}
