package eu.mondo.map.modelmetrics.tests;

import static eu.mondo.map.modelmetrics.tests.ModelContext.dim1;
import static eu.mondo.map.modelmetrics.tests.ModelContext.dim2;
import static eu.mondo.map.modelmetrics.tests.ModelContext.network;
import static eu.mondo.map.modelmetrics.tests.ModelContext.node1;
import static eu.mondo.map.modelmetrics.tests.ModelContext.node2;
import static eu.mondo.map.modelmetrics.tests.ModelContext.node3;

import org.junit.Assert;
import org.junit.Test;

import eu.mondo.map.core.metrics.tests.ListMetricTest;
import eu.mondo.map.modelmetrics.composite.MultiplexParticipationCoefficient;

public class MultiplexParticipationCoefficientTest extends ListMetricTest<Double, MultiplexParticipationCoefficient> {

	@Override
	public MultiplexParticipationCoefficient initMetric() {
		return new MultiplexParticipationCoefficient();
	}

	@Override
	public void clear() {
		network.clear();
	}

	@Test
	public void testCalculation1() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim2, node2, node3);

		metric.calculate(network);

		Assert.assertEquals(0.0, metric.get(0), 0.01);
		Assert.assertEquals(1.0, metric.get(1), 0.01);
		Assert.assertEquals(0.0, metric.get(2), 0.01);

	}

	@Test
	public void testCalculation2() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim2, node2, node3);
		network.addEdge(dim1, node2, node3);

		metric.calculate(network);

		Assert.assertEquals(0.0, metric.get(0), 0.01);
		Assert.assertEquals(0.88, metric.get(1), 0.01);
		Assert.assertEquals(1.0, metric.get(2), 0.01);

	}

}
