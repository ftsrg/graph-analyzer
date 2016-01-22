package eu.mondo.map.core.metrics.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimap;

public abstract class ListMetric<T> {

	protected List<T> values;
	protected ListMultimap<String, T> typedValues;

	public ListMetric() {
		this.values = new ArrayList<T>();
		this.typedValues = ArrayListMultimap.create();
	}

	public List<T> getValues() {
		return values;
	}

	public void setMetricValues(List<T> metricValues) {
		this.values = metricValues;
	}

	public ListMultimap<String, T> getTypedValues() {
		return typedValues;
	}

	public void setTypedValues(ListMultimap<String, T> typedValues) {
		this.typedValues = typedValues;
	}

	@Override
	public String toString() {
		return "ListMetric [metricValues=" + values + ", typedValues=" + typedValues + "]";
	}

	public boolean add(T e) {
		return values.add(e);
	}

	public boolean addAll(Collection<? extends T> c) {
		return values.addAll(c);
	}

	public void clear() {
		values.clear();
		typedValues.clear();
	}

	public boolean add(String key, T value) {
		return typedValues.put(key, value);
	}

	public boolean addAll(Multimap<? extends String, ? extends T> multimap) {
		return typedValues.putAll(multimap);
	}

}
