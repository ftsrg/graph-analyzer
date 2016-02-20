package eu.mondo.map.modelmetrics.tests;

import org.junit.Assert;
import org.junit.Test;

import eu.mondo.map.modelmetrics.scalar.typed.PairwiseMultiplexity;

public class PairwiseMultiplexityTest extends ModelMetricTest {

	protected PairwiseMultiplexity pairwiseMultiplexity;

	@Override
	public void initMetric() {
		pairwiseMultiplexity = new PairwiseMultiplexity();
	}

	public void checkMutiplexity(String dim1, String dim2, double expected) {
		Assert.assertEquals(expected, pairwiseMultiplexity.getValues().get(dim1 + "-" + dim2)
				.doubleValue(), 0.01);
	}

	private void checkMutiplexityExclusive(String dim1, String dim2, double expected) {
		Assert.assertEquals(expected,
				pairwiseMultiplexity.getValues().get(dim1 + "-" + dim2 + "-exclusive")
						.doubleValue(), 0.01);

	}

	private void calculateMultiplexity() {
		pairwiseMultiplexity.calculate(network, dim1, dim2);
		pairwiseMultiplexity.calculate(network, dim1, dim3);
		pairwiseMultiplexity.calculate(network, dim2, dim3);
		pairwiseMultiplexity.calculateExclusive(network, dim1, dim2);
		pairwiseMultiplexity.calculateExclusive(network, dim1, dim3);
		pairwiseMultiplexity.calculateExclusive(network, dim2, dim3);
	}

	@Test
	public void zeroIntersection1() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim2, node3, node4);
		network.addEdge(dim3, node5, node6);

		calculateMultiplexity();
		checkMutiplexity(dim1, dim2, 0.0);
		checkMutiplexity(dim1, dim3, 0.0);
		checkMutiplexity(dim2, dim3, 0.0);
		checkMutiplexityExclusive(dim1, dim2, 0.0);
		checkMutiplexityExclusive(dim1, dim3, 0.0);
		checkMutiplexityExclusive(dim2, dim3, 0.0);
	}

	@Test
	public void intersection1() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim2, node3, node4);
		network.addEdge(dim3, node1, node5);

		calculateMultiplexity();

		checkMutiplexity(dim1, dim2, 0.0);
		checkMutiplexity(dim1, dim3, 0.2);
		checkMutiplexity(dim2, dim3, 0.0);
		checkMutiplexityExclusive(dim1, dim2, 0.0);
		checkMutiplexityExclusive(dim1, dim3, 0.33);
		checkMutiplexityExclusive(dim2, dim3, 0.0);
	}

	@Test
	public void intersection2() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim2, node3, node4);
		network.addEdge(dim3, node3, node2);

		calculateMultiplexity();
		checkMutiplexity(dim1, dim2, 0.0);
		checkMutiplexity(dim1, dim3, 0.25);
		checkMutiplexity(dim2, dim3, 0.25);
		checkMutiplexityExclusive(dim1, dim2, 0.0);
		checkMutiplexityExclusive(dim1, dim3, 0.333);
		checkMutiplexityExclusive(dim2, dim3, 0.333);
	}

	@Test
	public void intersection3() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node3, node4);
		network.addEdge(dim2, node1, node3);
		network.addEdge(dim3, node2, node5);
		network.addEdge(dim3, node1, node6);

		calculateMultiplexity();
		checkMutiplexity(dim1, dim2, 0.33);
		checkMutiplexity(dim1, dim3, 0.33);
		checkMutiplexity(dim2, dim3, 0.166);
		checkMutiplexityExclusive(dim1, dim2, 0.5);
		checkMutiplexityExclusive(dim1, dim3, 0.33);
		checkMutiplexityExclusive(dim2, dim3, 0.2);
	}

	@Test
	public void intersection4() {
		network.addEdge(dim1, node1, node2);
		network.addEdge(dim1, node1, node3);
		network.addEdge(dim1, node2, node3);
		network.addEdge(dim2, node2, node3);
		network.addEdge(dim3, node1, node4);
		network.addEdge(dim3, node2, node4);
		network.addEdge(dim3, node2, node5);

		calculateMultiplexity();
		checkMutiplexity(dim1, dim2, 0.40);
		checkMutiplexity(dim1, dim3, 0.40);
		checkMutiplexity(dim2, dim3, 0.20);
		checkMutiplexityExclusive(dim1, dim2, 0.666);
		checkMutiplexityExclusive(dim1, dim3, 0.4);
		checkMutiplexityExclusive(dim2, dim3, 0.2);
	}

}
