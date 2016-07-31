package eu.mondo.map.modelmetrics.tests;

import static eu.mondo.map.base.tests.ListDataTesterUtil.checkAppearance;

import java.util.function.Consumer;

import org.apache.log4j.Logger;

import eu.mondo.map.base.data.ListData;
import eu.mondo.map.modelmetrics.impl.ModelMetrics;
import eu.mondo.map.modelmetrics.impl.simple.ClusteringCoefficient;
import eu.mondo.map.tests.model.TestModelTypes;

public class ClusteringCoefficientTest extends ModelMetricTest<ListData<Double>, ClusteringCoefficient> {

    protected final Logger logger = Logger.getLogger(this.getClass());

    @Override
    public ModelMetrics getMetric() {
	return ModelMetrics.ClusteringCoefficient;
    }

    @Override
    public int getNumberOfEvaluatedNodes() {
	return metric.getData().size();
    }

    protected Object[] testCase(TestModelTypes modelType) {
	switch (modelType) {
	case Loop:
	case Loop_2T:
	    Consumer<ListData<Double>> checker = (data) -> {
		checkAppearance(1, 0.0, data);
	    };
	    return new Object[] { modelType, checker };
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
		checkAppearance(3, 0.0, data);
	    };
	    return new Object[] { modelType, checker };
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
		checkAppearance(3, 1.0, data);
	    };
	    return new Object[] { modelType, checker };
	default:
	    logger.warn("The model " + modelType + " is not evaluated");
	    return null;
	}
    }

}
