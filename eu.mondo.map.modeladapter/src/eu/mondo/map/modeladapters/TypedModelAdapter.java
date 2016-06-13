package eu.mondo.map.modeladapters;

import java.util.List;
import java.util.Set;

public abstract class TypedModelAdapter<M> extends ModelAdapter<M> {

	public Set<Object> getTypes() {
		throw new UnsupportedOperationException();
	}

	public Set<Object> getTypes(final Object element) {
		throw new UnsupportedOperationException();
	}

	public List<Object> getNodes(final Object type) {
		throw new UnsupportedOperationException();
	}

	public int getNumberOfTypes() {
		throw new UnsupportedOperationException();
	}

	public int getNumberOfTypes(final Object element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<Object> getNeighbors(final Object element) {
		throw new UnsupportedOperationException();
	}

	public Set<Object> getNeighbors(final Object element, final Object type) {
		throw new UnsupportedOperationException();
	}

	public int getDegree(final Object element, final Object type) {
		throw new UnsupportedOperationException();
	}

	public int getIndegree(final Object element, final Object type) {
		throw new UnsupportedOperationException();
	}

	public int getOutdegree(final Object element, final Object type) {
		throw new UnsupportedOperationException();
	}

	public int getNumberOfNodes(final Object type) {
		throw new UnsupportedOperationException();
	}

	public boolean isAdjacent(final Object source, final Object target, final Object type) {
		throw new UnsupportedOperationException();
	}

}
