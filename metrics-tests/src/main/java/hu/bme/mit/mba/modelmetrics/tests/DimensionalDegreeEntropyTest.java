package hu.bme.mit.mba.modelmetrics.tests;

import hu.bme.mit.mba.base.data.ListData;
import hu.bme.mit.mba.modelmetrics.impl.ModelMetricsEnum;
import hu.bme.mit.mba.tests.model.TestModelTypes;

import java.util.function.Consumer;

import static hu.bme.mit.mba.base.testutils.ListDataTesterUtil.checkAppearance;
import static hu.bme.mit.mba.base.testutils.ListDataTesterUtil.checkSize;

public class DimensionalDegreeEntropyTest extends ModelMetricTest<ListData<Double>> {

    @Override
    public ModelMetricsEnum getMetric() {
        return ModelMetricsEnum.DimensionalDegreeEntropy;
    }

    protected Object[] testCase(TestModelTypes modelType) {
        Consumer<ListData<Double>> checker = (data) -> {
        };
        switch (modelType) {
            case Loop_1T:
                checker = (data) -> {
                    checkSize(1, data);
                    checkAppearance(1, 0.0, data);
                };
                break;


            case Loop_2T:
                checker = (data) -> {
                    checkSize(1, data);
                    checkAppearance(1, Math.log(2), data);
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
                    checkSize(3, data);
                    checkAppearance(3, 0.0, data);
                };
                break;
            case Motif3N_7_2T:
            case Motif3N_3_2T:
                checker = (data) -> {
                    double n2 = 1.0 / 3.0 * Math.log(3.0) - 2.0 / 3.0 * Math.log(2.0 / 3.0);
                    checkSize(3, data);
                    checkAppearance(1, 0.0, data);
                    checkAppearance(1, n2, data);
                    checkAppearance(1, Math.log(2), data);
                };
                break;
            case Motif3N_10_2T:
            case Motif3N_6_2T:
            case Motif3N_11_2T:
                checker = (data) -> {
                    double n2 = 1.0 / 3.0 * Math.log(3.0) - 2.0 / 3.0 * Math.log(2.0 / 3.0);
                    checkSize(3, data);
                    checkAppearance(1, 0.0, data);
                    checkAppearance(2, n2, data);
                };
                break;
            case Motif3N_13_2T:
            case Motif3N_8_2T:
                checker = (data) -> {
                    checkSize(3, data);
                    checkAppearance(3, Math.log(2), data);
                };
                break;
            case Motif3N_12_2T:
                checker = (data) -> {
                    double n2 = 1.0 / 3.0 * Math.log(3.0) - 2.0 / 3.0 * Math.log(2.0 / 3.0);
                    checkSize(3, data);
                    checkAppearance(1, Math.log(2), data);
                    checkAppearance(2, n2, data);
                };
                break;

            case Motif5N_1_3T:
                checker = (data) -> {
                    double n2 = 1.0 / 3.0 * Math.log(3.0) - 2.0 / 3.0 * Math.log(2.0 / 3.0);
                    checkSize(5, data);
                    checkAppearance(1, 0.0, data);
                    checkAppearance(3, Math.log(2), data);
                    checkAppearance(1, n2, data);
                };
                break;
            case Motif5N_2_3T:
                checker = (data) -> {
                    checkSize(5, data);
                    checkAppearance(2, Math.log(2), data);
                    checkAppearance(2, 1.0 / 3.0 * Math.log(3) * 3, data);
                    checkAppearance(1, Math.log(2) * 1.5, data);
                };
                break;
            default:
                skippedModel(modelType);
        }
        return new Object[]{modelType, checker};
    }

}
