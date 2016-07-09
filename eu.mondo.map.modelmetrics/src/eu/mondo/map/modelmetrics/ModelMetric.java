package eu.mondo.map.modelmetrics;

import eu.mondo.map.base.metrics.Metric;
import eu.mondo.map.modeladapters.ModelAdapter;

public interface ModelMetric extends Metric {

	public <M, N, T> void evaluate(final ModelAdapter<M, N, T> adapter);

	public <M, N, T> void evaluate(final ModelAdapter<M, N, T> adapter, final N element);
}
