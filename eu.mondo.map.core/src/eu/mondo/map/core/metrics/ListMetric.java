package eu.mondo.map.core.metrics;

import static com.google.common.base.Preconditions.checkState;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class ListMetric<V extends Number> extends BaseMetric implements Publishing {

	protected List<V> values;

	public ListMetric(final String defaultName) {
		super(defaultName);
		this.values = new ArrayList<>();
	}

	public boolean add(final V e) {
		return values.add(e);
	}

	public boolean addAll(final Collection<? extends V> c) {
		return values.addAll(c);
	}

	@Override
	public void clear() {
		values.clear();
	}

	public V get(final int index) {
		return values.get(index);
	}

	public List<V> getValues() {
		return values;
	}

	@Override
	public List<PublishedMetric> resolve() {
		List<PublishedMetric> resolvedMetrics = new ArrayList<>();
		for (Integer i = 0; i < values.size(); i++) {
			PublishedMetric metric = new PublishedMetric(name).setIndex(i).setValue(values.get(i));
			resolvedMetrics.add(metric);
		}
		return resolvedMetrics;
	}

	public void setMetricValues(final List<V> metricValues) {
		this.values = metricValues;
	}

	public int size() {
		return values.size();
	}

	public V last() {
		checkState(!values.isEmpty());
		return values.get(values.size() - 1);
	}

}
