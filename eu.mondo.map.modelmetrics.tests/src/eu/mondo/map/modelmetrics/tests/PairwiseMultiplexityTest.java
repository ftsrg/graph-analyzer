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
import static eu.mondo.map.modelmetrics.tests.ModelContext.node6;

import org.junit.Assert;
import org.junit.Test;

import eu.mondo.map.core.tests.metrics.TypedScalarMetricTest;
import eu.mondo.map.modelmetrics.impl.typed.PairwiseMultiplexity;

public class PairwiseMultiplexityTest extends TypedScalarMetricTest<PairwiseMultiplexity> {

	protected TestModel model;
	protected TestTypedModelAdapter adapter;

	@Override
	public PairwiseMultiplexity initMetric() {
		return new PairwiseMultiplexity();
	}

	@Override
	public void init() {
		super.init();
		model = new TestModel();
		adapter = new TestTypedModelAdapter();
	}

	@Override
	public void clear() {
		model.clear();
	}

	public void checkMutiplexity(String dim1, String dim2, double expected) {
		Assert.assertEquals(expected, metric.getValues().get(dim1 + "-" + dim2).doubleValue(), 0.01);
	}

	private void checkMutiplexityExclusive(String dim1, String dim2, double expected) {
		Assert.assertEquals(expected, metric.getValues().get(dim1 + "-" + dim2 + "-exclusive").doubleValue(), 0.01);

	}

	private void evaluateMultiplexity() {
		metric.evaluate(network, dim1, dim2);
		metric.evaluate(network, dim1, dim3);
		metric.evaluate(network, dim2, dim3);
		metric.evaluateExclusive(network, dim1, dim2);
		metric.evaluateExclusive(network, dim1, dim3);
		metric.evaluateExclusive(network, dim2, dim3);
	}

	@Test
	public void zeroIntersection1() {
		model.addEdge(dim1, node1, node2);
		model.addEdge(dim2, node3, node4);
		model.addEdge(dim3, node5, node6);

		evaluateMultiplexity();
		checkMutiplexity(dim1, dim2, 0.0);
		checkMutiplexity(dim1, dim3, 0.0);
		checkMutiplexity(dim2, dim3, 0.0);
		checkMutiplexityExclusive(dim1, dim2, 0.0);
		checkMutiplexityExclusive(dim1, dim3, 0.0);
		checkMutiplexityExclusive(dim2, dim3, 0.0);
	}

	@Test
	public void intersection1() {
		model.addEdge(dim1, node1, node2);
		model.addEdge(dim2, node3, node4);
		model.addEdge(dim3, node1, node5);

		evaluateMultiplexity();

		checkMutiplexity(dim1, dim2, 0.0);
		checkMutiplexity(dim1, dim3, 0.2);
		checkMutiplexity(dim2, dim3, 0.0);
		checkMutiplexityExclusive(dim1, dim2, 0.0);
		checkMutiplexityExclusive(dim1, dim3, 0.33);
		checkMutiplexityExclusive(dim2, dim3, 0.0);
	}

	@Test
	public void intersection2() {
		model.addEdge(dim1, node1, node2);
		model.addEdge(dim2, node3, node4);
		model.addEdge(dim3, node3, node2);

		evaluateMultiplexity();
		checkMutiplexity(dim1, dim2, 0.0);
		checkMutiplexity(dim1, dim3, 0.25);
		checkMutiplexity(dim2, dim3, 0.25);
		checkMutiplexityExclusive(dim1, dim2, 0.0);
		checkMutiplexityExclusive(dim1, dim3, 0.333);
		checkMutiplexityExclusive(dim2, dim3, 0.333);
	}

	@Test
	public void intersection3() {
		model.addEdge(dim1, node1, node2);
		model.addEdge(dim1, node3, node4);
		model.addEdge(dim2, node1, node3);
		model.addEdge(dim3, node2, node5);
		model.addEdge(dim3, node1, node6);

		evaluateMultiplexity();
		checkMutiplexity(dim1, dim2, 0.33);
		checkMutiplexity(dim1, dim3, 0.33);
		checkMutiplexity(dim2, dim3, 0.166);
		checkMutiplexityExclusive(dim1, dim2, 0.5);
		checkMutiplexityExclusive(dim1, dim3, 0.33);
		checkMutiplexityExclusive(dim2, dim3, 0.2);
	}

	@Test
	public void intersection4() {
		model.addEdge(dim1, node1, node2);
		model.addEdge(dim1, node1, node3);
		model.addEdge(dim1, node2, node3);
		model.addEdge(dim2, node2, node3);
		model.addEdge(dim3, node1, node4);
		model.addEdge(dim3, node2, node4);
		model.addEdge(dim3, node2, node5);

		evaluateMultiplexity();
		checkMutiplexity(dim1, dim2, 0.40);
		checkMutiplexity(dim1, dim3, 0.40);
		checkMutiplexity(dim2, dim3, 0.20);
		checkMutiplexityExclusive(dim1, dim2, 0.666);
		checkMutiplexityExclusive(dim1, dim3, 0.4);
		checkMutiplexityExclusive(dim2, dim3, 0.2);
	}

}
