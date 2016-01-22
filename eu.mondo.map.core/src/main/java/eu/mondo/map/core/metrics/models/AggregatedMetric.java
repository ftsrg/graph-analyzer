package eu.mondo.map.core.metrics.models;

import java.util.HashMap;
import java.util.Map;

public abstract class AggregatedMetric<T> extends ModelMetric {

	protected T value;
	protected Map<String, T> typedValues;

	public AggregatedMetric() {
		typedValues = new HashMap<String, T>();
	}

	public T getValue() {
		return value;
	}

	public Map<String, T> getTypedValues() {
		return typedValues;
	}

	public void setTypedValues(Map<String, T> typedValues) {
		this.typedValues = typedValues;
	}

	public void setValue(T value) {
		this.value = value;
	}

}
