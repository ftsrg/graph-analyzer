package eu.mondo.map.modelmetrics.tests.emf;

import eu.mondo.map.modelmetrics.tests.DimensionActivityTest;
import eu.mondo.map.tests.model.TestModelTypes;

public class EmfDimensionActivityTest extends DimensionActivityTest {

    @Override
    protected void initModel(TestModelTypes modelType) {
	testModel = modelType.init();
	adapter = EmfAdapterInitializer.getAdapter(testModel);
    }

}
