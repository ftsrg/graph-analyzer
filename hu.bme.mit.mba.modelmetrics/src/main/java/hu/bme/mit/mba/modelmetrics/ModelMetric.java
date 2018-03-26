package hu.bme.mit.mba.modelmetrics;

import hu.bme.mit.mba.base.metrics.Metric;
import hu.bme.mit.mba.modeladapters.ModelAdapter;

public interface ModelMetric extends Metric {

    <N, T> void evaluate(final ModelAdapter<N, T> adapter);

    <N, T> void evaluate(final ModelAdapter<N, T> adapter, final N element);

}
