package eu.mondo.map.modelmetrics;

import eu.mondo.map.base.metrics.Metric;
import eu.mondo.map.modeladapters.ModelAdapter;

public interface ModelMetric extends Metric {

    public <N, T> void evaluate(final ModelAdapter<N, T> adapter);

    public <N, T> void evaluate(final ModelAdapter<N, T> adapter, final N element);
}
