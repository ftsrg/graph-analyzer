package eu.mondo.map.modeladapters;

import java.util.List;
import java.util.Set;

public abstract class TypedModelAdapter<M, N, T> extends ModelAdapter<M, N, T> {

	public Set<T> getTypes() {
		throw new UnsupportedOperationException();
	}

	public Set<T> getTypes(final N element) {
		throw new UnsupportedOperationException();
	}

	public List<N> getNodes(final T type) {
		throw new UnsupportedOperationException();
	}

	public int getNumberOfTypes() {
		throw new UnsupportedOperationException();
	}

	public int getNumberOfTypes(final N element) {
		throw new UnsupportedOperationException();
	}

	public Set<N> getNeighbors(final N element, final T type) {
		throw new UnsupportedOperationException();
	}

	public int getDegree(final N element, final T type) {
		throw new UnsupportedOperationException();
	}

	public int getIndegree(final N element, final T type) {
		throw new UnsupportedOperationException();
	}

	public int getOutdegree(final N element, final T type) {
		throw new UnsupportedOperationException();
	}

	public int getNumberOfNodes(final T type) {
		throw new UnsupportedOperationException();
	}

	public boolean isAdjacent(final N source, final N target, final T type) {
		throw new UnsupportedOperationException();
	}

}
