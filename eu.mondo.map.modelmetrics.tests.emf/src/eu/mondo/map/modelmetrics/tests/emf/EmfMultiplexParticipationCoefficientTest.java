package eu.mondo.map.modelmetrics.tests.emf;

import eu.mondo.map.modelmetrics.tests.MultiplexParticipationCoefficientTest;
import eu.mondo.map.tests.model.TestModelTypes;

public class EmfMultiplexParticipationCoefficientTest extends MultiplexParticipationCoefficientTest {

    @Override
    protected void initModel(TestModelTypes modelType) {
	testModel = modelType.init();
	adapter = EmfAdapterInitializer.getAdapter(testModel);
    }

}
