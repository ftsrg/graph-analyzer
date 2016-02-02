package eu.mondo.map.core.metrics.models.aggregated;

import eu.mondo.map.core.metrics.TypedAggregatedMetric;
import eu.mondo.map.core.metrics.models.DegreeList;

public class NumberOfNodes extends TypedAggregatedMetric<String, Integer> {

	public void calculate(final DegreeList degreeList) {
		value = degreeList.getValues().size();
	}

	public void calculateOfTypes(final DegreeList degreeList) {
		if (degreeList.getTypedValues().isEmpty()) {
			throw new IllegalArgumentException(
					"The typedValues field is empty in the degreeList parameter.");
		}
		for (String key : degreeList.getTypedValues().keys()) {
			typedValues.put(key, degreeList.getTypedValues().get(key).size());
		}
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
