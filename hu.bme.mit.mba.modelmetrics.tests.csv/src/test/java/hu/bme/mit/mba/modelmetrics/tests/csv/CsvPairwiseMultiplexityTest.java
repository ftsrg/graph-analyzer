package hu.bme.mit.mba.modelmetrics.tests.csv;

import hu.bme.mit.mba.modelmetrics.tests.PairwiseMultiplexityTest;
import hu.bme.mit.mba.tests.model.TestModelTypes;

public class CsvPairwiseMultiplexityTest extends PairwiseMultiplexityTest {

    @Override
    protected void initModel(TestModelTypes modelType) {
        testModel = modelType.init();
        adapter = CsvAdapterInitializer.getAdapter(testModel);
    }

}
