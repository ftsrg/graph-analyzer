package eu.mondo.map.modelmetrics.impl.scalar;

import eu.mondo.map.core.graph.Network;
import eu.mondo.map.core.metrics.ScalarMetric;
import eu.mondo.map.modeladapters.ModelAdapter;
import eu.mondo.map.modelmetrics.ModelEvaluator;
import eu.mondo.map.modelmetrics.impl.composite.DegreeList;
import eu.mondo.map.modelmetrics.impl.composite.typed.TypedDegreeList;

public class NumberOfNodes extends ScalarMetric<Integer> implements ModelEvaluator {

	public NumberOfNodes() {
		super("NumberOfNodes");
	}

	public void calculate(final DegreeList degreeList) {
		value = degreeList.getValues().size();
	}

	public void calculate(final TypedDegreeList typedDegreeList) {
		value = 0;
		for (String key : typedDegreeList.getValues().keySet()) {
			value += typedDegreeList.getValues().get(key).size();
		}
	}

	public void calculate(final Network<?> network) {
		value = network.getNumberOfNodes();
	}

	@Override
	public void clear() {
		value = 0;
	}

	@Override
	public <M> void evaluate(ModelAdapter<M> adapter) {
		// TODO Auto-generated method stub

	}

	@Override
	public <M> void evaluate(ModelAdapter<M> adapter, Object element) {
		// TODO Auto-generated method stub

	}

}
