package eu.mondo.map.modelmetrics.tests;

import org.junit.Assert;
import org.junit.Test;

import eu.mondo.map.modelmetrics.composite.MultiplexParticipationCoefficient;

public class MultiplexParticipationCoefficientTest extends ModelMetricTest {

	protected MultiplexParticipationCoefficient coefficient;

	@Override
	public void initMetric() {
		coefficient = new MultiplexParticipationCoefficient();
	}

	@Test
	public void testCalculation1() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim2, node2, node3);

		coefficient.calculate(network);

		Assert.assertEquals(0.0, coefficient.get(0), 0.01);
		Assert.assertEquals(1.0, coefficient.get(1), 0.01);
		Assert.assertEquals(0.0, coefficient.get(2), 0.01);

	}

	@Test
	public void testCalculation2() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim2, node2, node3);
		network.addEdge(dim1, node2, node3);

		coefficient.calculate(network);

		Assert.assertEquals(0.0, coefficient.get(0), 0.01);
		Assert.assertEquals(0.88, coefficient.get(1), 0.01);
		Assert.assertEquals(1.0, coefficient.get(2), 0.01);

	}

}
