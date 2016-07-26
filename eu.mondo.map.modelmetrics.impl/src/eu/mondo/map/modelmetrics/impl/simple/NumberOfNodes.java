package eu.mondo.map.modelmetrics.impl.simple;

import eu.mondo.map.base.data.ScalarData;
import eu.mondo.map.modeladapters.ModelAdapter;
import eu.mondo.map.modelmetrics.AbstractModelMetric;
import eu.mondo.map.modelmetrics.incr.IncrementalModelEvaluator;

public class NumberOfNodes extends AbstractModelMetric<ScalarData<Integer>>implements IncrementalModelEvaluator {

    public NumberOfNodes() {
	super("NumberOfNodes", new ScalarData<>());
    }

    @Override
    public void clear() {
	data.setValue(0);
    }

    @Override
    protected <M, N, T> void evaluateAll(ModelAdapter<M, N, T> adapter) {
	data.setValue(adapter.getNumberOfNodes());
    }

    @Override
    public <M, N, T> void reevaluateNewEdge(ModelAdapter<M, N, T> adapter, T type, N sourceNode, N targetNode) {
	evaluateAll(adapter);
    }

}
