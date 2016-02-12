package eu.mondo.map.core.metrics.models.scalar;

import eu.mondo.map.core.metrics.TypedScalarMetric;
import eu.mondo.map.core.metrics.models.DegreeList;
import groovy.util.GroovyCollections;

public class NumberOfEdges extends TypedScalarMetric<String, Integer> {

	public void calculate(final DegreeList degreeList) {
		value = (Integer) GroovyCollections.sum(degreeList.getValues().toArray());
	}

	@Override
	public String getName() {
		return null;
	}
}
