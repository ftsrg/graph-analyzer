package eu.mondo.map.modelmetrics.impl.simple;

import eu.mondo.map.base.metrics.ScalarMetric;
import eu.mondo.map.modeladapters.ModelAdapter;
import eu.mondo.map.modelmetrics.ModelEvaluator;
import eu.mondo.map.modelmetrics.impl.typed.TypedDegreeList;

public class NumberOfNodes extends ScalarMetric<Integer> implements ModelEvaluator {

	public NumberOfNodes() {
		super("NumberOfNodes");
	}

	public void calculate(final DegreeList degreeList) {
		value = degreeList.getValues().size();
	}

	public void calculate(final TypedDegreeList typedDegreeList) {
		value = 0;
		for (String key : typedDegreeList.getValues().keySet()) {
			value += typedDegreeList.getValues().get(key).size();
		}
	}

	// public void calculate(final Network<?> network) {
	// value = network.getNumberOfNodes();
	// }

	@Override
	public void clear() {
		value = 0;
	}

	@Override
	public <M, N, T> void evaluate(ModelAdapter<M, N, T> adapter) {
		value = adapter.getNumberOfNodes();
	}

	@Override
	public <M, N, T> void evaluate(ModelAdapter<M, N, T> adapter, N element) {
		throw new UnsupportedOperationException("Cannot evaluate NumberOfNodes metric on an element.");
	}

}
