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

	public void clearMetrics() {
		metrics.clear();
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

	//
	// public Analyzer(final List<Metric> metrics) {
	// this.metrics = metrics;
	// }
	//
	// public boolean addAllMetrics(final Collection<? extends Metric> c) {
	// return metrics.addAll(c);
	// }
	//
	// public boolean addMetric(final Metric e) {
	// return metrics.add(e);
	// }
	//
	// public List<PublishedMetric> resolve() {
	// List<PublishedMetric> resolvedMetrics = new ArrayList<>();
	// for (Metric metric : metrics) {
	// if (metric instanceof Publishing) {
	// resolvedMetrics.addAll(((Publishing) metric).resolve());
	// }
	// }
	// return resolvedMetrics;
	// }
	//
	// public void setMetrics(final List<Metric> metrics) {
	// this.metrics = metrics;
	// }

}
