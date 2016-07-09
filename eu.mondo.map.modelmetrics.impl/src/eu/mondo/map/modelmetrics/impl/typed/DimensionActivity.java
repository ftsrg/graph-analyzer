package eu.mondo.map.modelmetrics.impl.typed;

import eu.mondo.map.base.data.MapData;
import eu.mondo.map.modeladapters.ModelAdapter;
import eu.mondo.map.modeladapters.TypedModelAdapter;
import eu.mondo.map.modelmetrics.AbstractModelMetric;

public class DimensionActivity extends AbstractModelMetric<MapData<String, Integer>> {

	public DimensionActivity() {
		super("DimensionActivity", new MapData<>());
	}

	// public void calculate(final Network<?> network) {
	// clear();
	// for (String dimension : network.getNodesOnDimensions().keySet()) {
	// typedValues.put(dimension, network.getNumberOfNodes(dimension));
	// }
	// }

	@Override
	public <M, N, T> void evaluate(ModelAdapter<M, N, T> adapter) {
		TypedModelAdapter<M, N, T> typedAdapter = castAdapter(adapter);
		for (T type : typedAdapter.getTypes()) {
			data.put(type.toString(), typedAdapter.getNumberOfNodes(type));
		}
	}

	@Override
	public <M, N, T> void evaluate(ModelAdapter<M, N, T> adapter, N element) {
		throw new UnsupportedOperationException("Cannot evaluate DimensionActivity metric on an element.");
	}

}
