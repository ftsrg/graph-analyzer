package hu.bme.mit.mba.modelmetrics.tests.neo4j;

import hu.bme.mit.mba.modelmetrics.tests.NumberOfNodesTest;
import hu.bme.mit.mba.tests.model.TestGraphInstances;

public class Neo4jNumberOfNodesTest extends NumberOfNodesTest {

    @Override
    protected void initModel(TestGraphInstances modelType) {
        testGraph = modelType.init();
        adapter = Neo4jAdapterInitializer.getAdapter(testGraph);
    }

}
