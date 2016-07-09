package eu.mondo.map.modelmetrics;

import java.util.Iterator;

import eu.mondo.map.base.data.BaseData;
import eu.mondo.map.base.metrics.BaseMetric;
import eu.mondo.map.modeladapters.ModelAdapter;
import eu.mondo.map.modeladapters.TypedModelAdapter;

public abstract class AbstractModelMetric<D extends BaseData> extends BaseMetric<D> implements ModelMetric, TraceableModelMetric {

	protected BaseData tracing;

	public AbstractModelMetric(String defaultName, final D data) {
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

	// public abstract <M, N, T> void evaluate(final ModelAdapter<M, N, T>
	// adapter);
	//

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

	@Override
	public <N, T> void trace() {
		throw new UnsupportedOperationException("Tracing is not supported in metric " + name + ". To enable it, " + name
				+ " must override trace method.");
	}

	@Override
	public <N, T> BaseData getTracing() {
		return tracing;
	}

}
