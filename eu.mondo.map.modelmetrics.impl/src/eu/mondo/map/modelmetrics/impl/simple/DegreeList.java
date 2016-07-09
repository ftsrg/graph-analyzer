package eu.mondo.map.modelmetrics.impl.simple;

import eu.mondo.map.base.data.ListData;
import eu.mondo.map.modeladapters.ModelAdapter;
import eu.mondo.map.modelmetrics.AbstractModelMetric;

public class DegreeList extends AbstractModelMetric<ListData<Integer>> {

	public DegreeList() {
		super("DegreeList", new ListData<>());
	}

	// public void calculate(final Network<N> network) {
	// for (Node<N> node : network.getNodes()) {
	// values.add(node.getDegree());
	// }
	// }

	@Override
	/**
	 * <p>
	 * Calculates the number of degree for every node. The previously calculated
	 * values are not replaced.
	 * </p>
	 */
	public <M, N, T> void evaluate(ModelAdapter<M, N, T> adapter) {
		evaluateEveryNode(adapter);
	}

	@Override
	/**
	 * <p>
	 * Calculates the degree for the element.
	 * </p>
	 */
	public <M, N, T> void evaluate(ModelAdapter<M, N, T> adapter, N element) {
		data.add(adapter.getDegree(element));
	}

}
