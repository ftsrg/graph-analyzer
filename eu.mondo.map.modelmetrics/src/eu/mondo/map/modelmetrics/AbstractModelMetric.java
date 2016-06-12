package eu.mondo.map.modelmetrics;

import eu.mondo.map.core.metrics.Metric;
import eu.mondo.map.modeladapters.ModelAdapter;

public abstract class AbstractModelMetric extends Metric {

	public AbstractModelMetric(String defaultName) {
		super(defaultName);
	}

	public abstract <M> void calculate(final ModelAdapter<M> adapter);
}
