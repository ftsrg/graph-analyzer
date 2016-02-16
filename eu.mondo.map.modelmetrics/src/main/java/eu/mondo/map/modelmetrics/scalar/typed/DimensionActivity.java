package eu.mondo.map.modelmetrics.scalar.typed;

import eu.mondo.map.core.graph.Network;
import eu.mondo.map.core.metrics.typed.TypedScalarMetric;

public class DimensionActivity extends TypedScalarMetric<String, Integer> {

	@Override
	public String getName() {
		return "DimensionActivity";
	}

	public void calculate(final Network<?> network) {
		for (String dimension : network.getNodesOnDimensions().keySet()) {
			typedValues.put(dimension, network.getNumberOfNodes(dimension));
		}
	}

}
