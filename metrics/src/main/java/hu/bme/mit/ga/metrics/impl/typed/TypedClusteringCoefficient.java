package hu.bme.mit.ga.metrics.impl.typed;

import hu.bme.mit.ga.adapters.GraphAdapter;
import hu.bme.mit.ga.base.data.ListData;
import hu.bme.mit.ga.metrics.AbstractGraphMetric;

public abstract class TypedClusteringCoefficient extends AbstractGraphMetric<ListData<Double>> {

    protected int maxNeighbours = 1000;
    protected boolean useHeuristic = false;

    public TypedClusteringCoefficient(String name) {
        super(name, new ListData<>());
    }

    @Override
    protected <N, T> void evaluateAll(GraphAdapter<N, T> adapter) {
        evaluateEveryNode(adapter);
        evaluate(adapter);
    }

}
