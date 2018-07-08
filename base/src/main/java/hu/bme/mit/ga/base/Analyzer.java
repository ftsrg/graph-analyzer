package hu.bme.mit.ga.base;

import com.google.common.collect.Lists;
import hu.bme.mit.ga.base.metrics.Metric;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class Analyzer<K, V extends Metric> {

    protected Map<K, V> metrics;

    public Analyzer() {
        metrics = new LinkedHashMap<>();
    }

    @Override
    public String toString() {
        return "Analyzer [metrics=" + metrics + "]";
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
