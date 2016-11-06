package hu.bme.mit.mba.modelmetrics.tests.neo4j;

import hu.bme.mit.mba.modelmetrics.tests.MultiplexParticipationCoefficientTest;
import hu.bme.mit.mba.tests.model.TestModelTypes;

public class Neo4jMultiplexParticipationCoefficientTest extends MultiplexParticipationCoefficientTest {

    @Override
    protected void initModel(TestModelTypes modelType) {
        testModel = modelType.init();
        adapter = Neo4jAdapterInitializer.getAdapter(testModel);
    }

}
