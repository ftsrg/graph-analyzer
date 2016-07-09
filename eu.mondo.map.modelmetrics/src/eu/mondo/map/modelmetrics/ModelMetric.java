package eu.mondo.map.modelmetrics;

import java.util.Iterator;

import eu.mondo.map.base.data.BaseData;
import eu.mondo.map.base.metrics.BaseMetric;
import eu.mondo.map.modeladapters.ModelAdapter;
import eu.mondo.map.modeladapters.TypedModelAdapter;

public abstract class ModelMetric<D extends BaseData> extends BaseMetric<D> {

	protected BaseData tracing;

	public ModelMetric(String defaultName, final D data) {
		super(defaultName);
		this.data = data;
	}

	@Override
	public void clear() {
		super.clear();
		if (tracing != null) {
			tracing.clear();
		}

	}

	public abstract <M, N, T> void evaluate(final ModelAdapter<M, N, T> adapter);

	public abstract <M, N, T> void evaluate(final ModelAdapter<M, N, T> adapter, final N element);

	protected <M, N, T> void evaluateEveryNode(final ModelAdapter<M, N, T> adapter) {
		Iterator<N> iterator = adapter.getModelIterator();
		while (iterator.hasNext()) {
			this.evaluate(adapter, iterator.next());
		}
	}

	protected <M, N, T> TypedModelAdapter<M, N, T> castAdapter(final ModelAdapter<M, N, T> adapter) {
		TypedModelAdapter<M, N, T> typedAdapter;
		if (adapter instanceof TypedModelAdapter<?, ?, ?>) {
			typedAdapter = (TypedModelAdapter<M, N, T>) adapter;
		} else {
			throw new IllegalArgumentException("The adapter must be an instance of TypedModelAdapter.");
		}
		return typedAdapter;
	}

	public <N, T> BaseData getTracing() {
		return tracing;
	}

}
