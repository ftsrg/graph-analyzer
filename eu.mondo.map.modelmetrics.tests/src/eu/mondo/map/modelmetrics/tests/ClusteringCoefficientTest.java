package eu.mondo.map.modelmetrics.tests;

import static eu.mondo.map.modelmetrics.tests.ModelContext.dim1;
import static eu.mondo.map.modelmetrics.tests.ModelContext.node1;
import static eu.mondo.map.modelmetrics.tests.ModelContext.node2;
import static eu.mondo.map.modelmetrics.tests.ModelContext.node3;
import static eu.mondo.map.modelmetrics.tests.ModelContext.node4;
import static eu.mondo.map.modelmetrics.tests.ModelContext.node5;
import static eu.mondo.map.modelmetrics.tests.ModelContext.node6;

import org.junit.Assert;
import org.junit.Test;

import eu.mondo.map.base.tests.metrics.ListMetricTest;
import eu.mondo.map.modelmetrics.impl.simple.ClusteringCoefficient;

public class ClusteringCoefficientTest extends ListMetricTest<Double, ClusteringCoefficient> {

	protected TestModel model;
	protected TestTypedModelAdapter adapter;

	@Override
	public ClusteringCoefficient initMetric() {
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
	}

	protected void checkValue(double expected, String node) {
		metric.evaluate(adapter, node);
		Assert.assertEquals(expected, metric.last(), 0.01);
	}

	@Test
	public void testZero() {
		model.addEdge(dim1, node1, node2);
		model.addEdge(dim1, node1, node3);
		adapter.init(model);

		metric.evaluate(adapter);
		checkSize(3);
		for (Double value : metric.getValues()) {
			Assert.assertEquals(value.toString(), 0.0, value, 0.01);
		}
		metric.clear();
		checkValue(0.0, node1);
		checkValue(0.0, node2);
		checkValue(0.0, node3);
		// Assert.assertEquals(0.0, metric.evaluate(network.getNode(node1)),
		// 0.01);
		// Assert.assertEquals(0.0, metric.evaluate(network.getNode(node2)),
		// 0.01);
		// Assert.assertEquals(0.0, metric.evaluate(network.getNode(node3)),
		// 0.01);

		checkSize(3);
	}

	@Test
	public void testClustering() {
		model.addEdge(dim1, node1, node2);
		model.addEdge(dim1, node1, node3);
		model.addEdge(dim1, node3, node2);
		adapter.init(model);

		metric.evaluate(adapter);
		checkSize(3);
		metric.clear();
		checkValue(1.0, node1);
		checkValue(1.0, node2);
		checkValue(1.0, node3);
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
		checkSize(5);
		metric.clear();
		checkValue(0.333, node1);
		checkValue(1.0, node2);
		checkValue(1.0, node3);
		checkValue(0.0, node4);
		checkValue(0.0, node5);
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
		checkSize(6);
		metric.clear();
		checkValue(0.333, node1);
		checkValue(1.0, node2);
		checkValue(0.5, node3);
		checkValue(0.666, node4);
		checkValue(0.0, node5);
		checkValue(0.333, node6);
		checkSize(6);
	}

}
