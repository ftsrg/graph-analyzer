package hu.bme.mit.mba.modelmetrics.tests.emf;

import hu.bme.mit.mba.modelmetrics.tests.MultiplexParticipationCoefficientTest;
import hu.bme.mit.mba.tests.model.TestModelTypes;

public class EmfMultiplexParticipationCoefficientTest extends MultiplexParticipationCoefficientTest {

    @Override
    protected void initModel(TestModelTypes modelType) {
	testModel = modelType.init();
	adapter = EmfAdapterInitializer.getAdapter(testModel);
    }

}
