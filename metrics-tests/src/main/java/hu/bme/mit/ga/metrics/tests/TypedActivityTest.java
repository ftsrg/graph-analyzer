package hu.bme.mit.ga.metrics.tests;

import hu.bme.mit.ga.base.data.MapData;
import hu.bme.mit.ga.metrics.impl.GraphMetricsEnum;
import hu.bme.mit.ga.tests.graph.TestGraphInstances;

import java.util.function.Consumer;

import static hu.bme.mit.ga.base.testutils.MapDataTesterUtil.checkKeysSize;
import static hu.bme.mit.ga.base.testutils.MapDataTesterUtil.checkValue;
import static hu.bme.mit.ga.tests.graph.TestGraphConstants.type1;
import static hu.bme.mit.ga.tests.graph.TestGraphConstants.type2;
import static hu.bme.mit.ga.tests.graph.TestGraphConstants.type3;

public class TypedActivityTest extends GraphMetricTest<MapData<String, Integer>> {

    @Override
    public GraphMetricsEnum getMetric() {
        return GraphMetricsEnum.TypedActivity;
    }

    @Override
    protected Object[] testCase(TestGraphInstances modelType) {
        Consumer<MapData<String, Integer>> checker = (data) -> {
        };
        switch (modelType) {
            case Loop_1T:
                checker = (data) -> {
                    checkKeysSize(1, data);
                    checkValue(data, type1, 1);
                };
                break;
            case Loop_2T:
                checker = (data) -> {
                    checkKeysSize(2, data);
                    checkValue(data, type1, 1);
                    checkValue(data, type2, 1);
                    checkValue(data, type3, null);
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
                    checkValue(data, type1, 3);
                    checkValue(data, type2, null);
                    checkValue(data, type3, null);
                };
                break;
            case Motif3N_3_2T:
            case Motif3N_6_2T:
            case Motif3N_7_2T:
            case Motif3N_10_2T:
            case Motif3N_11_2T:
                checker = (data) -> {
                    checkKeysSize(2, data);
                    checkValue(data, type1, 3);
                    checkValue(data, type2, 2);
                    checkValue(data, type3, null);
                };
                break;
            case Motif3N_8_2T:
            case Motif3N_12_2T:
            case Motif3N_13_2T:
                checker = (data) -> {
                    checkKeysSize(2, data);
                    checkValue(data, type1, 3);
                    checkValue(data, type2, 3);
                    checkValue(data, type3, null);
                };
                break;
            case Motif5N_1_3T:
                checker = (data) -> {
                    checkKeysSize(3, data);
                    checkValue(data, type1, 3);
                    checkValue(data, type2, 4);
                    checkValue(data, type3, 2);
                };
                break;
            case Motif5N_2_3T:
                checker = (data) -> {
                    checkKeysSize(3, data);
                    checkValue(data, type1, 4);
                    checkValue(data, type2, 5);
                    checkValue(data, type3, 4);
                };
                break;
            default:
                skippedModel(modelType);
                break;
        }
        return new Object[]{modelType, checker};
    }

}
