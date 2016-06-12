package eu.mondo.map.modelmetrics;

import eu.mondo.map.modeladapters.ModelAdapter;

public abstract class AbstractModelMetric {

	public abstract <M> void calculate(final ModelAdapter<M> adapter);

	public abstract <M> void calculate(final ModelAdapter<M> adapter, final Object element);
}
