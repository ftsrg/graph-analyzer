package eu.mondo.map.core.metrics.models.aggregated;

import eu.mondo.map.core.metrics.TypedAggregatedMetric;
import eu.mondo.map.core.metrics.models.DegreeList;
import groovy.util.GroovyCollections;

public class NumberOfEdges extends TypedAggregatedMetric<String, Integer> {

	public void calculate(final DegreeList degreeList) {
		value = (Integer) GroovyCollections.sum(degreeList.getValues().toArray());
	}

	@Override
	public String getName() {
		return null;
	}
}
