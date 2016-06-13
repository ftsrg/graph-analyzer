package eu.mondo.map.modeladapters;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public abstract class ModelAdapter<M, N, T> {

	protected M model;
	protected ModelIndexer<N, T> indexer;

	public abstract Iterator<N> getModelIterator();

	public abstract void init(final M model);

	public List<N> getNodes() {
		throw new UnsupportedOperationException();
	}

	public int getNumberOfNodes() {
		throw new UnsupportedOperationException();
	}

	public int getNumberOfEdges() {
		throw new UnsupportedOperationException();
	}

	public int getDegree(final N element) {
		throw new UnsupportedOperationException();
	}

	public int getIndegree(final N element) {
		throw new UnsupportedOperationException();
	}

	public int getOutdegree(final N element) {
		throw new UnsupportedOperationException();
	}

	public Set<N> getNeighbors(final N element) {
		throw new UnsupportedOperationException();
	}

	public M getModel() {
		return model;
	}

	public boolean isAdjacent(final N source, final N target) {
		throw new UnsupportedOperationException();
	}

	public ModelIndexer<N, T> getIndexer() {
		return indexer;
	}

	public void setIndexer(ModelIndexer<N, T> indexer) {
		this.indexer = indexer;
	}

}
