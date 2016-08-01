package eu.mondo.map.modelmetrics.tests;

import static org.testng.Assert.assertEquals;

import java.util.function.Consumer;

import eu.mondo.map.base.data.ScalarData;
import eu.mondo.map.modelmetrics.impl.ModelMetrics;
import eu.mondo.map.tests.model.TestModelTypes;

public class NumberOfNodesTest extends ModelMetricTest<ScalarData<Integer>> {

    @Override
    public ModelMetrics getMetric() {
	return ModelMetrics.NumberOfNodes;
    }

    @Override
    protected Object[] testCase(TestModelTypes modelType) {
	Consumer<ScalarData<Integer>> checker = null;
	switch (modelType) {
	case Loop:
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
