package eu.mondo.map.core.metrics.models.scalar;

import eu.mondo.map.core.metrics.MultitypedScalarMetric;

public class NumberOfAttributes extends MultitypedScalarMetric<String, String, Integer> {

	@Override
	public String getName() {
		return "Attributes";
	}

}
