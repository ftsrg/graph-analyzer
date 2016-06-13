package eu.mondo.map.modeladapters;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public abstract class ModelAdapter<M> {

	protected M model;
	protected ModelIndexer indexer;

	public abstract Iterator<Object> getModelIterator();

	public abstract void initModel(final M model);

	public List<Object> getNodes() {
		throw new UnsupportedOperationException();
	}

	public List<Object> getEdges() {
		throw new UnsupportedOperationException();
	}

	public int getNumberOfNodes() {
		throw new UnsupportedOperationException();
	}

	public int getNumberOfEdges() {
		throw new UnsupportedOperationException();
	}

	public int getDegree(final Object element) {
		throw new UnsupportedOperationException();
	}

	public int getIndegree(final Object element) {
		throw new UnsupportedOperationException();
	}

	public int getOutdegree(final Object element) {
		throw new UnsupportedOperationException();
	}

	public Set<Object> getNeighbors(final Object element) {
		throw new UnsupportedOperationException();
	}

	public M getModel() {
		return model;
	}

	public boolean isAdjacent(final Object source, final Object target) {
		throw new UnsupportedOperationException();
	}

	public ModelIndexer getIndexer() {
		return indexer;
	}

	public void setIndexer(ModelIndexer indexer) {
		this.indexer = indexer;
	}

}
