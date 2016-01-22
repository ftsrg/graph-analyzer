package eu.mondo.map.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import eu.mondo.map.core.metrics.Metric;
import eu.mondo.sam.core.metrics.BenchmarkMetric;

public class Analyzer {

	protected ArrayList<Metric> metrics;

	public Analyzer() {
		metrics = new ArrayList<Metric>();
	}

	public Analyzer(ArrayList<Metric> metrics) {
		this.metrics = metrics;
	}

	public List<BenchmarkMetric> resolve() {
		return null;
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
