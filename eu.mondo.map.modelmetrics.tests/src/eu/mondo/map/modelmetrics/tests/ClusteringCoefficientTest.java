package eu.mondo.map.modelmetrics.tests;

import static eu.mondo.map.base.tests.ListDataTesterUtil.checkAppearance;
import static eu.mondo.map.base.tests.ListDataTesterUtil.checkSize;

import java.util.function.Consumer;

import eu.mondo.map.base.data.ListData;
import eu.mondo.map.modelmetrics.impl.ModelMetrics;
import eu.mondo.map.tests.model.TestModelTypes;

public class ClusteringCoefficientTest extends ModelMetricTest<ListData<Double>> {

    @Override
    public ModelMetrics getMetric() {
	return ModelMetrics.ClusteringCoefficient;
    }

    protected Object[] testCase(TestModelTypes modelType) {
	Consumer<ListData<Double>> checker = (data) -> {
	};
	switch (modelType) {
	case Loop:
	case Loop_2T:
	    checker = (data) -> {
		checkSize(1, data);
		checkAppearance(1, 0.0, data);
	    };
	    break;
	case Motif3N_1:
	case Motif3N_2:
	case Motif3N_3:
	case Motif3N_3_2T:
	case Motif3N_4:
	case Motif3N_7:
	case Motif3N_7_2T:
	case Motif3N_8:
	case Motif3N_8_2T:
	    checker = (data) -> {
		checkSize(3, data);
		checkAppearance(3, 0.0, data);
	    };
	    break;
	case Motif3N_5:
	case Motif3N_6:
	case Motif3N_6_2T:
	case Motif3N_9:
	case Motif3N_10:
	case Motif3N_10_2T:
	case Motif3N_11:
	case Motif3N_11_2T:
	case Motif3N_12:
	case Motif3N_12_2T:
	case Motif3N_13:
	case Motif3N_13_2T:
	    checker = (data) -> {
		checkSize(3, data);
		checkAppearance(3, 1.0, data);
	    };
	    break;
	case Motif5N_1_3T:
	    checker = (data) -> {
		checkSize(5, data);
		checkAppearance(5, 0.0, data);
	    };
	    break;
	case Motif5N_2_3T:
	    checker = (data) -> {
		checkSize(5, data);
		checkAppearance(2, 0.0, data);
		checkAppearance(1, 1.0, data);
		checkAppearance(2, 1.0 / 3.0, data);
	    };
	    break;
	default:
	    skippedModel(modelType);
	}
	return new Object[] { modelType, checker };
    }

}
