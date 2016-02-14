package eu.mondo.map.modelmetrics.scalar;

import eu.mondo.map.core.metrics.ScalarMetric;
import eu.mondo.map.modelmetrics.composite.DegreeList;
import eu.mondo.map.modelmetrics.composite.typed.TypedDegreeList;

public class NumberOfNodes extends ScalarMetric<Integer> {

	public void calculate(final DegreeList degreeList) {
		value = degreeList.getValues().size();
	}

	public void calculate(final TypedDegreeList typedDegreeList) {
		value = 0;
		for (String key : typedDegreeList.getValues().keySet()) {
			value += typedDegreeList.getValues().get(key).size();
		}
	}

	@Override
	public String getName() {
		return "NumberOfNodes";
	}

	@Override
	public void clear() {
		value = 0;
	}

}
