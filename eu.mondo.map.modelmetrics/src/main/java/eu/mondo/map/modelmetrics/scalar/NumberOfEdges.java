package eu.mondo.map.modelmetrics.scalar;

import eu.mondo.map.core.metrics.ScalarMetric;
import eu.mondo.map.modelmetrics.composite.DegreeList;
import eu.mondo.map.modelmetrics.composite.typed.TypedDegreeList;
import groovy.util.GroovyCollections;

public class NumberOfEdges extends ScalarMetric<Integer> {

	public void calculate(final DegreeList degreeList) {
		value = (Integer) GroovyCollections.sum(degreeList.getValues().toArray());
	}

	public void calculate(final TypedDegreeList typedDegreeList) {
		value = 0;
		for (String key : typedDegreeList.getValues().keySet()) {
			value += (Integer) GroovyCollections.sum(typedDegreeList.getValues().get(key)
					.toArray());
		}
	}

	@Override
	public String getName() {
		return "NumberOfEdges";
	}

	@Override
	public void clear() {
		value = 0;
	}
}
