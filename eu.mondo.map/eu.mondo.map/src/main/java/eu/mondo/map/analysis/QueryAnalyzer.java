package eu.mondo.map.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import eu.mondo.map.analysis.metrics.Metric;
import eu.mondo.map.analysis.metrics.queries.ConstantsMetric;
import eu.mondo.map.analysis.metrics.queries.FiltersMetric;
import eu.mondo.map.analysis.metrics.queries.NavigationsMetric;
import eu.mondo.map.analysis.metrics.queries.QueryMetric;
import eu.mondo.map.analysis.metrics.queries.VariablesMetric;
import eu.mondo.sam.core.metrics.BenchmarkMetric;

public abstract class QueryAnalyzer extends Analyzer {

	protected NavigationsMetric navigationsMetric;
	protected FiltersMetric filtersMetric;
	protected VariablesMetric variablesMetric;
	protected ConstantsMetric constantsMetric;

	protected Map<String, QueryMetric> queryMetrics;

	@Override
	public void calculateAll() {
		calculateMetrics();
	}

	@Override
	public void initializeMetrics() {
		if (metrics == null) {
			metrics = new ArrayList<BenchmarkMetric>();
		}
		queryMetrics = new HashMap<String, QueryMetric>();

		navigationsMetric = new NavigationsMetric();
		filtersMetric = new FiltersMetric();
		variablesMetric = new VariablesMetric();
		constantsMetric = new ConstantsMetric();

		metrics.add(navigationsMetric);
		metrics.add(filtersMetric);
		metrics.add(variablesMetric);
		metrics.add(constantsMetric);

		for (BenchmarkMetric m : metrics) {
			((Metric) m).initName();
			queryMetrics.put(m.getMetricName(), (QueryMetric) m);
		}
	}

	@Override
	public void resetMetrics() {
		for (Entry<String, QueryMetric> e : queryMetrics.entrySet()) {
			e.getValue().reset();
		}

	}

	public QueryMetric getNavigationsMetric() {
		return queryMetrics.get(navigationsMetric.getMetricName());
	}

	public QueryMetric getFiltersMetric() {
		return queryMetrics.get(filtersMetric.getMetricName());
	}

}
