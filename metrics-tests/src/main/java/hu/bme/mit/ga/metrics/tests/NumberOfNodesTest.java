package hu.bme.mit.ga.metrics.tests;

import hu.bme.mit.ga.base.data.ScalarData;
import hu.bme.mit.ga.metrics.impl.GraphMetricsEnum;
import hu.bme.mit.ga.tests.graph.TestGraphInstances;

import java.util.function.Consumer;

import static org.testng.Assert.assertEquals;

public class NumberOfNodesTest extends GraphMetricTest<ScalarData<Integer>> {

    @Override
    public GraphMetricsEnum getMetric() {
        return GraphMetricsEnum.NumberOfNodes;
    }

    @Override
    protected Object[] testCase(TestGraphInstances modelType) {
        Consumer<ScalarData<Integer>> checker = null;
        switch (modelType) {
            case Loop_1T:
            case Loop_2T:
                checker = (data) -> {
                    assertEquals(data.getValue().intValue(), 1);
                };
                break;
            case Motif5N_1_3T:
            case Motif5N_2_3T:
                checker = (data) -> {
                    assertEquals(data.getValue().intValue(), 5);
                };
                break;
            default:
                checker = (data) -> {
                    assertEquals(data.getValue().intValue(), 3);
                };
        }
        return new Object[]{modelType, checker};

    }

}
