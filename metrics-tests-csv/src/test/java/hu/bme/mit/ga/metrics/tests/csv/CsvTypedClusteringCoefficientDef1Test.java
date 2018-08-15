package hu.bme.mit.ga.metrics.tests.csv;

import hu.bme.mit.ga.metrics.tests.TypedClusteringCoefficientDef1Test;
import hu.bme.mit.ga.tests.graph.TestGraphInstances;

import java.io.IOException;

public class CsvTypedClusteringCoefficientDef1Test extends TypedClusteringCoefficientDef1Test {

    @Override
    protected void initModel(TestGraphInstances modelType) throws IOException {
        testGraph = modelType.init();
        adapter = CsvAdapterInitializer.getAdapter(testGraph);
    }

}
