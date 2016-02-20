package eu.mondo.map.modelmetrics.tests;

import org.junit.Assert;
import org.junit.Test;

import eu.mondo.map.modelmetrics.composite.ClusteringCoefficientList;

public class ClusteringCoefficientTest extends ModelMetricTest {

	protected ClusteringCoefficientList clusteringCoefficientList;

	@Override
	public void initMetric() {
		clusteringCoefficientList = new ClusteringCoefficientList();
	}

	protected void checkSize(int expected) {
		Assert.assertEquals(expected, clusteringCoefficientList.size());
	}

	protected void checkValue(double expected, String node) {
		Assert.assertEquals(expected, clusteringCoefficientList.calculate(network.getNode(node)),
				0.01);
	}

	@Test
	public void testZero() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node1, node3);

		clusteringCoefficientList.calculate(network);
		checkSize(3);
		for (Double value : clusteringCoefficientList.getValues()) {
			Assert.assertEquals(value.toString(), 0.0, value, 0.01);
		}
		clusteringCoefficientList.clear();

		Assert.assertEquals(0.0, clusteringCoefficientList.calculate(network.getNode(node1)), 0.01);
		Assert.assertEquals(0.0, clusteringCoefficientList.calculate(network.getNode(node2)), 0.01);
		Assert.assertEquals(0.0, clusteringCoefficientList.calculate(network.getNode(node3)), 0.01);

		checkSize(3);
	}

	@Test
	public void testClustering() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node1, node3);
		network.addEdge(dim1, node3, node2);

		clusteringCoefficientList.calculate(network);
		checkSize(3);
		clusteringCoefficientList.clear();
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

		clusteringCoefficientList.calculate(network);
		checkSize(5);
		clusteringCoefficientList.clear();
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

		clusteringCoefficientList.calculate(network);
		checkSize(6);
		clusteringCoefficientList.clear();
		checkValue(0.333, node1);
		checkValue(1.0, node2);
		checkValue(0.5, node3);
		checkValue(0.666, node4);
		checkValue(0.0, node5);
		checkValue(0.333, node6);
		checkSize(6);
	}

}
