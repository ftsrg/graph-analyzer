package hu.bme.mit.mba.modelmetrics.impl.simple;

import hu.bme.mit.mba.base.data.ScalarData;
import hu.bme.mit.mba.modeladapters.ModelAdapter;
import hu.bme.mit.mba.modelmetrics.AbstractModelMetric;
import hu.bme.mit.mba.modelmetrics.incr.IncrementalModelEvaluator;

public class NumberOfNodes extends AbstractModelMetric<ScalarData<Integer>> implements IncrementalModelEvaluator {

    public NumberOfNodes() {
        super("NumberOfNodes", new ScalarData<>());
    }

    @Override
    public void clear() {
        data.setValue(0);
    }

    @Override
    protected <N, T> void evaluateAll(ModelAdapter<N, T> adapter) {
        data.setValue(adapter.getNumberOfNodes());
    }

    @Override
    public <N, T> void reevaluateNewEdge(ModelAdapter<N, T> adapter, T type, N sourceNode, N targetNode) {
        evaluateAll(adapter);
    }

}
