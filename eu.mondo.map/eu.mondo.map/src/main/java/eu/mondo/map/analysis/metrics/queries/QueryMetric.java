package eu.mondo.map.analysis.metrics.queries;

import eu.mondo.map.analysis.metrics.Metric;

public abstract class QueryMetric extends Metric {

	public void reset() {
		metricValue = 0.0;
	}

	public void increase() {
		metricValue++;
	}

	public void increase(final int value) {
		metricValue += value;
	}

}
