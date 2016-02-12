package eu.mondo.map.core.metrics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

public abstract class TypedListMetric<Type, Value> extends ListMetric<Value> {

	protected ListMultimap<Type, Value> typedValues;

	public TypedListMetric() {
		super();
		this.typedValues = ArrayListMultimap.create();
	}

	public ListMultimap<Type, Value> getTypedValues() {
		return typedValues;
	}

	public void setTypedValues(ListMultimap<Type, Value> typedValues) {
		this.typedValues = typedValues;
	}

	@Override
	public List<PublishedMetric> resolve() {
		List<PublishedMetric> resolvedMetrics = new ArrayList<PublishedMetric>();
		for (Type key : typedValues.keys()) {
			for (int i = 0; i < values.size(); i++) {
				resolvedMetrics.add(new PublishedMetric(values.get(i).toString(), String
						.format(getName(), "-", key.toString(), "-", i)));
			}
		}
		return resolvedMetrics;
	}

	@Override
	public void clear() {
		typedValues.clear();
	}

	@Override
	public boolean add(Value e) {
		return super.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends Value> c) {
		return super.addAll(c);
	}

}
