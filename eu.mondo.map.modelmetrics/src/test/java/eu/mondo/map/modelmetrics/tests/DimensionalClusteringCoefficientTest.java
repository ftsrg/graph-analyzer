package eu.mondo.map.modelmetrics.tests;

import org.junit.Assert;
import org.junit.Test;

import eu.mondo.map.modelmetrics.composite.DimensionalClusteringCoefficient;

public class DimensionalClusteringCoefficientTest extends ModelMetricTest {

	protected DimensionalClusteringCoefficient coef;

	@Override
	public void initMetric() {
		coef = new DimensionalClusteringCoefficient();
	}

	protected void checkSize(int expected) {
		Assert.assertEquals(expected, coef.size());
	}

	protected void checkValue(double expected, String node) {
		Assert.assertEquals(expected, coef.calculateFirstDefinition(network, network.getNode(node)),
				0.01);
	}

	@Test
	public void calculate1() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node1, node3);

		coef.calculateFirstDefinition(network);
		checkSize(3);
		coef.clear();
		checkValue(0.0, node1);
		checkValue(0.0, node2);
		checkValue(0.0, node3);

	}

	@Test
	public void calculate2() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim2, node1, node3);

		coef.calculateFirstDefinition(network);
		checkSize(3);
		coef.clear();
		checkValue(0.0, node1);
		checkValue(0.0, node2);
		checkValue(0.0, node3);

	}

	@Test
	public void calculate3() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node1, node3);
		network.addEdge(dim1, node2, node3);

		coef.calculateFirstDefinition(network);
		checkSize(3);
		coef.clear();
		checkValue(0.0, node1);
		checkValue(0.0, node2);
		checkValue(0.0, node3);

	}

	@Test
	public void calculate4() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node1, node3);
		network.addEdge(dim2, node2, node3);

		coef.calculateFirstDefinition(network);
		checkSize(3);
		coef.clear();
		checkValue(1.0, node1);
		checkValue(0.0, node2);
		checkValue(0.0, node3);
	}

	@Test
	public void calculate5() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node1, node3);
		network.addEdge(dim2, node2, node3);
		network.addEdge(dim1, node2, node3);

		coef.calculateFirstDefinition(network);
		checkSize(3);
		coef.clear();
		checkValue(1.0, node1);
		checkValue(0.0, node2);
		checkValue(0.0, node3);
	}

	@Test
	public void calculate6() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node1, node3);
		network.addEdge(dim2, node2, node3);
		network.addEdge(dim1, node2, node3);
		network.addEdge(dim2, node1, node4);

		coef.calculateFirstDefinition(network);
		checkSize(4);
		coef.clear();
		checkValue(1.0, node1);
		checkValue(0.0, node2);
		checkValue(0.0, node3);
		checkValue(0.0, node4);
	}

	@Test
	public void calculate7() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node1, node3);
		network.addEdge(dim2, node2, node3);
		network.addEdge(dim1, node2, node3);
		network.addEdge(dim2, node1, node4);
		network.addEdge(dim2, node1, node5);

		coef.calculateFirstDefinition(network);
		checkSize(5);
		coef.clear();
		checkValue(0.5, node1);
		checkValue(0.0, node2);
		checkValue(0.0, node3);
		checkValue(0.0, node4);
		checkValue(0.0, node5);
	}

	@Test
	public void calculate8() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node1, node3);
		network.addEdge(dim2, node2, node3);
		network.addEdge(dim1, node2, node3);
		network.addEdge(dim2, node1, node4);
		network.addEdge(dim2, node1, node5);
		network.addEdge(dim2, node2, node5);

		coef.calculateFirstDefinition(network);
		checkSize(5);
		coef.clear();
		checkValue(0.5, node1);
		checkValue(0.0, node2);
		checkValue(0.0, node3);
		checkValue(0.0, node4);
		checkValue(1.0, node5);
	}

	@Test
	public void calculate9() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node1, node3);
		network.addEdge(dim2, node2, node3);
		network.addEdge(dim1, node2, node3);
		network.addEdge(dim2, node1, node4);
		network.addEdge(dim2, node1, node5);
		network.addEdge(dim2, node2, node5);
		network.addEdge(dim1, node2, node4);

		coef.calculateFirstDefinition(network);
		checkSize(5);
		coef.clear();
		checkValue(0.5, node1);
		checkValue(0.25, node2);
		checkValue(0.0, node3);
		checkValue(0.0, node4);
		checkValue(1.0, node5);
	}

	@Test
	public void calculate10() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim2, node1, node2);
		network.addEdge(dim2, node1, node3);
		network.addEdge(dim1, node1, node4);
		network.addEdge(dim2, node1, node4);
		network.addEdge(dim3, node1, node4);

		coef.calculateFirstDefinition(network);
		checkSize(4);
		coef.clear();
		checkValue(0.0, node1);
		checkValue(0.0, node2);
		checkValue(0.0, node3);
		checkValue(0.0, node4);
	}

	@Test
	public void calculate11() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim2, node1, node2);
		network.addEdge(dim2, node1, node3);
		network.addEdge(dim1, node1, node4);
		network.addEdge(dim2, node1, node4);
		network.addEdge(dim3, node1, node4);
		network.addEdge(dim3, node2, node4);

		coef.calculateFirstDefinition(network);
		checkSize(4);
		coef.clear();
		checkValue(0.25, node1);
		checkValue(0.0, node2);
		checkValue(0.0, node3);
		checkValue(1.0, node4);
	}

}
