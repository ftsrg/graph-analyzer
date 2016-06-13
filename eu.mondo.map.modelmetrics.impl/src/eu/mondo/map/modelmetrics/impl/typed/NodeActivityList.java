package eu.mondo.map.modelmetrics.impl.typed;

import static eu.mondo.map.modelmetrics.impl.typed.TypedModelMetric.castAdapter;
import static eu.mondo.map.modelmetrics.impl.typed.TypedModelMetric.evaluateEveryNode;

import eu.mondo.map.core.metrics.ListMetric;
import eu.mondo.map.modeladapters.ModelAdapter;
import eu.mondo.map.modeladapters.TypedModelAdapter;
import eu.mondo.map.modelmetrics.ModelEvaluator;

public class NodeActivityList<N> extends ListMetric<Integer> implements ModelEvaluator {

	public NodeActivityList() {
		super("NodeActivityList");
	}

	// public void calculate(final Network<N> network) {
	// clear();
	// for (Node<N> node : network.getNodes()) {
	// values.add(node.getDimensions().keySet().size());
	// }
	// }

	@Override
	public <M> void evaluate(ModelAdapter<M> adapter) {
		evaluateEveryNode(adapter, this);
	}

	@Override
	public <M> void evaluate(ModelAdapter<M> adapter, Object element) {
		evaluate(castAdapter(adapter), element);
	}

	protected <M> void evaluate(TypedModelAdapter<M> adapter, Object element) {
		values.add(adapter.getNumberOfTypes(element));
	}

}
