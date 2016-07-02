package eu.mondo.map.modelmetrics.tests;

import static eu.mondo.map.modelmetrics.tests.ModelContext.dim1;
import static eu.mondo.map.modelmetrics.tests.ModelContext.dim2;
import static eu.mondo.map.modelmetrics.tests.ModelContext.node1;
import static eu.mondo.map.modelmetrics.tests.ModelContext.node2;
import static eu.mondo.map.modelmetrics.tests.ModelContext.node3;

import org.junit.Assert;
import org.junit.Test;

import eu.mondo.map.base.tests.metrics.ListMetricTest;
import eu.mondo.map.modelmetrics.impl.typed.MultiplexParticipationCoefficient;

public class MultiplexParticipationCoefficientTest<N>
		extends ListMetricTest<Double, MultiplexParticipationCoefficient> {

	protected TestModel model;
	protected TestTypedModelAdapter adapter;

	@Override
	public MultiplexParticipationCoefficient initMetric() {
		return new MultiplexParticipationCoefficient();
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

	@Test
	public void testCalculation1() {
		model.addEdge(dim1, node1, node2);
		model.addEdge(dim2, node2, node3);
		adapter.init(model);
		metric.evaluate(adapter);

		Assert.assertEquals(0.0, metric.get(0), 0.01);
		Assert.assertEquals(1.0, metric.get(1), 0.01);
		Assert.assertEquals(0.0, metric.get(2), 0.01);

	}

	@Test
	public void testCalculation2() {
		model.addEdge(dim1, node1, node2);
		model.addEdge(dim2, node2, node3);
		model.addEdge(dim1, node2, node3);
		adapter.init(model);

		metric.evaluate(adapter);
		Assert.assertEquals(0.0, metric.get(0), 0.01);
		Assert.assertEquals(0.88, metric.get(1), 0.01);
		Assert.assertEquals(1.0, metric.get(2), 0.01);

	}

}
