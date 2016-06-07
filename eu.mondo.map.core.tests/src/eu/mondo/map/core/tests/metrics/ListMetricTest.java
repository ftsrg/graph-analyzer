package eu.mondo.map.core.tests.metrics;

import org.junit.Assert;

import eu.mondo.map.core.metrics.ListMetric;

/**
 * 
 *
 * @param <V>
 *                represents the generic Value in ListMetric
 * @param <M>
 *                derived from ListMetric
 * 
 * @see ListMetric
 */
public abstract class ListMetricTest<V extends Number, M extends ListMetric<V>> extends MetricTest<M> {

	public void checkSize(int expected) {
		Assert.assertEquals(expected, metric.size());
	}

	public void checkAppearance(int expecpectedAppearance, final V value) {
		int appearance = 0;
		for (int i = 0; i < metric.size(); i++) {
			if (value.equals(metric.get(i))) {
				appearance++;
			}
		}
		Assert.assertEquals(expecpectedAppearance, appearance);
	}

	public void checkMinAppearance(int expecpectedMinAppearance, final V value) {
		int appearance = 0;
		for (int i = 0; i < metric.size(); i++) {
			if (value.equals(metric.get(i))) {
				appearance++;
			}
		}
		Assert.assertTrue(appearance >= expecpectedMinAppearance);
	}

	public void checkMaxAppearance(int expecpectedMaxAppearance, final V value) {
		int appearance = 0;
		for (int i = 0; i < metric.size(); i++) {
			if (value.equals(metric.get(i))) {
				appearance++;
			}
		}
		Assert.assertTrue(appearance <= expecpectedMaxAppearance);
	}

}
