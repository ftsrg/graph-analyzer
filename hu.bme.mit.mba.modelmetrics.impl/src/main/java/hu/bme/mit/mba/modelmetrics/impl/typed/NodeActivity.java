package hu.bme.mit.mba.modelmetrics.impl.typed;

import hu.bme.mit.mba.base.data.ListData;
import hu.bme.mit.mba.modeladapters.ModelAdapter;
import hu.bme.mit.mba.modelmetrics.AbstractModelMetric;
import hu.bme.mit.mba.modelmetrics.incr.IncrementalModelEvaluator;

public class NodeActivity extends AbstractModelMetric<ListData<Integer>> implements IncrementalModelEvaluator {

    public NodeActivity() {
        super("NodeActivityList", new ListData<>());
    }

    @Override
    protected <N, T> void evaluateAll(ModelAdapter adapter) {
        evaluateEveryNode(adapter);
    }

    @Override
    public <N, T> void evaluate(ModelAdapter adapter, N element) {
        // evaluate(adapter, element);
        int numberOfTypes = adapter.getNumberOfTypes(element);
        data.add(numberOfTypes);
    }

    // protected <N, T> void evaluate(ModelAdapter adapter, N element) {
    // int numberOfTypes = adapter.getNumberOfTypes(element);
    // data.add(numberOfTypes);
    // }

    @Override
    public <N, T> void reevaluateNewEdge(ModelAdapter adapter, T type, N sourceNode, N targetNode) {
        evaluate(adapter, sourceNode);
        evaluate(adapter, targetNode);
    }

}
