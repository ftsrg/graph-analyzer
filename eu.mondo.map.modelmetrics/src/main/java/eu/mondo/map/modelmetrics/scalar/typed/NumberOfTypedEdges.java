package eu.mondo.map.modelmetrics.scalar.typed;

import eu.mondo.map.core.metrics.typed.TypedScalarMetric;

public class NumberOfTypedEdges extends TypedScalarMetric<String, Integer> {

	@Override
	public String getName() {
		return "NumberOfTypedEdges";
	}

}
