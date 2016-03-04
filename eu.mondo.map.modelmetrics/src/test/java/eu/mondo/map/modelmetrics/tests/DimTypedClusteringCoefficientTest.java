package eu.mondo.map.modelmetrics.tests;

import static eu.mondo.map.modelmetrics.tests.ModelContext.dim1;
import static eu.mondo.map.modelmetrics.tests.ModelContext.network;
import static eu.mondo.map.modelmetrics.tests.ModelContext.node1;
import static eu.mondo.map.modelmetrics.tests.ModelContext.node2;
import static eu.mondo.map.modelmetrics.tests.ModelContext.node3;

import org.junit.Test;

import eu.mondo.map.core.metrics.tests.TypedListMetricTest;
import eu.mondo.map.modelmetrics.composite.typed.DimensionalTypedClusteringCoefficientList;

public class DimTypedClusteringCoefficientTest extends TypedListMetricTest<DimensionalTypedClusteringCoefficientList> {

	@Override
	public DimensionalTypedClusteringCoefficientList initMetric() {
		return new DimensionalTypedClusteringCoefficientList();
	}

	@Override
	public void clear() {
		network.clear();
	}

	@Test
	public void testZero() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node1, node3);

		metric.calculate(network);
	}

}
