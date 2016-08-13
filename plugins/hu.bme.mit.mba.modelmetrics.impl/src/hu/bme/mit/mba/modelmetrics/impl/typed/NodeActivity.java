package hu.bme.mit.mba.modelmetrics.impl.typed;

import hu.bme.mit.mba.base.data.ListData;
import hu.bme.mit.mba.modeladapters.ModelAdapter;
import hu.bme.mit.mba.modeladapters.TypedModelAdapter;
import hu.bme.mit.mba.modelmetrics.AbstractModelMetric;
import hu.bme.mit.mba.modelmetrics.incr.IncrementalModelEvaluator;

public class NodeActivity extends AbstractModelMetric<ListData<Integer>>implements IncrementalModelEvaluator {

    public NodeActivity() {
	super("NodeActivityList", new ListData<>());
    }

    @Override
    protected <N, T> void evaluateAll(ModelAdapter<N, T> adapter) {
	evaluateEveryNode(adapter);
    }

    @Override
    public <N, T> void evaluate(ModelAdapter<N, T> adapter, N element) {
	evaluate(castAdapter(adapter), element);
    }

    protected <N, T> void evaluate(TypedModelAdapter<N, T> adapter, N element) {
	int numberOfTypes = adapter.getNumberOfTypes(element);
	data.add(numberOfTypes);
    }

    @Override
    public <N, T> void reevaluateNewEdge(ModelAdapter<N, T> adapter, T type, N sourceNode, N targetNode) {
	evaluate(adapter, sourceNode);
	evaluate(adapter, targetNode);
    }

}
