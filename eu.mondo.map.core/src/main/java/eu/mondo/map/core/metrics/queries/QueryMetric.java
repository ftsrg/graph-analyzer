package eu.mondo.map.core.metrics.queries;

import eu.mondo.sam.core.metrics.BenchmarkMetric;

public abstract class QueryMetric extends BenchmarkMetric {

	protected double metricValue;

	public void reset() {
		metricValue = 0.0;
	}

	public void increase() {
		metricValue++;
	}

//	protected abstract String getIdentifier();

	public void increase(final int value) {
		metricValue += value;
	}

	@Override
	public String getValue() {
		return Double.toString(metricValue);
	}

}
