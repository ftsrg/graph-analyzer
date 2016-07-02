package eu.mondo.map.modelmetrics.impl.typed;

import static eu.mondo.map.modelmetrics.impl.typed.TypedModelMetric.castAdapter;
import static eu.mondo.map.modelmetrics.impl.typed.TypedModelMetric.evaluateEveryNode;

import eu.mondo.map.base.metrics.ListMetric;
import eu.mondo.map.modeladapters.ModelAdapter;
import eu.mondo.map.modeladapters.TypedModelAdapter;
import eu.mondo.map.modelmetrics.ModelEvaluator;

public class NodeActivityList extends ListMetric<Integer> implements ModelEvaluator {

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
	public <M, N, T> void evaluate(ModelAdapter<M, N, T> adapter) {
		evaluateEveryNode(adapter, this);
	}

	@Override
	public <M, N, T> void evaluate(ModelAdapter<M, N, T> adapter, N element) {
		evaluate(castAdapter(adapter), element);
	}

	protected <M, N, T> void evaluate(TypedModelAdapter<M, N, T> adapter, N element) {
		values.add(adapter.getNumberOfTypes(element));
	}

}
