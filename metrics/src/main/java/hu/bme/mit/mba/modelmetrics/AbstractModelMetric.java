package hu.bme.mit.mba.modelmetrics;

import hu.bme.mit.mba.base.data.BaseData;
import hu.bme.mit.mba.base.metrics.BaseMetric;
import hu.bme.mit.mba.modeladapters.ModelAdapter;

import java.util.*;

public abstract class AbstractModelMetric<D extends BaseData> extends BaseMetric<D> implements ModelMetric {

    public AbstractModelMetric(String defaultName, final D data) {
        super(defaultName);
        this.data = data;
    }

    @Override
    public <N, T> void evaluate(ModelAdapter<N, T> adapter, N element) {
        throw new UnsupportedOperationException("Cannot evaluate metric " + name + " on one element.");
    }

    @Override
    public <N, T> void evaluate(ModelAdapter<N, T> adapter) {
        evaluateAll(adapter);
    }

    protected abstract <N, T> void evaluateAll(ModelAdapter<N, T> adapter);

    protected <N, T> void evaluateEveryNode(final ModelAdapter<N, T> adapter) {
        Iterator<N> iterator = adapter.getIndexer().getModelIterator();
        while (iterator.hasNext()) {
            this.evaluate(adapter, iterator.next());
        }
    }

    abstract public List<Map<String, Object>> getTsvMaps(String[] header);

}
