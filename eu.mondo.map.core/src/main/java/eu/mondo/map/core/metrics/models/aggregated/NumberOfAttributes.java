package eu.mondo.map.core.metrics.models.aggregated;

import eu.mondo.map.core.metrics.MultitypedAggregatedMetric;

public class NumberOfAttributes extends MultitypedAggregatedMetric<String, String, Integer> {

	@Override
	public String getName() {
		return "Attributes";
	}

}
