package eu.mondo.map.core.metrics.models.aggregated;

import eu.mondo.map.core.metrics.TypedAggregatedMetric;
import eu.mondo.map.core.metrics.models.DegreeList;
import eu.mondo.map.core.util.ListUtil;

public class NumberOfEdges extends TypedAggregatedMetric<String, Integer> {

	public void calculate(final DegreeList degreeList) {
		value = ListUtil.summarize(degreeList.getValues());
	}

	@Override
	public String getName() {
		return null;
	}
}
