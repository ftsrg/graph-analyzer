package eu.mondo.map.modelmetrics.impl.typed;

import java.util.Iterator;

import eu.mondo.map.modeladapters.ModelAdapter;
import eu.mondo.map.modeladapters.TypedModelAdapter;
import eu.mondo.map.modelmetrics.ModelEvaluator;

public class TypedModelMetric {

	/**
	 * Casts a {@link ModelAdapter} instance to a {@link TypedModelAdapter} if
	 * it is possible. If it is not, throws an {@link IllegalArgumentException}.
	 * 
	 * @param adapter
	 *            instance of {@link ModelAdapter}
	 * @param <M>
	 *            type of the model
	 * 
	 * @throws IllegalArgumentException
	 *             if the casting is not possible
	 * 
	 * @return the adapter parameter as a {@link TypedModelAdapter}
	 */
	public static <M> TypedModelAdapter<M> castAdapter(final ModelAdapter<M> adapter) {
		TypedModelAdapter<M> typedAdapter;
		if (adapter instanceof TypedModelAdapter<?>) {
			typedAdapter = (TypedModelAdapter<M>) adapter;
		} else {
			throw new IllegalArgumentException("The adapter must be an instance of TypedModelAdapter.");
		}

		return typedAdapter;
	}

	public static <M> void evaluateEveryNode(final ModelAdapter<M> adapter, final ModelEvaluator metric) {
		Iterator<Object> iterator = adapter.getModelIterator();
		while (iterator.hasNext()) {
			metric.evaluate(adapter, iterator.next());
		}
	}

}
