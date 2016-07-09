package eu.mondo.map.base.tests.metrics;

import org.junit.Assert;

import eu.mondo.map.base.data.MappedListData;

/**
 * 
 *
 * @param <T>
 *                represents the generic Type in TypedListMetric
 * @param <V>
 *                represents the generic Value in TypedListMetric
 * @param <M>
 *                derived from TypedListMetric
 * 
 * @see MappedListData
 */
public abstract class TypedListMetricTest<T, V extends Number, M extends MappedListData<T, V>> extends MetricTest<M> {

	public void checkSize(final int expectedSize) {
		Assert.assertEquals(expectedSize, metric.getValues().size());
	}

	public void checkSize(final int expectedSize, final T type) {
		Assert.assertEquals(expectedSize, metric.getValues().get(type).size());
	}

	public void checkDimensionsNumber(final int expectedNumberOfDimensions) {
		Assert.assertEquals(expectedNumberOfDimensions, metric.getValues().keySet().size());
	}

	public void checkValue(final V expectedValue, final T type, final int index) {
		Assert.assertEquals(expectedValue, metric.getValues().get(type).get(index));
	}

}
