package eu.mondo.map.modelmetrics.tests;

import static eu.mondo.map.base.tests.ListDataTesterUtil.checkSize;
import static eu.mondo.map.base.tests.MapDataTesterUtil.checkKeysSize;
import static eu.mondo.map.modelmetrics.tests.ModelContext.dim1;
import static eu.mondo.map.modelmetrics.tests.ModelContext.node1;
import static eu.mondo.map.modelmetrics.tests.ModelContext.node2;
import static eu.mondo.map.modelmetrics.tests.ModelContext.node3;
import static eu.mondo.map.modelmetrics.tests.ModelContext.node4;
import static eu.mondo.map.modelmetrics.tests.ModelContext.node5;
import static eu.mondo.map.modelmetrics.tests.ModelContext.node6;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import eu.mondo.map.base.data.ListData;
import eu.mondo.map.modelmetrics.impl.simple.ClusteringCoefficient;

public class ClusteringCoefficientTest extends ModelMetricTest<ListData<Double>, ClusteringCoefficient> {

	protected TestModel model;
	protected TestTypedModelAdapter adapter;

	@Override
	public ClusteringCoefficient newMetric() {
		return new ClusteringCoefficient();
	}

	@Override
	public void clear() {
		model.clear();
	}

	@Override
	public void init() {
		super.init();
		model = new TestModel();
		adapter = new TestTypedModelAdapter();
		metric.trace();
	}

	protected void evaluateAndCheck(double expected, String node) {
		metric.evaluate(adapter, node);
		assertEquals(expected, metric.getData().last(), 0.01);
		assertEquals(Double.valueOf(expected), metric.getTracing().get(node), 0.01);
	}

	@Test
	public void testZero() {
		model.addEdge(dim1, node1, node2);
		model.addEdge(dim1, node1, node3);
		adapter.init(model);

		metric.evaluate(adapter);
		checkSize(3, data);
		checkKeysSize(3, metric.getTracing());

		for (Double value : data.getValues()) {
			assertEquals(value.toString(), 0.0, value, 0.01);
		}
		metric.clear();

		evaluateAndCheck(0.0, node1);
		evaluateAndCheck(0.0, node2);
		evaluateAndCheck(0.0, node3);

		checkSize(3, data);
		checkKeysSize(3, metric.getTracing());
	}

	@Test
	public void testClustering() {
		model.addEdge(dim1, node1, node2);
		model.addEdge(dim1, node1, node3);
		model.addEdge(dim1, node3, node2);
		adapter.init(model);

		metric.evaluate(adapter);
		checkSize(3, data);
		checkKeysSize(3, metric.getTracing());

		metric.clear();

		evaluateAndCheck(1.0, node1);
		evaluateAndCheck(1.0, node2);
		evaluateAndCheck(1.0, node3);
	}

	@Test
	public void testClustering2() {
		model.addEdge(dim1, node1, node2);
		model.addEdge(dim1, node1, node3);
		model.addEdge(dim1, node3, node2);
		model.addEdge(dim1, node4, node5);
		model.addEdge(dim1, node1, node4);
		adapter.init(model);

		metric.evaluate(adapter);
		checkSize(5, data);
		checkKeysSize(5, metric.getTracing());

		metric.clear();

		evaluateAndCheck(0.333, node1);
		evaluateAndCheck(1.0, node2);
		evaluateAndCheck(1.0, node3);
		evaluateAndCheck(0.0, node4);
		evaluateAndCheck(0.0, node5);
	}

	@Test
	public void testClustering3() {
		model.addEdge(dim1, node1, node2);
		model.addEdge(dim1, node1, node3);
		model.addEdge(dim1, node1, node4);
		model.addEdge(dim1, node1, node5);
		model.addEdge(dim1, node2, node3);

		model.addEdge(dim1, node3, node6);
		model.addEdge(dim1, node4, node6);
		model.addEdge(dim1, node4, node3);
		model.addEdge(dim1, node5, node6);
		adapter.init(model);

		metric.evaluate(adapter);
		checkSize(6, data);
		checkKeysSize(6, metric.getTracing());

		metric.clear();

		evaluateAndCheck(0.333, node1);
		evaluateAndCheck(1.0, node2);
		evaluateAndCheck(0.5, node3);
		evaluateAndCheck(0.666, node4);
		evaluateAndCheck(0.0, node5);
		evaluateAndCheck(0.333, node6);
		checkSize(6, data);
		checkKeysSize(6, metric.getTracing());
	}

	@Test
	public void testTracingReevaluation() {
		model.addEdge(dim1, node1, node2);
		model.addEdge(dim1, node1, node3);
		model.addEdge(dim1, node3, node2);
		model.addEdge(dim1, node4, node5);
		model.addEdge(dim1, node1, node4);
		adapter.init(model);

		evaluateAndCheck(0.333, node1);
		evaluateAndCheck(1.0, node2);

		evaluateAndCheck(0.333, node1);
	}

	@Test
	public void testTracingOnModification() {
		model.addEdge(dim1, node1, node2);
		model.addEdge(dim1, node1, node3);
		model.addEdge(dim1, node3, node2);
		model.addEdge(dim1, node4, node5);
		model.addEdge(dim1, node1, node4);
		adapter.init(model);

		evaluateAndCheck(0.333, node1);
		checkKeysSize(1, metric.getTracing());

		model.addEdge(dim1, node4, node2);
		adapter.init(model);

		evaluateAndCheck(0.66, node1);
		checkKeysSize(1, metric.getTracing());

	}

}
