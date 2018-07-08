package hu.bme.mit.mba.modelmetrics.tests.csv;

import hu.bme.mit.mba.modelmetrics.tests.PairwiseMultiplexityTest;
import hu.bme.mit.mba.tests.model.TestGraphInstances;

import java.io.IOException;

public class CsvPairwiseMultiplexityTest extends PairwiseMultiplexityTest {

    @Override
    protected void initModel(TestGraphInstances modelType) throws IOException {
        testGraph = modelType.init();
        adapter = CsvAdapterInitializer.getAdapter(testGraph);
    }

}
