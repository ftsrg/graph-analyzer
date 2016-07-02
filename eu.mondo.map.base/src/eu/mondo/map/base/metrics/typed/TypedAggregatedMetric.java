package eu.mondo.map.base.metrics.typed;

import eu.mondo.map.base.util.MathUtils;

public class TypedAggregatedMetric<Type, M extends TypedListMetric<Type, ?>> extends TypedScalarMetric<Type, Double> {

	public TypedAggregatedMetric() {
		super("TypedAggregatedMetric");
	}

	public void calculateAverage(final TypedListMetric<Type, ? extends Number> list) {
		for (Type t : list.getValues().keySet()) {
			double sum = MathUtils.sumDouble(list.getValues().get(t));
			typedValues.put(t, sum / list.getValues().size());
		}
	}

}
