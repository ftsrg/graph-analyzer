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
