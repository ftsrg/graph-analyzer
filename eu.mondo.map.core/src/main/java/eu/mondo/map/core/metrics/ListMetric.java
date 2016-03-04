package eu.mondo.map.core.metrics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class ListMetric<Value> extends Metric implements Publishing {

	public ListMetric(String defaultName) {
		super(defaultName);
		this.values = new ArrayList<Value>();
	}

	protected List<Value> values;

	@Override
	public List<PublishedMetric> resolve() {
		List<PublishedMetric> resolvedMetrics = new ArrayList<PublishedMetric>();
		for (int i = 0; i < values.size(); i++) {
			resolvedMetrics.add(
					new PublishedMetric(values.get(i).toString(), String.format("%s-%d", name, i)));
		}
		return resolvedMetrics;
	}

	public List<Value> getValues() {
		return values;
	}

	public void setMetricValues(List<Value> metricValues) {
		this.values = metricValues;
	}

	public boolean add(Value e) {
		return values.add(e);
	}

	public boolean addAll(Collection<? extends Value> c) {
		return values.addAll(c);
	}

	@Override
	public void clear() {
		values.clear();
	}

	public int size() {
		return values.size();
	}

	public Value get(int index) {
		return values.get(index);
	}

}
