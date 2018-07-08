package hu.bme.mit.ga.metrics;

import hu.bme.mit.ga.base.metrics.Metric;
import hu.bme.mit.ga.adapters.GraphAdapter;

public interface GraphMetric extends Metric {

    <N, T> void evaluate(final GraphAdapter<N, T> adapter);

    <N, T> void evaluate(final GraphAdapter<N, T> adapter, final N element);

}
