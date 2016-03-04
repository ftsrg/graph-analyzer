package eu.mondo.map.modelmetrics.tests;

import static eu.mondo.map.modelmetrics.tests.ModelContext.dim1;
import static eu.mondo.map.modelmetrics.tests.ModelContext.dim2;
import static eu.mondo.map.modelmetrics.tests.ModelContext.dim3;
import static eu.mondo.map.modelmetrics.tests.ModelContext.network;
import static eu.mondo.map.modelmetrics.tests.ModelContext.node1;
import static eu.mondo.map.modelmetrics.tests.ModelContext.node2;
import static eu.mondo.map.modelmetrics.tests.ModelContext.node3;
import static eu.mondo.map.modelmetrics.tests.ModelContext.node4;
import static eu.mondo.map.modelmetrics.tests.ModelContext.node5;

import org.junit.Assert;
import org.junit.Test;

import eu.mondo.map.core.metrics.tests.ListMetricTest;
import eu.mondo.map.modelmetrics.composite.DimensionalClusteringCoefficient;

public class DimensionalClusteringCoefficientTest extends ListMetricTest<DimensionalClusteringCoefficient> {

	@Override
	public DimensionalClusteringCoefficient initMetric() {
		return new DimensionalClusteringCoefficient();
	}

	@Override
	public void clear() {
		network.clear();
	}

	protected void checkValue(double expected, String node) {
		Assert.assertEquals(expected, metric.calculateFirstDefinition(network, network.getNode(node)), 0.01);
	}

	protected void calculateFirstDefinition() {
		metric.calculateFirstDefinition(network);
	}

	@Test
	public void calculate1() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node1, node3);

		calculateFirstDefinition();
		checkSize(3);
		metric.clear();
		checkValue(0.0, node1);
		checkValue(0.0, node2);
		checkValue(0.0, node3);

	}

	@Test
	public void calculate2() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim2, node1, node3);

		calculateFirstDefinition();
		checkSize(3);
		metric.clear();
		checkValue(0.0, node1);
		checkValue(0.0, node2);
		checkValue(0.0, node3);

	}

	@Test
	public void calculate3() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node1, node3);
		network.addEdge(dim1, node2, node3);

		calculateFirstDefinition();
		checkSize(3);
		metric.clear();
		checkValue(0.0, node1);
		checkValue(0.0, node2);
		checkValue(0.0, node3);

	}

	@Test
	public void calculate4() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node1, node3);
		network.addEdge(dim2, node2, node3);

		calculateFirstDefinition();
		checkSize(3);
		metric.clear();
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

		calculateFirstDefinition();
		checkSize(3);
		metric.clear();
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

		calculateFirstDefinition();
		checkSize(4);
		metric.clear();
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

		calculateFirstDefinition();
		checkSize(5);
		metric.clear();
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

		calculateFirstDefinition();
		checkSize(5);
		metric.clear();
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

		calculateFirstDefinition();
		checkSize(5);
		metric.clear();
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

		calculateFirstDefinition();
		checkSize(4);
		metric.clear();
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

		calculateFirstDefinition();
		checkSize(4);
		metric.clear();
		checkValue(0.25, node1);
		checkValue(0.0, node2);
		checkValue(0.0, node3);
		checkValue(1.0, node4);
	}

}
