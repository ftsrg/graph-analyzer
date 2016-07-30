package eu.mondo.map.modelmetrics.tests;

import static eu.mondo.map.tests.model.ModelContext.dim1;
import static eu.mondo.map.tests.model.ModelContext.dim2;
import static eu.mondo.map.tests.model.ModelContext.node1;
import static eu.mondo.map.tests.model.ModelContext.node2;
import static eu.mondo.map.tests.model.ModelContext.node3;

import org.junit.Assert;
import org.junit.Test;

import eu.mondo.map.base.data.ListData;
import eu.mondo.map.modeladapters.tests.CustomTypedModelAdapter;
import eu.mondo.map.modelmetrics.impl.typed.MultiplexParticipationCoefficient;
import eu.mondo.map.tests.model.TestModel;

public class MultiplexParticipationCoefficientTest<N>
		extends ModelMetricTest2<ListData<Double>, MultiplexParticipationCoefficient> {

	protected TestModel model;
	protected CustomTypedModelAdapter adapter;

	@Override
	public MultiplexParticipationCoefficient newMetric() {
		return new MultiplexParticipationCoefficient();
	}

	@Override
	public void init() {
		super.init();
		model = new TestModel();
		adapter = new CustomTypedModelAdapter();
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
		metric.evaluateAll(adapter);

		Assert.assertEquals(0.0, data.get(0), 0.01);
		Assert.assertEquals(1.0, data.get(1), 0.01);
		Assert.assertEquals(0.0, data.get(2), 0.01);

	}

	@Test
	public void testCalculation2() {
		model.addEdge(dim1, node1, node2);
		model.addEdge(dim2, node2, node3);
		model.addEdge(dim1, node2, node3);
		adapter.init(model);

		metric.evaluateAll(adapter);
		Assert.assertEquals(0.0, data.get(0), 0.01);
		Assert.assertEquals(0.88, data.get(1), 0.01);
		Assert.assertEquals(1.0, data.get(2), 0.01);

	}

}
