package hu.bme.mit.mba.modelmetrics;

import hu.bme.mit.mba.base.metrics.Metric;
import hu.bme.mit.mba.modeladapters.GraphAdapter;

public interface GraphMetric extends Metric {

    <N, T> void evaluate(final GraphAdapter<N, T> adapter);

    <N, T> void evaluate(final GraphAdapter<N, T> adapter, final N element);

}
