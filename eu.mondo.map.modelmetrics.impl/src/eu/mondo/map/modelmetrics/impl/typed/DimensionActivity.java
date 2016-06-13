package eu.mondo.map.modelmetrics.impl.typed;

import static eu.mondo.map.modelmetrics.impl.typed.TypedModelMetric.castAdapter;

import eu.mondo.map.core.metrics.typed.TypedScalarMetric;
import eu.mondo.map.modeladapters.ModelAdapter;
import eu.mondo.map.modeladapters.TypedModelAdapter;
import eu.mondo.map.modelmetrics.ModelEvaluator;

public class DimensionActivity extends TypedScalarMetric<String, Integer> implements ModelEvaluator {

	public DimensionActivity() {
		super("DimensionActivity");
	}

	// public void calculate(final Network<?> network) {
	// clear();
	// for (String dimension : network.getNodesOnDimensions().keySet()) {
	// typedValues.put(dimension, network.getNumberOfNodes(dimension));
	// }
	// }

	@Override
	public <M> void evaluate(ModelAdapter<M> adapter) {
		TypedModelAdapter<M> typedAdapter = castAdapter(adapter);
		for (Object type : typedAdapter.getTypes()) {
			typedValues.put(type.toString(), typedAdapter.getNumberOfNodes(type));
		}
	}

	@Override
	public <M> void evaluate(ModelAdapter<M> adapter, Object element) {
		throw new UnsupportedOperationException("Cannot evaluate DimensionActivity metric on an element.");
	}

}
