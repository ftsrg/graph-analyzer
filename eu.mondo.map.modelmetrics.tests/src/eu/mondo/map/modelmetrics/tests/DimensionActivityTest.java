package eu.mondo.map.modelmetrics.tests;

import static eu.mondo.map.base.tests.MapDataTesterUtil.checkKeysSize;
import static eu.mondo.map.base.tests.MapDataTesterUtil.checkValue;
import static eu.mondo.map.tests.model.ModelContext.dim1;
import static eu.mondo.map.tests.model.ModelContext.dim2;
import static eu.mondo.map.tests.model.ModelContext.dim3;

import java.util.function.Consumer;

import org.testng.annotations.Test;

import eu.mondo.map.base.data.MapData;
import eu.mondo.map.modelmetrics.impl.ModelMetrics;
import eu.mondo.map.tests.model.TestModelTypes;

public class DimensionActivityTest extends ModelMetricTest<MapData<String, Integer>> {

    @Override
    public ModelMetrics getMetric() {
	return ModelMetrics.DimensionActivity;
    }

    @Test
    @Override
    protected Object[] testCase(TestModelTypes modelType) {
	Consumer<MapData<String, Integer>> checker = (data) -> {
	};
	switch (modelType) {
	case Loop:
	    checker = (data) -> {
		checkKeysSize(1, data);
		checkValue(data, dim1, 1);
	    };
	    break;
	case Loop_2T:
	    checker = (data) -> {
		checkKeysSize(2, data);
		checkValue(data, dim1, 1);
		checkValue(data, dim2, 1);
		checkValue(data, dim3, null);
	    };
	    break;
	case Motif3N_1:
	case Motif3N_2:
	case Motif3N_3:
	case Motif3N_4:
	case Motif3N_5:
	case Motif3N_6:
	case Motif3N_7:
	case Motif3N_8:
	case Motif3N_9:
	case Motif3N_10:
	case Motif3N_11:
	case Motif3N_12:
	case Motif3N_13:
	    checker = (data) -> {
		checkKeysSize(1, data);
		checkValue(data, dim1, 3);
		checkValue(data, dim2, null);
		checkValue(data, dim3, null);
	    };
	    break;
	case Motif3N_3_2T:
	case Motif3N_6_2T:
	case Motif3N_7_2T:
	case Motif3N_10_2T:
	case Motif3N_11_2T:
	    checker = (data) -> {
		checkKeysSize(2, data);
		checkValue(data, dim1, 3);
		checkValue(data, dim2, 2);
		checkValue(data, dim3, null);
	    };
	    break;
	case Motif3N_8_2T:
	case Motif3N_12_2T:
	case Motif3N_13_2T:
	    checker = (data) -> {
		checkKeysSize(2, data);
		checkValue(data, dim1, 3);
		checkValue(data, dim2, 3);
		checkValue(data, dim3, null);
	    };
	    break;
	case Motif5N_1_3T:
	    checker = (data) -> {
		checkKeysSize(3, data);
		checkValue(data, dim1, 3);
		checkValue(data, dim2, 4);
		checkValue(data, dim3, 2);
	    };
	    break;
	case Motif5N_2_3T:
	    checker = (data) -> {
		checkKeysSize(3, data);
		checkValue(data, dim1, 4);
		checkValue(data, dim2, 5);
		checkValue(data, dim3, 4);
	    };
	    break;
	default:
	    skippedModel(modelType);
	    break;
	}
	return new Object[] { modelType, checker };
    }

}
