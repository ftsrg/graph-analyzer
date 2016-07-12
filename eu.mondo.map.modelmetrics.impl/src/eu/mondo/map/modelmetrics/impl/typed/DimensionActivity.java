package eu.mondo.map.modelmetrics.impl.typed;

import eu.mondo.map.base.data.MapData;
import eu.mondo.map.modeladapters.ModelAdapter;
import eu.mondo.map.modeladapters.TypedModelAdapter;
import eu.mondo.map.modelmetrics.AbstractModelMetric;

public class DimensionActivity extends AbstractModelMetric<MapData<String, Integer>> {

	public DimensionActivity() {
		super("DimensionActivity", new MapData<>());
	}

	@Override
	public <M, N, T> void evaluate(ModelAdapter<M, N, T> adapter) {
		TypedModelAdapter<M, N, T> typedAdapter = castAdapter(adapter);
		for (T type : typedAdapter.getTypes()) {
			data.put(type.toString(), typedAdapter.getNumberOfNodes(type));
		}
	}

}
