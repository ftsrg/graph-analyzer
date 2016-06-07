package eu.mondo.map.core.metrics.typed;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimap;

import eu.mondo.map.core.metrics.Metric;
import eu.mondo.map.core.metrics.PublishedMetric;
import eu.mondo.map.core.metrics.Publishing;

public abstract class TypedListMetric<Type, Value extends Number> extends Metric implements Publishing {

	protected ListMultimap<Type, Value> typedValues;

	public TypedListMetric(String defaultName) {
		super(defaultName);
		typedValues = ArrayListMultimap.create();
	}

	public ListMultimap<Type, Value> getValues() {
		return typedValues;
	}

	public void setTypedValues(ListMultimap<Type, Value> typedValues) {
		this.typedValues = typedValues;
	}

	@Override
	public List<PublishedMetric> resolve() {
		List<PublishedMetric> resolvedMetrics = new ArrayList<>();
		for (Type key : typedValues.keySet()) {
			for (int i = 0; i < typedValues.get(key).size(); i++) {
				Value value = typedValues.get(key).get(i);
				if (isSkippable(value)) {
					continue;
				}				
				PublishedMetric metric = new PublishedMetric(name).setInstance(key.toString()).setIndex(i).setValue(value);
				resolvedMetrics.add(metric);
			}
		}
		return resolvedMetrics;
	}
	
	protected abstract boolean isSkippable(Value value);

	@Override
	public void clear() {
		typedValues.clear();
	}

	public boolean put(Type key, Value value) {
		return typedValues.put(key, value);
	}

	public boolean putAll(Multimap<? extends Type, ? extends Value> multimap) {
		return typedValues.putAll(multimap);
	}

}
