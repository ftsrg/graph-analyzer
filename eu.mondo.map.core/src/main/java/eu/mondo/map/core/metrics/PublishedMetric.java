package eu.mondo.map.core.metrics;

import eu.mondo.sam.core.metrics.BenchmarkMetric;

public class PublishedMetric extends BenchmarkMetric {

	protected String value;

	public PublishedMetric(final String value, final String name) {
		super(name);
		this.value = value;
	}

	@Override
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
