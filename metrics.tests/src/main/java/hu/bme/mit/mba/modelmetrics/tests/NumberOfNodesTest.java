package hu.bme.mit.mba.modelmetrics.tests;

import static org.testng.Assert.assertEquals;

import java.util.function.Consumer;

import hu.bme.mit.mba.base.data.ScalarData;
import hu.bme.mit.mba.modelmetrics.impl.ModelMetricsEnum;
import hu.bme.mit.mba.tests.model.TestModelTypes;

public class NumberOfNodesTest extends ModelMetricTest<ScalarData<Integer>> {

    @Override
    public ModelMetricsEnum getMetric() {
        return ModelMetricsEnum.NumberOfNodes;
    }

    @Override
    protected Object[] testCase(TestModelTypes modelType) {
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
        return new Object[] { modelType, checker };

    }

}