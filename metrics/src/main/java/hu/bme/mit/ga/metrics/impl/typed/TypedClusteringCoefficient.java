package hu.bme.mit.ga.metrics.impl.typed;

import hu.bme.mit.ga.adapters.GraphAdapter;
import hu.bme.mit.ga.base.data.ListData;
import hu.bme.mit.ga.metrics.AbstractGraphMetric;

public abstract class TypedClusteringCoefficient extends AbstractGraphMetric<ListData<Double>> {

    protected int maxNeighbours = 1000;
    protected boolean useHeuristic = false;

    public TypedClusteringCoefficient(String defaultName) {
        super(defaultName, new ListData<>());
    }

    public int getMaxNeighbours() {
        return maxNeighbours;
    }

    public void setMaxNeighbours(int maxNeighbours) {
        this.maxNeighbours = maxNeighbours;
    }

    public boolean isUseHeuristic() {
        return useHeuristic;
    }

    public void setUseHeuristic(boolean useHeuristic) {
        this.useHeuristic = useHeuristic;
    }

    @Override
    public <N, T> void evaluate(final GraphAdapter<N, T> adapter) {
        for (N node : adapter.getIndexer().getNodes()) {
            evaluate(adapter, node);
        }
    }

    @Override
    protected <N, T> void evaluateAll(GraphAdapter<N, T> adapter) {
        evaluateEveryNode(adapter);
        evaluate(adapter);
    }
}
