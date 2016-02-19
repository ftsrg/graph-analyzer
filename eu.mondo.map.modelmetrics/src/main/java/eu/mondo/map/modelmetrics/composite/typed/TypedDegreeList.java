package eu.mondo.map.modelmetrics.composite.typed;

import eu.mondo.map.core.metrics.typed.TypedListMetric;

public abstract class TypedDegreeList extends TypedListMetric<String, Integer> {

	@Override
	public String getName() {
		return "TypedDegreeList";
	}

}
