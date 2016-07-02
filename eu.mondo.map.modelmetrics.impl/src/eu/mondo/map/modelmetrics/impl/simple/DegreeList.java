package eu.mondo.map.modelmetrics.impl.simple;

import static eu.mondo.map.modelmetrics.impl.typed.TypedModelMetric.evaluateEveryNode;

import eu.mondo.map.base.metrics.ListMetric;
import eu.mondo.map.modeladapters.ModelAdapter;
import eu.mondo.map.modelmetrics.ModelEvaluator;

public class DegreeList extends ListMetric<Integer> implements ModelEvaluator {

	public DegreeList() {
		super("DegreeList");
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
		evaluateEveryNode(adapter, this);
	}

	@Override
	/**
	 * <p>
	 * Calculates the degree for the element.
	 * </p>
	 */
	public <M, N, T> void evaluate(ModelAdapter<M, N, T> adapter, N element) {
		values.add(adapter.getDegree(element));
	}

}
