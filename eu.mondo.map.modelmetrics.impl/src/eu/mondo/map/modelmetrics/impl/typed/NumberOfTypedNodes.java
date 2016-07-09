package eu.mondo.map.modelmetrics.impl.typed;

import eu.mondo.map.base.data.MapData;

public class NumberOfTypedNodes extends MapData<String, Integer> {

	public NumberOfTypedNodes() {
		super("NumberOfTypedNodes");
	}

	public void calculate(final TypedDegreeList degreeList) {
		for (String key : degreeList.getValues().keySet()) {
			typedValues.put(key, degreeList.getValues().get(key).size());
		}
	}

}
