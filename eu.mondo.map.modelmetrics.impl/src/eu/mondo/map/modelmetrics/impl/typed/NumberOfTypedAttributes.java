package eu.mondo.map.modelmetrics.impl.typed;

import eu.mondo.map.core.metrics.typed.MultitypedScalarMetric;

public class NumberOfTypedAttributes extends MultitypedScalarMetric<String, String, Integer> {

	public NumberOfTypedAttributes(String defaultName) {
		super(defaultName);
	}

}
