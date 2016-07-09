package eu.mondo.map.modelmetrics.impl.typed;

import eu.mondo.map.base.data.MappedListData;
import eu.mondo.map.modeladapters.ModelAdapter;
import eu.mondo.map.modeladapters.TypedModelAdapter;
import eu.mondo.map.modelmetrics.AbstractModelMetric;

public class DimensionalDegreeList extends AbstractModelMetric<MappedListData<String, Integer>> {

	public DimensionalDegreeList() {
		super("DimensionalDegreeList", new MappedListData<>());
	}

	// public void calculate(final Network<N> network) {
	// clear();
	// for (String dimension : network.getDimensions()) {
	// for (Node<N> node : network.getNodes()) {
	// typedValues.put(dimension, node.getNumberOfNeighbors(dimension));
	// }
	// }
	// }

	// @Override
	// protected boolean isSkippable(Integer value) {
	// return value.equals(0);
	// }

	@Override
	public <M, N, T> void evaluate(final ModelAdapter<M, N, T> adapter) {
		TypedModelAdapter<M, N, T> typedAdapter = castAdapter(adapter);
		for (T type : typedAdapter.getTypes()) {
			for (N node : typedAdapter.getNodes(type)) {
				data.put(type.toString(), typedAdapter.getDegree(node, type));
			}
		}
	}

	@Override
	public <M, N, T> void evaluate(final ModelAdapter<M, N, T> adapter, final N element) {
		throw new UnsupportedOperationException("Cannot evaluate DimensionalDegreeList metric on an element.");
	}

}
