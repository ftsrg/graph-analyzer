package eu.mondo.map.modelmetrics;

import eu.mondo.map.core.metrics.Metric;
import eu.mondo.map.modeladapters.ModelAdapter;

public interface ModelEvaluator extends Metric {

	public <M> void evaluate(final ModelAdapter<M> adapter);

	public <M> void evaluate(final ModelAdapter<M> adapter, final Object element);
}
