package eu.mondo.map.modelmetrics.impl.composite;

import java.util.Iterator;

import eu.mondo.map.core.metrics.ListMetric;
import eu.mondo.map.modeladapters.ModelAdapter;
import eu.mondo.map.modelmetrics.ModelEvaluator;

public class DegreeList<N> extends ListMetric<Integer> implements ModelEvaluator {

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
	public <M> void evaluate(ModelAdapter<M> adapter) {
		Iterator<Object> iterator = adapter.getModelIterator();
		while (iterator.hasNext()) {
			evaluate(adapter, iterator.next());
		}
	}

	@Override
	/**
	 * <p>
	 * Calculates the degree for the element.
	 * </p>
	 */
	public <M> void evaluate(ModelAdapter<M> adapter, Object element) {
		values.add(adapter.getDegree(element));
	}

}
