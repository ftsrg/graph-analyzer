package eu.mondo.map.core;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

import eu.mondo.map.core.metrics.Metric;;

public abstract class Analyzer<K, V extends Metric> {

	protected Map<K, V> metrics;

	public Analyzer() {
		metrics = new LinkedHashMap<>();
	}

	@Override
	public String toString() {
		return "Analyzer [metrics=" + metrics + "]";
	}

	/**
	 * <p>
	 * Removes every metric that were added to this class by clearing the
	 * <em>metrics</em> collection.
	 * </p>
	 */
	public Analyzer<K, V> clear() {
		metrics.clear();
		return this;
	}

	public Map<K, V> getMetrics() {
		return metrics;
	}

	/**
	 * <p>
	 * Returns the values from <em>metrics</em> collection in the order of
	 * insertion.
	 * </p>
	 * 
	 * @return metrics in insertion order
	 */
	public List<V> getMetricsInOrder() {
		return Lists.newArrayList(metrics.values());
	}

}
