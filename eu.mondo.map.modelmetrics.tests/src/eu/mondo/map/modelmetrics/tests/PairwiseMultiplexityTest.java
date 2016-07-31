package eu.mondo.map.modelmetrics.tests;

import static eu.mondo.map.base.tests.MapDataTesterUtil.checkKeysSize;
import static eu.mondo.map.tests.model.ModelContext.dim1;
import static eu.mondo.map.tests.model.ModelContext.dim2;

import java.util.function.Consumer;

import org.testng.Assert;

import eu.mondo.map.base.data.MapData;
import eu.mondo.map.modelmetrics.impl.ModelMetrics;
import eu.mondo.map.modelmetrics.impl.typed.PairwiseMultiplexity;
import eu.mondo.map.tests.model.TestModelTypes;

public class PairwiseMultiplexityTest extends ModelMetricTest<MapData<String, Double>, PairwiseMultiplexity> {

    @Override
    public ModelMetrics getMetric() {
	return ModelMetrics.PairwiseMultiplexity;
    }

    @Override
    protected Object[] testCase(TestModelTypes modelType) {
	Consumer<MapData<String, Double>> checker = (data) -> {
	};
	switch (modelType) {
	case Loop:
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
		checkKeysSize(0, data);
	    };
	    break;
	case Loop_2T:
	case Motif3N_8_2T:
	case Motif3N_12_2T:
	case Motif3N_13_2T:
	    checker = (data) -> {
		checkKeysSize(1, data);
		check(data, dim1, dim2, 1.0);
	    };
	    break;
	case Motif3N_3_2T:
	case Motif3N_6_2T:
	case Motif3N_7_2T:
	case Motif3N_10_2T:
	case Motif3N_11_2T:
	    checker = (data) -> {
		checkKeysSize(1, data);
		check(data, dim1, dim2, 2.0 / 3.0);
	    };
	    break;
	default:
	    skippedModel(modelType);
	}
	return new Object[] { modelType, checker };
    }

    protected void check(MapData<String, Double> data, String firstType, String secondType, double expectedValue) {
	String key = getKey(firstType, secondType);
	if (data.getValues().containsKey(key)) {
	    Assert.assertEquals(data.get(key), expectedValue);
	} else {
	    String reversedKey = getKey(secondType, firstType);
	    Assert.assertEquals(data.get(reversedKey), expectedValue);
	}
    }

    protected String getKey(String first, String second) {
	return String.format("%s-%s", first, second);
    }

    //
    // private void evaluateMultiplexity() {
    // metric.evaluate(network, dim1, dim2);
    // metric.evaluate(network, dim1, dim3);
    // metric.evaluate(network, dim2, dim3);
    // metric.evaluateExclusive(network, dim1, dim2);
    // metric.evaluateExclusive(network, dim1, dim3);
    // metric.evaluateExclusive(network, dim2, dim3);
    // }
    //
    // @Test
    // public void zeroIntersection1() {
    // model.addEdge(dim1, node1, node2);
    // model.addEdge(dim2, node3, node4);
    // model.addEdge(dim3, node5, node6);
    //
    // evaluateMultiplexity();
    // checkMutiplexity(dim1, dim2, 0.0);
    // checkMutiplexity(dim1, dim3, 0.0);
    // checkMutiplexity(dim2, dim3, 0.0);
    // checkMutiplexityExclusive(dim1, dim2, 0.0);
    // checkMutiplexityExclusive(dim1, dim3, 0.0);
    // checkMutiplexityExclusive(dim2, dim3, 0.0);
    // }
    //
    // @Test
    // public void intersection1() {
    // model.addEdge(dim1, node1, node2);
    // model.addEdge(dim2, node3, node4);
    // model.addEdge(dim3, node1, node5);
    //
    // evaluateMultiplexity();
    //
    // checkMutiplexity(dim1, dim2, 0.0);
    // checkMutiplexity(dim1, dim3, 0.2);
    // checkMutiplexity(dim2, dim3, 0.0);
    // checkMutiplexityExclusive(dim1, dim2, 0.0);
    // checkMutiplexityExclusive(dim1, dim3, 0.33);
    // checkMutiplexityExclusive(dim2, dim3, 0.0);
    // }
    //
    // @Test
    // public void intersection2() {
    // model.addEdge(dim1, node1, node2);
    // model.addEdge(dim2, node3, node4);
    // model.addEdge(dim3, node3, node2);
    //
    // evaluateMultiplexity();
    // checkMutiplexity(dim1, dim2, 0.0);
    // checkMutiplexity(dim1, dim3, 0.25);
    // checkMutiplexity(dim2, dim3, 0.25);
    // checkMutiplexityExclusive(dim1, dim2, 0.0);
    // checkMutiplexityExclusive(dim1, dim3, 0.333);
    // checkMutiplexityExclusive(dim2, dim3, 0.333);
    // }
    //
    // @Test
    // public void intersection3() {
    // model.addEdge(dim1, node1, node2);
    // model.addEdge(dim1, node3, node4);
    // model.addEdge(dim2, node1, node3);
    // model.addEdge(dim3, node2, node5);
    // model.addEdge(dim3, node1, node6);
    //
    // evaluateMultiplexity();
    // checkMutiplexity(dim1, dim2, 0.33);
    // checkMutiplexity(dim1, dim3, 0.33);
    // checkMutiplexity(dim2, dim3, 0.166);
    // checkMutiplexityExclusive(dim1, dim2, 0.5);
    // checkMutiplexityExclusive(dim1, dim3, 0.33);
    // checkMutiplexityExclusive(dim2, dim3, 0.2);
    // }
    //
    // @Test
    // public void intersection4() {
    // model.addEdge(dim1, node1, node2);
    // model.addEdge(dim1, node1, node3);
    // model.addEdge(dim1, node2, node3);
    // model.addEdge(dim2, node2, node3);
    // model.addEdge(dim3, node1, node4);
    // model.addEdge(dim3, node2, node4);
    // model.addEdge(dim3, node2, node5);
    //
    // evaluateMultiplexity();
    // checkMutiplexity(dim1, dim2, 0.40);
    // checkMutiplexity(dim1, dim3, 0.40);
    // checkMutiplexity(dim2, dim3, 0.20);
    // checkMutiplexityExclusive(dim1, dim2, 0.666);
    // checkMutiplexityExclusive(dim1, dim3, 0.4);
    // checkMutiplexityExclusive(dim2, dim3, 0.2);
    // }

}
