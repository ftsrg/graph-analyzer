package eu.mondo.map.modelmetrics.tests;

import static eu.mondo.map.modelmetrics.tests.ModelContext.dim1;
import static eu.mondo.map.modelmetrics.tests.ModelContext.network;
import static eu.mondo.map.modelmetrics.tests.ModelContext.node1;
import static eu.mondo.map.modelmetrics.tests.ModelContext.node2;
import static eu.mondo.map.modelmetrics.tests.ModelContext.node3;
import static eu.mondo.map.modelmetrics.tests.ModelContext.node4;
import static eu.mondo.map.modelmetrics.tests.ModelContext.node5;
import static eu.mondo.map.modelmetrics.tests.ModelContext.node6;

import org.junit.Assert;
import org.junit.Test;

import eu.mondo.map.core.metrics.tests.ListMetricTest;
import eu.mondo.map.modelmetrics.composite.ClusteringCoefficientList;

public class ClusteringCoefficientTest extends ListMetricTest<ClusteringCoefficientList> {

	@Override
	public ClusteringCoefficientList initMetric() {
		return new ClusteringCoefficientList();
	}

	@Override
	public void clear() {
		network.clear();
	}

	protected void checkValue(double expected, String node) {
		Assert.assertEquals(expected, metric.calculate(network.getNode(node)), 0.01);
	}

	@Test
	public void testZero() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node1, node3);

		metric.calculate(network);
		checkSize(3);
		for (Double value : metric.getValues()) {
			Assert.assertEquals(value.toString(), 0.0, value, 0.01);
		}
		metric.clear();

		Assert.assertEquals(0.0, metric.calculate(network.getNode(node1)), 0.01);
		Assert.assertEquals(0.0, metric.calculate(network.getNode(node2)), 0.01);
		Assert.assertEquals(0.0, metric.calculate(network.getNode(node3)), 0.01);

		checkSize(3);
	}

	@Test
	public void testClustering() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node1, node3);
		network.addEdge(dim1, node3, node2);

		metric.calculate(network);
		checkSize(3);
		metric.clear();
		checkValue(1.0, node1);
		checkValue(1.0, node2);
		checkValue(1.0, node3);
	}

	@Test
	public void testClustering2() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node1, node3);
		network.addEdge(dim1, node3, node2);
		network.addEdge(dim1, node4, node5);
		network.addEdge(dim1, node1, node4);

		metric.calculate(network);
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
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node1, node3);
		network.addEdge(dim1, node1, node4);
		network.addEdge(dim1, node1, node5);
		network.addEdge(dim1, node2, node3);

		network.addEdge(dim1, node3, node6);
		network.addEdge(dim1, node4, node6);
		network.addEdge(dim1, node4, node3);
		network.addEdge(dim1, node5, node6);

		metric.calculate(network);
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
