package eu.mondo.map.modelmetrics.impl.typed;

import eu.mondo.map.core.graph.Network;
import eu.mondo.map.core.metrics.typed.TypedScalarMetric;

public class DimensionActivity extends TypedScalarMetric<String, Integer> {

	public DimensionActivity() {
		super("DimensionActivity");
	}

	public void calculate(final Network<?> network) {
		clear();
		for (String dimension : network.getNodesOnDimensions().keySet()) {
			typedValues.put(dimension, network.getNumberOfNodes(dimension));
		}
	}

}
