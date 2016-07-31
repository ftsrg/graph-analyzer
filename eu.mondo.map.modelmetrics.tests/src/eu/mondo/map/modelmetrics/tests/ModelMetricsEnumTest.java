package eu.mondo.map.modelmetrics.tests;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import eu.mondo.map.modelmetrics.impl.ModelMetrics;

public class ModelMetricsEnumTest {

    @Test
    public void testMetricsEnum() {
	for (ModelMetrics metric : ModelMetrics.values()) {
	    AssertJUnit.assertNotNull("Cannot instantiate: " + metric.toString(), metric.instantiate());
	}
    }

}
