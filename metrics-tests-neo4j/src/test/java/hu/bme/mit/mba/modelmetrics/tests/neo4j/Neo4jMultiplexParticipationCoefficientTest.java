package hu.bme.mit.mba.modelmetrics.tests.neo4j;

import hu.bme.mit.mba.modelmetrics.tests.MultiplexParticipationCoefficientTest;
import hu.bme.mit.mba.tests.model.TestGraphInstances;

public class Neo4jMultiplexParticipationCoefficientTest extends MultiplexParticipationCoefficientTest {

    @Override
    protected void initModel(TestGraphInstances modelType) {
        testGraph = modelType.init();
        adapter = Neo4jAdapterInitializer.getAdapter(testGraph);
    }

}
