package hu.bme.mit.ga.metrics.tests.neo4j;

import hu.bme.mit.ga.metrics.tests.ClusteringCoefficientTest;
import hu.bme.mit.ga.tests.graph.TestGraphInstances;

public class Neo4jClusteringCoefficientTest extends ClusteringCoefficientTest {

    @Override
    protected void initModel(TestGraphInstances modelType) {
        testGraph = modelType.init();
        adapter = Neo4jAdapterInitializer.getAdapter(testGraph);
    }
}
