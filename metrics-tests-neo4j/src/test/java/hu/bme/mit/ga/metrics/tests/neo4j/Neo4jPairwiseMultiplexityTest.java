package hu.bme.mit.ga.metrics.tests.neo4j;

import hu.bme.mit.ga.metrics.tests.PairwiseMultiplexityTest;
import hu.bme.mit.ga.tests.graph.TestGraphInstances;

public class Neo4jPairwiseMultiplexityTest extends PairwiseMultiplexityTest {

    @Override
    protected void initModel(TestGraphInstances modelType) {
        testGraph = modelType.init();
        adapter = Neo4jAdapterInitializer.getAdapter(testGraph);
    }

}
