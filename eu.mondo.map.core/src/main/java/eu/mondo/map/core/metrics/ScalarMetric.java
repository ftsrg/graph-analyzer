package eu.mondo.map.core.metrics;

import java.util.ArrayList;
import java.util.List;

public abstract class ScalarMetric<T> extends Metric implements Publishing {

	public ScalarMetric(String defaultName) {
		super(defaultName);
	}

	protected T value;

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	@Override
	public List<PublishedMetric> resolve() {
		List<PublishedMetric> metrics = new ArrayList<PublishedMetric>();
		metrics.add(new PublishedMetric(value.toString(), name));
		return metrics;
	}

}
