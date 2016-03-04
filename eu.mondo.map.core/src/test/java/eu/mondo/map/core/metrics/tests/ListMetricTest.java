package eu.mondo.map.core.metrics.tests;

import org.junit.Assert;

import eu.mondo.map.core.metrics.ListMetric;

public abstract class ListMetricTest<M extends ListMetric<?>> extends MetricTest<M> {

	protected void checkSize(int expected) {
		Assert.assertEquals(expected, metric.size());
	}

}
