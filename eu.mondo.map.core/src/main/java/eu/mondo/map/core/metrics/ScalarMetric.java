package eu.mondo.map.core.metrics;

import java.util.ArrayList;
import java.util.List;

public abstract class ScalarMetric<T> extends Metric implements Publishing {

	protected T value;

	public ScalarMetric(final String defaultName) {
		super(defaultName);
	}

	public T getValue() {
		return value;
	}

	@Override
	public List<PublishedMetric> resolve() {
		List<PublishedMetric> metrics = new ArrayList<>();
		metrics.add(new PublishedMetric(value.toString(), name));
		return metrics;
	}

	public void setValue(final T value) {
		this.value = value;
	}

}
