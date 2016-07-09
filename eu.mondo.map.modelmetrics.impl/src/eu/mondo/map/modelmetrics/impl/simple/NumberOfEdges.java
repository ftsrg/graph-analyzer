package eu.mondo.map.modelmetrics.impl.simple;

import eu.mondo.map.base.data.ScalarData;
import eu.mondo.map.modeladapters.ModelAdapter;
import eu.mondo.map.modelmetrics.ModelMetric;

public class NumberOfEdges extends ModelMetric<ScalarData<Integer>> {

	public NumberOfEdges() {
		super("NumberOfEdges", new ScalarData<>());
	}

	// public void calculate(final DegreeList degreeList) {
	// value = MathUtils.sumInt(degreeList.getValues());
	// }

	// public void calculate(final TypedDegreeList typedDegreeList) {
	// clear();
	// for (String key : typedDegreeList.getValues().keySet()) {
	// value += MathUtils.sumInt(typedDegreeList.getValues().get(key));
	// }
	// value /= 2;
	// }

	// public void calculate(final Network<?> network) {
	// value = network.getNumberOfEdges();
	// }

	@Override
	public void clear() {
		data.clear();
	}

	@Override
	/**
	 * Calculates the number of nodes in the graph.
	 */
	public <M, N, T> void evaluate(ModelAdapter<M, N, T> adapter) {
		data.setValue(adapter.getNumberOfEdges());
	}

	@Override
	/**
	 * Calculates the number of edges which belong to the element.
	 */
	public <M, N, T> void evaluate(ModelAdapter<M, N, T> adapter, N element) {
		data.setValue(adapter.getDegree(element));
	}

}
