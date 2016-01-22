package eu.mondo.map.core.metrics.models;

import eu.mondo.map.core.util.ListUtil;

public class NumberOfEdges extends AggregatedMetric<Integer> {

	public void calculate(final DegreeList degreeList) {
		value = ListUtil.summarize(degreeList.getValues());
	}
}
