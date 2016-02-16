package eu.mondo.map.core.metrics;

import groovy.util.GroovyCollections;

public class AggregatedMetric extends ScalarMetric<Double> {

	@Override
	public void clear() {
		value = null;
	}

	@Override
	public String getName() {
		return "AggregatedMetric";
	}

	public void calculateAverage(final ListMetric<?> list) {
		double sum = (double) GroovyCollections.sum(list.getValues().toArray());
		value = sum / list.getValues().size();
	}

}
