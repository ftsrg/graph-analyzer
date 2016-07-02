package eu.mondo.map.modelmetrics;

import java.util.Map;

import eu.mondo.map.base.metrics.BaseMetric;
import eu.mondo.map.base.metrics.ListMetric;
import eu.mondo.map.modeladapters.ModelAdapter;

public class TraceableListModelMetric<V extends Number, M extends ListMetric<V> & ModelEvaluator, N> extends BaseMetric
		implements ModelEvaluator {

	protected Map<N, V> mapping;
	protected M metric;

	public TraceableListModelMetric(String defaultName) {
		super(defaultName);
	}

	@Override
	public void clear() {
		mapping.clear();
		metric.clear();
	}

	@Override
	public <M, N, T> void evaluate(ModelAdapter<M, N, T> adapter) {
		metric.evaluate(adapter);

	}

	@Override
	public <M, N, T> void evaluate(ModelAdapter<M, N, T> adapter, N element) {
		metric.evaluate(adapter, element);
	}

}
