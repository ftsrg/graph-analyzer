package hu.bme.mit.mba.modelmetrics;

import hu.bme.mit.mba.base.data.BaseData;
import hu.bme.mit.mba.base.metrics.BaseMetric;
import hu.bme.mit.mba.modeladapters.ModelAdapter;

import java.util.Iterator;

public abstract class AbstractModelMetric<D extends BaseData> extends BaseMetric<D> implements TraceableModelMetric {

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

    @Override
    public <N, T> void evaluate(ModelAdapter<N, T> adapter, N element) {
        throw new UnsupportedOperationException("Cannot evaluate metric " + name + " on one element.");
    }

    @Override
    public <N, T> void evaluate(ModelAdapter<N, T> adapter) {
        clear();
        evaluateAll(adapter);
    }

    protected abstract <N, T> void evaluateAll(ModelAdapter<N, T> adapter);

    protected <N, T> void evaluateEveryNode(final ModelAdapter<N, T> adapter) {
        Iterator<N> iterator = adapter.getModelIterator();
        while (iterator.hasNext()) {
            this.evaluate(adapter, iterator.next());
        }
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

    protected boolean notNullTracing() {
        return tracing != null;
    }

}
