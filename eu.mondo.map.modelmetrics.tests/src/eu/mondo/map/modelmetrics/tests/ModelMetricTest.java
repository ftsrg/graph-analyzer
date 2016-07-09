package eu.mondo.map.modelmetrics.tests;

import org.junit.After;
import org.junit.Before;

import eu.mondo.map.base.data.BaseData;
import eu.mondo.map.modelmetrics.AbstractModelMetric;

public abstract class ModelMetricTest<D extends BaseData, M extends AbstractModelMetric<D>> {

	protected M metric;
	protected D data;

	@Before
	public void init() {
		metric = newMetric();
		data = metric.getData();
	}

	public abstract M newMetric();

	@After
	public abstract void clear();

	public M getMetric() {
		return metric;
	}

}
