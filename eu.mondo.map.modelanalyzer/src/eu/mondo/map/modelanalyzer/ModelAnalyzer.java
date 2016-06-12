package eu.mondo.map.modelanalyzer;

import static com.google.common.base.Preconditions.checkState;

import org.apache.log4j.Logger;

import eu.mondo.map.core.Analyzer;
import eu.mondo.map.core.metrics.Metric;
import eu.mondo.map.core.metrics.SummaryMetric;
import eu.mondo.map.modelmetrics.ModelMetrics;

public class ModelAnalyzer extends Analyzer<String, Metric> {

	private final Logger logger = Logger.getLogger(this.getClass());

	/**
	 * <p>
	 * Adds the parameter metric (see {@link ModelMetrics}) to the metrics
	 * collection which will be used in the analysis. Throws
	 * IllegalStateException if the metric was already added to this class.
	 * </p>
	 * 
	 * @param metric
	 *            represents the metric
	 * @throws IllegalStateException
	 *             if metric was already added
	 * @return this
	 */
	public ModelAnalyzer use(ModelMetrics metric) {
		useMetric(metric, null);
		return this;
	}

	/**
	 * <p>
	 * Adds the parameter metric (see {@link ModelMetrics}) to the
	 * <em>metrics</em> collection which will be used in the analysis. The
	 * metric is identified by the given name parameter. Throws
	 * IllegalStateException if the metric was already added to this class.
	 * </p>
	 * 
	 * @param metric
	 *            {@link ModelMetrics}
	 * 
	 * @param name
	 *            String, represents the name of the metric
	 * @throws IllegalStateException
	 *             if metric was already added
	 * 
	 * @return this
	 */
	public ModelAnalyzer use(ModelMetrics metric, String name) {
		useMetric(metric, name);
		return this;
	}

	// /**
	// * <p>
	// * Adds the parameter metric (see {@link Metric}) to the <em>metrics</em>
	// * collection which will be used in the analysis.
	// * </p>
	// *
	// * @param metric
	// * {@link Metric} object
	// * @return this
	// */
	// public ModelAnalyzer use(Metric metric) {
	// metrics.put(metric.getName(), metric);
	// return this;
	// }

	// /**
	// * <p>
	// * Adds the parameter metric (see {@link Metric}) to the <em>metrics</em>
	// * collection which will be used in the analysis. The metric is identified
	// * by the given name parameter.
	// * </p>
	// *
	// * @param metric
	// * {@link Metric} object
	// * @param name
	// * String, represents the name of the metric
	// *
	// * @return this
	// */
	// public ModelAnalyzer use(Metric metric, String name) {
	// metric.setName(name);
	// metrics.put(metric.getName(), metric);
	// return this;
	// }

	protected void useMetric(ModelMetrics metric, String name) {
		checkState(!metrics.containsKey(metric.toString()),
				"The metric " + metric.toString() + " was already added to the analyzer.");
		Metric newMetric = metric.instantiate();
		if (name != null) {
			newMetric.setName(name);
		}
		metrics.put(metric.toString(), newMetric);
		logger.info("Use metric for the analysis: " + metric.toString());
	}

	/**
	 * <p>
	 * Adds all of the metrics that are in {@link ModelMetrics}. It must be
	 * guaranteed that other metric was not added before and the
	 * <em>metrics</em> collection is empty.
	 * </p>
	 * 
	 * @throws IllegalStateException
	 *             if at least one metric was added before
	 * 
	 * @return
	 */
	public ModelAnalyzer useAll() {
		checkState(metrics.isEmpty(),
				"The ModelAnalyzer already contains metrics, so the useAll function cannot be used.");
		for (ModelMetrics m : ModelMetrics.values()) {
			useMetric(m, null);
		}
		return this;
	}

	public ModelAnalyzer useSummary(SummaryMetric<?, ?> summary) {
		// checkArgument(metrics.containsKey(metric.toString()),
		// "The " + metric.toString() + " should be contained by the
		// ModelAnalyzer");
		// TODO
		return this;
	}

	/**
	 * <p>
	 * Removes the metric parameter (see {@link ModelMetrics}) from the
	 * <em>metrics</em> collection. As a result, the metric won't be calculated
	 * during the analysis.
	 * </p>
	 * 
	 * @param metric
	 *            {@link ModelMetrics} value
	 * 
	 * @throws IllegalStateException
	 *             if the <em>metrics</em> collection is empty
	 * @return this
	 */
	public ModelAnalyzer omit(ModelMetrics metric) {
		checkState(!metrics.isEmpty(), "The ModelAnalyzer should contain metrics to omit " + metric.toString()
				+ " from the analysis, but it is empty.");
		if (!metrics.containsKey(metric.toString())) {
			logger.warn("The ModelAnalyzer does not contain " + metric.toString() + " which is planned to be omitted.");
		} else {
			metrics.remove(metric.toString());
			logger.info("Omit the metric from the analysis: " + metric.toString());
		}
		return this;
	}

	/**
	 * Returns a {@link Metric} object which is mapped to the
	 * {@link ModelMetrics} parameter and contained by the ModelAnalyzer. If
	 * there is not any {@link Metric} object, returns <strong>null</strong>.
	 * 
	 * @param metric
	 *            {@link ModelMetrics} value
	 * 
	 * @return {@link Metric} object or <strong>null</strong>
	 */
	public Metric getMetric(ModelMetrics metric) {
		return metrics.get(metric.toString());
	}

}
