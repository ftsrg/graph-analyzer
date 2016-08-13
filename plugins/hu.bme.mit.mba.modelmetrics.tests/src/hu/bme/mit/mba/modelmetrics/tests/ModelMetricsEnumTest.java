package hu.bme.mit.mba.modelmetrics.tests;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import hu.bme.mit.mba.modelmetrics.impl.ModelMetrics;

public class ModelMetricsEnumTest {

    @Test
    public void testMetricsEnum() {
	for (ModelMetrics metric : ModelMetrics.values()) {
	    AssertJUnit.assertNotNull("Cannot instantiate: " + metric.toString(), metric.instantiate());
	}
    }

}
