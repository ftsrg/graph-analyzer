package eu.mondo.map.base.tests.metrics;

import org.junit.After;
import org.junit.Before;

import eu.mondo.map.base.metrics.Metric;

public abstract class MetricTest<M extends Metric> {

	protected M metric;

	@Before
	public void init() {
		metric = initMetric();
	}

	public abstract M initMetric();

	@After
	public abstract void clear();
}
