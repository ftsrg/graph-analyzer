package eu.mondo.map.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import eu.mondo.sam.core.metrics.BenchmarkMetric;
import eu.mondo.sam.core.metrics.CompositeMetric;
import eu.mondo.sam.core.metrics.ScalarMetric;

public abstract class ModelDescription extends Analyzer {

	/**
	 * The first key is the type of the objects. Every one of the them contains another Map that includes
	 * the degrees of the nodes and their distributions.
	 */
	protected Map<String, Map<Integer, Integer>> degreeDistributions;

	protected Map<String, Integer> numberOfElements;

	protected Map<String, List<String>> stationsOfSchedules;

	protected int repetitiveSchedules;

	protected String ALL = "All";

	@Override
	public void initializeMetrics() {
		degreeDistributions = new HashMap<String, Map<Integer, Integer>>();
		numberOfElements = new HashMap<String, Integer>();
		stationsOfSchedules = new HashMap<String, List<String>>();
		repetitiveSchedules = 0;
		if (metrics == null) {
			metrics = new ArrayList<BenchmarkMetric>();
		}

	}

	@Override
	public void resetMetrics() {
		degreeDistributions.clear();

	}

	@Override
	public void calculateAll() {
		calculateMetrics();
		for (String type : degreeDistributions.keySet()) {
			CompositeMetric compositeMetric = new CompositeMetric(type);
			for (Entry<Integer, Integer> entry : degreeDistributions.get(type).entrySet()) {
				ScalarMetric scalarMetric = new ScalarMetric(entry.getKey().toString());
				scalarMetric.setValue(entry.getValue());
				compositeMetric.addMetric(scalarMetric);
			}
			metrics.add(compositeMetric);
		}
		for (String key : numberOfElements.keySet()) {
			ScalarMetric metric = new ScalarMetric(key + "-elements");
			metric.setValue(numberOfElements.get(key));
			metrics.add(metric);
		}
		ScalarMetric repetitive = new ScalarMetric("Repetitive");
		repetitive.setValue(repetitiveSchedules);
		metrics.add(repetitive);
	}

	protected void addDegree(final String type, final int degree) {
		if (!degreeDistributions.containsKey(type)) {
			degreeDistributions.put(type, new HashMap<Integer, Integer>());
		}
		Map<Integer, Integer> dists = degreeDistributions.get(type);
		int count = 0;

		if (dists.containsKey(degree)) {
			count = dists.get(degree);
			count++;
			dists.put(degree, count);
		} else {
			dists.put(degree, 1);
		}

		if (!type.equals(ALL)) {
			addDegree(ALL, degree);
		}

	}

	protected void addStationOfSchedule(final String schedule, final String station) {
		if (!stationsOfSchedules.containsKey(schedule)) {
			stationsOfSchedules.put(schedule, new ArrayList<String>());
		}
		stationsOfSchedules.get(schedule).add(station);
	}

	protected void checkRepetitiveSchedules(final String schedule) {
		if (stationsOfSchedules.get(schedule).size() == 0) {
			return;
		}
		if (match(schedule, stationsOfSchedules.get(schedule))) {
			repetitiveSchedules++;
			stationsOfSchedules.remove(schedule);
		}
	}

	protected boolean match(final String key, final List<String> values) {
		for (Entry<String, List<String>> entry : stationsOfSchedules.entrySet()) {
			if (!entry.getKey().equals(key)) {
				if (entry.getValue().equals(values)) {
					return true;
				}
			}
		}
		return false;
	}
}
