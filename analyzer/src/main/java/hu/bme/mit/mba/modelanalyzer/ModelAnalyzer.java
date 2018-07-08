package hu.bme.mit.mba.modelanalyzer;

import com.google.common.base.Preconditions;
import hu.bme.mit.mba.base.Analyzer;
import hu.bme.mit.mba.base.metrics.Metric;
import hu.bme.mit.mba.modeladapters.ModelAdapter;
import hu.bme.mit.mba.modelmetrics.ModelMetric;
import hu.bme.mit.mba.modelmetrics.impl.ModelMetricsEnum;
import org.apache.log4j.Logger;

import static com.google.common.base.Preconditions.checkState;

public class ModelAnalyzer extends Analyzer<String, ModelMetric> {

    protected final Logger logger = Logger.getLogger(this.getClass());

    @Override
    public ModelAnalyzer clear() {
        super.clear();
        return this;
    }

    /**
     * Adds the parameter metric (see {@link ModelMetricsEnum}) to the metrics
     * collection which will be used in the analysis. Throws
     * IllegalStateException if the metric was already added to this class.
     *
     * @param metric
     *            represents the metric
     * @throws IllegalStateException
     *             if metric was already added
     * @return this
     */
    public ModelAnalyzer use(final ModelMetricsEnum metric) {
        useMetric(metric, null);
        return this;
    }

    /**
     * Adds the parameter metric (see {@link ModelMetricsEnum}) to the
     * <em>metrics</em> collection which will be used in the analysis. The
     * metric is identified by the given name parameter. Throws
     * IllegalStateException if the metric was already added to this class.
     *
     * @param metric
     *            {@link ModelMetricsEnum}
     *
     * @param name
     *            String, represents the name of the metric
     * @throws IllegalStateException
     *             if metric was already added
     *
     * @return this
     */
    public ModelAnalyzer use(final ModelMetricsEnum metric, final String name) {
        useMetric(metric, name);
        return this;
    }

    public ModelAnalyzer use(final ModelMetric metric) {
        final ModelMetricsEnum metricEnum = ModelMetricsEnum.getEnum(metric);
        checkNewMetric(metricEnum);
        addMetric(metricEnum, metric, metric.getName());
        return this;
    }

    protected void useMetric(final ModelMetricsEnum metric, final String name) {
        checkNewMetric(metric);
        try {
            addMetric(metric, metric.instantiate(), name);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException("The parameter " + metric + " cannot be instantiated.", e);
        }
    }

    protected void checkNewMetric(final ModelMetricsEnum metric) {
        checkState(!metrics.containsKey(metric.toString()),
                "The metric " + metric.toString() + " was already added to the analyzer.");
    }

    protected void addMetric(final ModelMetricsEnum metric, final ModelMetric metricObj, final String name) {
        metrics.put(metric.toString(), metricObj);
        logger.info("Use metric for the analysis: " + metricObj.getName());
    }

    /**
     * Adds all of the metrics that are in {@link ModelMetricsEnum}. It must be
     * guaranteed that other metric was not added before and the
     * <em>metrics</em> collection is empty.
     *
     * @throws IllegalStateException
     *             if at least one metric was added before
     *
     * @return
     */
    public ModelAnalyzer useAll() {
        checkEmptyMetrics();
        for (final ModelMetricsEnum m : ModelMetricsEnum.values()) {
            useMetric(m, null);
        }
        return this;
    }

    protected void checkEmptyMetrics() {
        checkState(metrics.isEmpty(),
                "The ModelAnalyzer already contains metrics, so the useAll function cannot be used.");
    }

    /**
     * Removes the metric parameter (see {@link ModelMetricsEnum}) from the
     * <em>metrics</em> collection. As a result, the metric won't be calculated
     * during the analysis.
     *
     * @param metric
     *            {@link ModelMetricsEnum} value
     *
     * @throws IllegalStateException
     *             if the <em>metrics</em> collection is empty
     * @return this
     */
    public ModelAnalyzer omit(final ModelMetricsEnum metric) {
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
     * {@link ModelMetricsEnum} parameter and contained by the ModelAnalyzer. If
     * there is not any {@link Metric} object, returns <strong>null</strong>.
     *
     * @param metric
     *            {@link ModelMetricsEnum} value
     *
     * @return {@link Metric} object or <strong>null</strong>
     */
    public ModelMetric getMetric(final ModelMetricsEnum metric) {
        return metrics.get(metric.toString());
    }

    /**
     * Evaluates every metric ({@link Metric}) which were added for the
     * analysis. The calculations are evaluated in the insertion order of
     * metrics. The adapter parameter ({@link ModelAdapter}) provides the
     * necessary interface for the calculations.
     *
     * @param adapter
     *            instance of {@link ModelAdapter}
     * @param <N>
     *            the model
     *
     * @return this
     */
    public <N, T> ModelAnalyzer evaluate(final ModelAdapter<N, T> adapter) {
        for (final ModelMetric m : metrics.values()) {
            m.evaluate(adapter);
        }
        return this;
    }

    /**
     * Evaluates the given {@link Metric} identified by the
     * {@link ModelMetricsEnum} parameter. The adapter parameter
     * ({@link ModelAdapter}) provides the necessary interface for the
     * calculations.
     *
     * @param adapter
     *            instance of {@link ModelAdapter}
     * @param metric
     *            value in {@link ModelMetricsEnum}, identifies the
     *            {@link Metric} to evaluateT
     * @param <N>
     *            type of the model
     *
     * @return this
     */
    public <N, T> ModelAnalyzer evaluate(final ModelAdapter<N, T> adapter, final ModelMetricsEnum metric) {
        final ModelMetric metricObj = getMetric(metric);
        Preconditions.checkNotNull("The " + metric + " metric was not added to the analyzer.", metricObj);

        metricObj.evaluate(adapter);
        return this;
    }

}
