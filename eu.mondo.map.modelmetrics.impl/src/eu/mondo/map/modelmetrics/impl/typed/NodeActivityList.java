package eu.mondo.map.modelmetrics.impl.typed;

import eu.mondo.map.base.data.ListData;
import eu.mondo.map.modeladapters.ModelAdapter;
import eu.mondo.map.modeladapters.TypedModelAdapter;
import eu.mondo.map.modelmetrics.AbstractModelMetric;

public class NodeActivityList extends AbstractModelMetric<ListData<Integer>> {

	public NodeActivityList() {
		super("NodeActivityList", new ListData<>());
	}

	// public void calculate(final Network<N> network) {
	// clear();
	// for (Node<N> node : network.getNodes()) {
	// values.add(node.getDimensions().keySet().size());
	// }
	// }

	@Override
	public <M, N, T> void evaluate(ModelAdapter<M, N, T> adapter) {
		evaluateEveryNode(adapter);
	}

	@Override
	public <M, N, T> void evaluate(ModelAdapter<M, N, T> adapter, N element) {
		evaluate(castAdapter(adapter), element);
	}

	protected <M, N, T> void evaluate(TypedModelAdapter<M, N, T> adapter, N element) {
		data.add(adapter.getNumberOfTypes(element));
	}

}
