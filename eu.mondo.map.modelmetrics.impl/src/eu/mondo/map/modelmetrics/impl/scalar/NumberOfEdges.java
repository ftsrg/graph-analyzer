package eu.mondo.map.modelmetrics.impl.scalar;

import eu.mondo.map.core.metrics.ScalarMetric;
import eu.mondo.map.core.util.MathUtils;
import eu.mondo.map.modeladapters.ModelAdapter;
import eu.mondo.map.modelmetrics.ModelEvaluator;
import eu.mondo.map.modelmetrics.impl.composite.typed.TypedDegreeList;

public class NumberOfEdges extends ScalarMetric<Integer> implements ModelEvaluator {

	public NumberOfEdges() {
		super("NumberOfEdges");
	}

	// public void calculate(final DegreeList degreeList) {
	// value = MathUtils.sumInt(degreeList.getValues());
	// }

	public void calculate(final TypedDegreeList typedDegreeList) {
		clear();
		for (String key : typedDegreeList.getValues().keySet()) {
			value += MathUtils.sumInt(typedDegreeList.getValues().get(key));
		}
		value /= 2;
	}

	// public void calculate(final Network<?> network) {
	// value = network.getNumberOfEdges();
	// }

	@Override
	public void clear() {
		value = 0;
	}

	@Override
	/**
	 * Calculates the number of nodes in the graph.
	 */
	public <M> void evaluate(ModelAdapter<M> adapter) {
		value = adapter.getNumberOfEdges();
	}

	@Override
	/**
	 * Calculates the number of edges which belong to the element.
	 */
	public <M> void evaluate(ModelAdapter<M> adapter, Object element) {
		value = adapter.getDegree(element);
	}

}
