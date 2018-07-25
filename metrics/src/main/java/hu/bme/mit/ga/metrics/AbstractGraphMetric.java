package hu.bme.mit.ga.metrics;

import hu.bme.mit.ga.base.data.BaseData;
import hu.bme.mit.ga.base.metrics.BaseMetric;
import hu.bme.mit.ga.adapters.GraphAdapter;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class AbstractGraphMetric<D extends BaseData> extends BaseMetric<D> implements GraphMetric {

    public AbstractGraphMetric(String name, final D data) {
        super(name);
        this.data = data;
    }

    @Override
    public <N, T> void evaluate(GraphAdapter<N, T> adapter, N element) {
        throw new UnsupportedOperationException("Cannot evaluate metric " + getName() + " on one element.");
    }

    @Override
    public <N, T> void evaluate(GraphAdapter<N, T> adapter) {
        evaluateAll(adapter);
    }

    protected abstract <N, T> void evaluateAll(GraphAdapter<N, T> adapter);

    protected <N, T> void evaluateEveryNode(final GraphAdapter<N, T> adapter) {
        Iterator<N> iterator = adapter.getIndexer().getModelIterator();
        while (iterator.hasNext()) {
            this.evaluate(adapter, iterator.next());
        }
    }

    abstract public List<Map<String, Object>> getTsvMaps(String[] header);

}
