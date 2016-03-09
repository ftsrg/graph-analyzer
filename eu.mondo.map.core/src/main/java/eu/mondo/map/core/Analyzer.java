package eu.mondo.map.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import eu.mondo.map.core.metrics.Metric;
import eu.mondo.map.core.metrics.PublishedMetric;
import eu.mondo.map.core.metrics.Publishing;;

public class Analyzer {

	protected List<Metric> metrics;

	public Analyzer() {
		metrics = new ArrayList<Metric>();
	}

	public Analyzer(final ArrayList<Metric> metrics) {
		this.metrics = metrics;
	}

	public boolean addAllMetrics(final Collection<? extends Metric> c) {
		return metrics.addAll(c);
	}

	public boolean addMetric(final Metric e) {
		return metrics.add(e);
	}

	public List<PublishedMetric> resolve() {
		List<PublishedMetric> resolvedMetrics = new ArrayList<PublishedMetric>();
		for (Metric metric : metrics) {
			if (metric instanceof Publishing) {
				resolvedMetrics.addAll(((Publishing) metric).resolve());
			}
		}
		return resolvedMetrics;
	}

	public void setMetrics(final ArrayList<Metric> metrics) {
		this.metrics = metrics;
	}

}
