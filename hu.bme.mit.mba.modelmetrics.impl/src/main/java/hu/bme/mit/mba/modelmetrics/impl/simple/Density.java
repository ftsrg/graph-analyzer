package hu.bme.mit.mba.modelmetrics.impl.simple;

import hu.bme.mit.mba.base.data.ScalarData;
import hu.bme.mit.mba.modeladapters.ModelAdapter;
import hu.bme.mit.mba.modelmetrics.AbstractModelMetric;
import hu.bme.mit.mba.modelmetrics.incr.IncrementalModelEvaluator;

public class Density extends AbstractModelMetric<ScalarData<Double>> implements IncrementalModelEvaluator {

    public Density() {
        super("Density", new ScalarData<>());
    }

    @Override
    protected <N, T> void evaluateAll(ModelAdapter<N, T> adapter) {
        int numOfNodes = adapter.getNumberOfNodes();
        Double value = adapter.getNumberOfEdges() / (double) numOfNodes;
        value /= numOfNodes - 1;

        data.setValue(value);
    }

    @Override
    public <N, T> void reevaluateNewEdge(ModelAdapter<N, T> adapter, T type, N sourceNode, N targetNode) {
        evaluateAll(adapter);
    }

}
