package eu.mondo.map.core.metrics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class ListMetric<Value> extends Metric implements Publishing {

	protected List<Value> values;

	public ListMetric(final String defaultName) {
		super(defaultName);
		this.values = new ArrayList<Value>();
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
		List<PublishedMetric> resolvedMetrics = new ArrayList<PublishedMetric>();
		for (int i = 0; i < values.size(); i++) {
			resolvedMetrics.add(
					new PublishedMetric(values.get(i).toString(), String.format("%s-%d", name, i)));
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
