package eu.mondo.map.modelmetrics.impl.simple;

import eu.mondo.map.base.data.ScalarData;
import eu.mondo.map.modeladapters.ModelAdapter;
import eu.mondo.map.modelmetrics.AbstractModelMetric;
import eu.mondo.map.modelmetrics.incr.IncrementalModelEvaluator;

public class NumberOfEdges extends AbstractModelMetric<ScalarData<Integer>>implements IncrementalModelEvaluator {

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
