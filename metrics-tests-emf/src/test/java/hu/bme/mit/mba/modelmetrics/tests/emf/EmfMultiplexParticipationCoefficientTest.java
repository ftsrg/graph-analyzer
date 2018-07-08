package hu.bme.mit.mba.modelmetrics.tests.emf;

import hu.bme.mit.mba.modelmetrics.tests.MultiplexParticipationCoefficientTest;
import hu.bme.mit.mba.tests.model.TestGraphInstances;

public class EmfMultiplexParticipationCoefficientTest extends MultiplexParticipationCoefficientTest {

    @Override
    protected void initModel(TestGraphInstances modelType) {
        testGraph = modelType.init();
        adapter = EmfAdapterInitializer.getAdapter(testGraph);
    }

}
