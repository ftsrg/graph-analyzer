package hu.bme.mit.mba.modelmetrics.impl.typed;

import hu.bme.mit.mba.base.data.ListData;
import hu.bme.mit.mba.base.data.MapData;
import hu.bme.mit.mba.modeladapters.ModelAdapter;
import hu.bme.mit.mba.modelmetrics.AbstractModelMetric;
import hu.bme.mit.mba.modelmetrics.incr.IncrementalModelEvaluator;

public class MultiplexParticipationCoefficient extends AbstractModelMetric<ListData<Double>>
        implements IncrementalModelEvaluator {

    public MultiplexParticipationCoefficient() {
        super("MultiplexParticipationCoefficient", new ListData<>());
    }

    @Override
    protected <N, T> void evaluateAll(ModelAdapter<N, T> adapter) {
        evaluateEveryNode(adapter);
    }

    // TODO delete later
    @Deprecated
    boolean exclusive = false;

    @Override
    public <N, T> void evaluate(ModelAdapter<N, T> adapter, N element) {
        int numOfDimensions = 0;
        if (exclusive) {
            numOfDimensions = adapter.getNumberOfTypes(element);
        } else {
            numOfDimensions = adapter.getNumberOfTypes();
        }

        double coef = 0.0;
        if (numOfDimensions == 1) {
            coef = 0.0;
        } else {
            for (T type : adapter.getTypes(element)) {
                int degree = adapter.getDegree(element, type);
                coef += Math.pow(adapter.getDegree(element, type) / (double) adapter.getDegree(element), 2.0);
            }
            coef = 1 - coef;
            coef = coef * numOfDimensions / (numOfDimensions - 1);
        }

        data.add(coef);
        updateTracing(element, coef);
    }

    @Override
    public <N, T> void trace() {
        tracing = new MapData<N, Double>();
    }

    @Override
    public <N, T> MapData<N, Double> getTracing() {
        return (MapData<N, Double>) tracing;
    }

    protected <N, T> void updateTracing(N node, Double value) {
        if (notNullTracing()) {
            getTracing().put(node, value);
        }
    }

    @Override
    public <N, T> void reevaluateNewEdge(ModelAdapter<N, T> adapter, T type, N sourceNode, N targetNode) {
        reevaluate(adapter, type, sourceNode);
        reevaluate(adapter, type, targetNode);
    }

    protected <N, T> void reevaluate(ModelAdapter<N, T> adapter, T type, N node) {
        if (!getTracing().containsKey(node)) {
            updateTracing(node, 0.0);
        } else {
            evaluate(adapter, node);
        }
    }

}
