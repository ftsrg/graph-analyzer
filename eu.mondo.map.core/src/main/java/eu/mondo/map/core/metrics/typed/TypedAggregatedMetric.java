package eu.mondo.map.core.metrics.typed;

import groovy.util.GroovyCollections;

public class TypedAggregatedMetric<Type, M extends TypedListMetric<Type, ?>> extends
		TypedScalarMetric<Type, Double> {

	@Override
	public String getName() {
		return "TypedAggregatedMetric";
	}

	public void calculateAverage(final TypedListMetric<Type, ?> list) {
		for (Type t : list.getValues().keySet()) {
			double sum = (double) GroovyCollections.sum(list.getValues().get(t).toArray());
			typedValues.put(t, sum / list.getValues().size());
		}
	}

}
