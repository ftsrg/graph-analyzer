package eu.mondo.map.modelmetrics.tests;

import org.junit.Assert;
import org.junit.Test;

import eu.mondo.map.modelmetrics.ModelMetrics;

public class ModelMetricsTest {

	@Test
	public void testMetricsEnum() {
		for (ModelMetrics metric : ModelMetrics.values()) {
			Assert.assertNotNull("Cannot instantiate: " + metric.toString(), metric.instantiate());
			;
		}
	}

}
