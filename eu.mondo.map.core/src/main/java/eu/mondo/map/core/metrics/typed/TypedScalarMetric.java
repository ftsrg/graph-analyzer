package eu.mondo.map.core.metrics.typed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import eu.mondo.map.core.metrics.Metric;
import eu.mondo.map.core.metrics.PublishedMetric;
import eu.mondo.map.core.metrics.Publishing;

public abstract class TypedScalarMetric<K, V> implements Metric, Publishing {

	protected Map<K, V> typedValues;

	public TypedScalarMetric() {
		typedValues = new HashMap<K, V>();
	}

	public Map<K, V> getValues() {
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
			metrics.add(new PublishedMetric(entry.getValue().toString(), getName() + "-"
					+ entry.getKey().toString()));
		}
		return metrics;
	}

}
