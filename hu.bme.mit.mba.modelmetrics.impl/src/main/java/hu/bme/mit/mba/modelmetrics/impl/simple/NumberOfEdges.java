package hu.bme.mit.mba.modelmetrics.impl.simple;

import hu.bme.mit.mba.base.data.ScalarData;
import hu.bme.mit.mba.modeladapters.ModelAdapter;
import hu.bme.mit.mba.modelmetrics.AbstractModelMetric;
import hu.bme.mit.mba.modelmetrics.incr.IncrementalModelEvaluator;

public class NumberOfEdges extends AbstractModelMetric<ScalarData<Integer>> implements IncrementalModelEvaluator {

    public NumberOfEdges() {
        super("NumberOfEdges", new ScalarData<>());
    }

    @Override
    public void clear() {
        data.clear();
    }

    @Override
    protected <N, T> void evaluateAll(ModelAdapter<N, T> adapter) {
        data.setValue(adapter.getNumberOfEdges());
    }

    @Override
    public <N, T> void evaluate(ModelAdapter<N, T> adapter, N element) {
        data.setValue(adapter.getDegree(element));
    }

    @Override
    public <N, T> void reevaluateNewEdge(ModelAdapter<N, T> adapter, T type, N sourceNode, N targetNode) {
        Integer value = data.getValue();
        value++;
        data.setValue(value);
    }

}
