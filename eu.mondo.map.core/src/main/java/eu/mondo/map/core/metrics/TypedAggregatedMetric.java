package eu.mondo.map.core.metrics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class TypedAggregatedMetric<K, V> extends AggregatedMetric<V> {

	protected Map<K, V> typedValues;

	public TypedAggregatedMetric() {
		typedValues = new HashMap<K, V>();
	}

	public Map<K, V> getTypedValues() {
		return typedValues;
	}

	public void setTypedValues(Map<K, V> typedValues) {
		this.typedValues = typedValues;
	}

	@Override
	public void clear() {
		typedValues.clear();
	}

	@Override
	public List<PublishedMetric> resolve() {
		List<PublishedMetric> metrics = new ArrayList<PublishedMetric>();
		for (Entry<K, V> entry : typedValues.entrySet()) {
			metrics.add(new PublishedMetric(entry.getValue().toString(), entry.getKey()
					.toString()));
		}
		return metrics;
	}

}
