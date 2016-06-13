package eu.mondo.map.modelmetrics.tests;

import static eu.mondo.map.modelmetrics.tests.ModelContext.dim1;
import static eu.mondo.map.modelmetrics.tests.ModelContext.dim2;
import static eu.mondo.map.modelmetrics.tests.ModelContext.network;
import static eu.mondo.map.modelmetrics.tests.ModelContext.node1;
import static eu.mondo.map.modelmetrics.tests.ModelContext.node2;
import static eu.mondo.map.modelmetrics.tests.ModelContext.node3;
import static eu.mondo.map.modelmetrics.tests.ModelContext.node4;
import static eu.mondo.map.modelmetrics.tests.ModelContext.node5;

import org.junit.Test;

import eu.mondo.map.core.tests.metrics.TypedListMetricTest;
import eu.mondo.map.modelmetrics.impl.typed.DimensionalTypedClusteringCoefficientList;

public class DimTypedClusteringCoefficientTest
		extends TypedListMetricTest<String, Double, DimensionalTypedClusteringCoefficientList<String>> {

	@Override
	public DimensionalTypedClusteringCoefficientList<String> initMetric() {
		return new DimensionalTypedClusteringCoefficientList<>();
	}

	@Override
	public void clear() {
		network.clear();
	}

	public void calculate() {
		metric.calculate(network);
	}

	@Test
	public void testZero1() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node1, node3);

		calculate();
		checkSize(3);
		checkSize(3, dim1);
		checkDimensionsNumber(1);
		checkValue(0.0, dim1, 0);
		checkValue(0.0, dim1, 1);
		checkValue(0.0, dim1, 2);
	}

	@Test
	public void testZero2() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node1, node3);
		network.addEdge(dim2, node2, node3);

		calculate();
		checkSize(5);
		checkSize(3, dim1);
		checkSize(2, dim2);
		checkDimensionsNumber(2);
		checkValue(0.0, dim1, 0);
		checkValue(0.0, dim1, 1);
		checkValue(0.0, dim1, 2);
	}

	@Test
	public void testClustering1() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node1, node3);
		network.addEdge(dim1, node2, node3);

		calculate();
		checkSize(3);
		checkSize(3, dim1);
		checkDimensionsNumber(1);
		checkValue(1.0, dim1, 0);
		checkValue(1.0, dim1, 1);
		checkValue(1.0, dim1, 2);
	}

	@Test
	public void testClustering2() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node1, node3);
		network.addEdge(dim1, node2, node3);
		network.addEdge(dim1, node2, node4);
		network.addEdge(dim2, node3, node4);

		calculate();
		checkSize(6);
		checkSize(4, dim1);
		checkSize(2, dim2);
		checkDimensionsNumber(2);
		checkValue(1.0, dim1, 0);
		double expected = 1.0 / 3.0;
		checkValue(expected, dim1, 1);
		checkValue(1.0, dim1, 2);

		checkValue(0.0, dim2, 0);
		checkValue(0.0, dim2, 1);
	}

	@Test
	public void testClustering3() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node1, node3);
		network.addEdge(dim1, node2, node3);
		network.addEdge(dim1, node2, node4);
		network.addEdge(dim2, node3, node4);
		network.addEdge(dim2, node3, node5);
		network.addEdge(dim1, node1, node5);
		network.addEdge(dim2, node2, node5);
		network.addEdge(dim2, node4, node5);

		calculate();
		checkSize(9);
		checkSize(5, dim1);
		checkSize(4, dim2);
		checkDimensionsNumber(2);

		double expected = 1.0 / 3.0;
		checkValue(expected, dim1, 0);
		checkValue(expected, dim1, 1);
		checkValue(1.0, dim1, 2);
		checkValue(0.0, dim1, 3);
		checkValue(0.0, dim1, 4);

		checkValue(0.0, dim2, 0); // node2
		checkValue(1.0, dim2, 1); // node3
		checkValue(1.0, dim2, 2); // node4
		checkValue(expected, dim2, 3); // node5
	}

}
