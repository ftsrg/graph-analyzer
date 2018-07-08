package hu.bme.mit.ga.analyzer;

import com.google.common.base.Preconditions;
import hu.bme.mit.ga.base.Analyzer;
import hu.bme.mit.ga.base.metrics.Metric;
import hu.bme.mit.ga.adapters.GraphAdapter;
import hu.bme.mit.ga.metrics.GraphMetric;
import hu.bme.mit.ga.metrics.impl.GraphMetricsEnum;
import org.apache.log4j.Logger;

import static com.google.common.base.Preconditions.checkState;

public class GraphAnalyzer extends Analyzer<String, GraphMetric> {

    protected final Logger logger = Logger.getLogger(this.getClass());

    /**
     * Adds the parameter metric (see {@link GraphMetricsEnum}) to the metrics
     * collection which will be used in the analysis. Throws
     * IllegalStateException if the metric was already added to this class.
     *
     * @param metric represents the metric
     * @return this
     * @throws IllegalStateException if metric was already added
     */
    public GraphAnalyzer use(final GraphMetricsEnum metric) {
        useMetric(metric, null);
        return this;
    }

    /**
     * Adds the parameter metric (see {@link GraphMetricsEnum}) to the
     * <em>metrics</em> collection which will be used in the analysis. The
     * metric is identified by the given name parameter. Throws
     * IllegalStateException if the metric was already added to this class.
     *
     * @param metric {@link GraphMetricsEnum}
     * @param name   String, represents the name of the metric
     * @return this
     * @throws IllegalStateException if metric was already added
     */
    public GraphAnalyzer use(final GraphMetricsEnum metric, final String name) {
        useMetric(metric, name);
        return this;
    }

    public GraphAnalyzer use(final GraphMetric metric) {
        final GraphMetricsEnum metricEnum = GraphMetricsEnum.getEnum(metric);
        checkNewMetric(metricEnum);
        addMetric(metricEnum, metric, metric.getName());
        return this;
    }

    protected void useMetric(final GraphMetricsEnum metric, final String name) {
        checkNewMetric(metric);
        try {
            addMetric(metric, metric.instantiate(), name);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException("The parameter " + metric + " cannot be instantiated.", e);
        }
    }

    protected void checkNewMetric(final GraphMetricsEnum metric) {
        checkState(!metrics.containsKey(metric.toString()),
            "The metric " + metric.toString() + " was already added to the analyzer.");
    }

    protected void addMetric(final GraphMetricsEnum metric, final GraphMetric metricObj, final String name) {
        metrics.put(metric.toString(), metricObj);
        logger.info("Use metric for the analysis: " + metricObj.getName());
    }

    /**
     * Adds all of the metrics that are in {@link GraphMetricsEnum}. It must be
     * guaranteed that other metric was not added before and the
     * <em>metrics</em> collection is empty.
     *
     * @return
     * @throws IllegalStateException if at least one metric was added before
     */
    public GraphAnalyzer useAll() {
        checkEmptyMetrics();
        for (final GraphMetricsEnum m : GraphMetricsEnum.values()) {
            useMetric(m, null);
        }
        return this;
    }

    protected void checkEmptyMetrics() {
        checkState(metrics.isEmpty(),
            "The GraphAnalyzer already contains metrics, so the useAll function cannot be used.");
    }

    /**
     * Removes the metric parameter (see {@link GraphMetricsEnum}) from the
     * <em>metrics</em> collection. As a result, the metric won't be calculated
     * during the analysis.
     *
     * @param metric {@link GraphMetricsEnum} value
     * @return this
     * @throws IllegalStateException if the <em>metrics</em> collection is empty
     */
    public GraphAnalyzer omit(final GraphMetricsEnum metric) {
        checkState(!metrics.isEmpty(), "The GraphAnalyzer should contain metrics to omit " + metric.toString()
            + " from the analysis, but it is empty.");
        if (!metrics.containsKey(metric.toString())) {
            logger.warn("The GraphAnalyzer does not contain " + metric.toString() + " which is planned to be omitted.");
        } else {
            metrics.remove(metric.toString());
            logger.info("Omit the metric from the analysis: " + metric.toString());
        }
        return this;
    }

    /**
     * Returns a {@link Metric} object which is mapped to the
     * {@link GraphMetricsEnum} parameter and contained by the GraphAnalyzer. If
     * there is not any {@link Metric} object, returns <strong>null</strong>.
     *
     * @param metric {@link GraphMetricsEnum} value
     * @return {@link Metric} object or <strong>null</strong>
     */
    public GraphMetric getMetric(final GraphMetricsEnum metric) {
        return metrics.get(metric.toString());
    }

    /**
     * Evaluates every metric ({@link Metric}) which were added for the
     * analysis. The calculations are evaluated in the insertion order of
     * metrics. The adapter parameter ({@link GraphAdapter}) provides the
     * necessary interface for the calculations.
     *
     * @param adapter instance of {@link GraphAdapter}
     * @param <N>     the model
     * @return this
     */
    public <N, T> GraphAnalyzer evaluate(final GraphAdapter<N, T> adapter) {
        for (final GraphMetric m : metrics.values()) {
            m.evaluate(adapter);
        }
        return this;
    }

    /**
     * Evaluates the given {@link Metric} identified by the
     * {@link GraphMetricsEnum} parameter. The adapter parameter
     * ({@link GraphAdapter}) provides the necessary interface for the
     * calculations.
     *
     * @param adapter instance of {@link GraphAdapter}
     * @param metric  value in {@link GraphMetricsEnum}, identifies the
     *                {@link Metric} to evaluateT
     * @param <N>     type of the model
     * @return this
     */
    public <N, T> GraphAnalyzer evaluate(final GraphAdapter<N, T> adapter, final GraphMetricsEnum metric) {
        final GraphMetric metricObj = getMetric(metric);
        Preconditions.checkNotNull("The " + metric + " metric was not added to the analyzer.", metricObj);

        metricObj.evaluate(adapter);
        return this;
    }

}
