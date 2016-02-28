package eu.mondo.map.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import eu.mondo.map.core.metrics.Metric;
import eu.mondo.map.core.metrics.Publishing;
import eu.mondo.map.core.metrics.PublishedMetric;;

public class Analyzer {

	protected ArrayList<Metric> metrics;

	public Analyzer() {
		metrics = new ArrayList<Metric>();
	}

	public Analyzer(ArrayList<Metric> metrics) {
		this.metrics = metrics;
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

	public void setMetrics(ArrayList<Metric> metrics) {
		this.metrics = metrics;
	}

	public boolean addMetric(Metric e) {
		return metrics.add(e);
	}

	public boolean addAllMetrics(Collection<? extends Metric> c) {
		return metrics.addAll(c);
	}

}
