package eu.mondo.map.modelmetrics.impl.typed;

import eu.mondo.map.core.metrics.typed.TypedListMetric;
import eu.mondo.map.modeladapters.ModelAdapter;
import eu.mondo.map.modeladapters.TypedModelAdapter;
import eu.mondo.map.modelmetrics.ModelEvaluator;

public class DimensionalDegreeList<N> extends TypedListMetric<String, Integer> implements ModelEvaluator {

	public DimensionalDegreeList() {
		super("DimensionalDegreeList");
	}

	// public void calculate(final Network<N> network) {
	// clear();
	// for (String dimension : network.getDimensions()) {
	// for (Node<N> node : network.getNodes()) {
	// typedValues.put(dimension, node.getNumberOfNeighbors(dimension));
	// }
	// }
	// }

	@Override
	protected boolean isSkippable(Integer value) {
		return value.equals(0);
	}

	@Override
	public <M> void evaluate(final ModelAdapter<M> adapter) {
		TypedModelAdapter<M> typedAdapter = TypedModelMetric.castAdapter(adapter);
		for (Object type : typedAdapter.getTypes()) {
			for (Object node : typedAdapter.getNodes()) {
				typedValues.put(type.toString(), typedAdapter.getDegree(node, type));
			}
		}
	}

	@Override
	public <M> void evaluate(final ModelAdapter<M> adapter, final Object element) {
		throw new UnsupportedOperationException("Cannot evaluate DimensionalDegreeList metric on an element.");
	}

}
