package eu.mondo.map.modelmetrics.impl.typed;

import eu.mondo.map.base.data.ListData;
import eu.mondo.map.modeladapters.ModelAdapter;
import eu.mondo.map.modeladapters.TypedModelAdapter;
import eu.mondo.map.modelmetrics.AbstractModelMetric;
import eu.mondo.map.modelmetrics.incr.IncrementalModelEvaluator;

public class NodeActivity extends AbstractModelMetric<ListData<Integer>>implements IncrementalModelEvaluator {

    public NodeActivity() {
	super("NodeActivityList", new ListData<>());
    }

    @Override
    protected <M, N, T> void evaluateAll(ModelAdapter<M, N, T> adapter) {
	evaluateEveryNode(adapter);
    }

    @Override
    public <M, N, T> void evaluate(ModelAdapter<M, N, T> adapter, N element) {
	evaluate(castAdapter(adapter), element);
    }

    protected <M, N, T> void evaluate(TypedModelAdapter<M, N, T> adapter, N element) {
	int numberOfTypes = adapter.getNumberOfTypes(element);
	data.add(numberOfTypes);
    }

    @Override
    public <M, N, T> void reevaluateNewEdge(ModelAdapter<M, N, T> adapter, T type, N sourceNode, N targetNode) {
	evaluate(adapter, sourceNode);
	evaluate(adapter, targetNode);
    }

}
