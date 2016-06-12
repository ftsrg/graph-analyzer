package eu.mondo.map.core.metrics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class ListMetric<Value extends Number> extends BaseMetric implements Publishing {

	protected List<Value> values;

	public ListMetric(final String defaultName) {
		super(defaultName);
		this.values = new ArrayList<>();
	}

	public boolean add(final Value e) {
		return values.add(e);
	}

	public boolean addAll(final Collection<? extends Value> c) {
		return values.addAll(c);
	}

	@Override
	public void clear() {
		values.clear();
	}

	public Value get(final int index) {
		return values.get(index);
	}

	public List<Value> getValues() {
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

	public void setMetricValues(final List<Value> metricValues) {
		this.values = metricValues;
	}

	public int size() {
		return values.size();
	}

}
