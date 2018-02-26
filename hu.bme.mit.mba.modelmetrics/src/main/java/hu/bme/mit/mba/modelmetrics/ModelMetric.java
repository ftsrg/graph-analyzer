package hu.bme.mit.mba.modelmetrics;

import hu.bme.mit.mba.base.metrics.Metric;
import hu.bme.mit.mba.modeladapters.ModelAdapter;

public interface ModelMetric extends Metric {

    public <N, T> void evaluate(final ModelAdapter adapter);

    public <N, T> void evaluate(final ModelAdapter adapter, final N element);
}
