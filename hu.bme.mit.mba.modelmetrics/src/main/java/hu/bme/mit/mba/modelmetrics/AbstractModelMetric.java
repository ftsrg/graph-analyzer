package hu.bme.mit.mba.modelmetrics;

import java.util.Set;

import hu.bme.mit.mba.base.data.BaseData;
import hu.bme.mit.mba.base.metrics.BaseMetric;
import hu.bme.mit.mba.modeladapters.ModelAdapter;

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
    public <N, T> void evaluate(ModelAdapter adapter, N element) {
        throw new UnsupportedOperationException("Cannot evaluate metric " + name + " on one element.");
    }

    @Override
    public <N, T> void evaluate(ModelAdapter adapter) {
        clear();
        evaluateAll(adapter);
    }

    protected abstract <N, T> void evaluateAll(ModelAdapter adapter);

    protected <N, T> void evaluateEveryNode(final ModelAdapter adapter) {
        Set<N> nodes = adapter.<N, T>getNodes();
        nodes.forEach(n -> evaluate(adapter, n));
    }

    // protected <N, T> TypedModelAdapter castAdapter(final ModelAdapter
    // adapter) {
    // TypedModelAdapter typedAdapter;
    // if (adapter instanceof TypedModelAdapter<?, ?>) {
    // typedAdapter = (TypedModelAdapter) adapter;
    // } else {
    // throw new IllegalArgumentException("The adapter must be an instance of
    // TypedModelAdapter.");
    // }
    // return typedAdapter;
    // }

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
