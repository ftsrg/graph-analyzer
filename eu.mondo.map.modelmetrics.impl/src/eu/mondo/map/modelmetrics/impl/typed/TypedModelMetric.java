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
	public static <M, N, T> TypedModelAdapter<M, N, T> castAdapter(final ModelAdapter<M, N, T> adapter) {
		TypedModelAdapter<M, N, T> typedAdapter;
		if (adapter instanceof TypedModelAdapter<?, ?, ?>) {
			typedAdapter = (TypedModelAdapter<M, N, T>) adapter;
		} else {
			throw new IllegalArgumentException("The adapter must be an instance of TypedModelAdapter.");
		}

		return typedAdapter;
	}

	public static <M, N, T> void evaluateEveryNode(final ModelAdapter<M, N, T> adapter, final ModelEvaluator metric) {
		Iterator<N> iterator = adapter.getModelIterator();
		while (iterator.hasNext()) {
			metric.evaluate(adapter, iterator.next());
		}
	}

}
