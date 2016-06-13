package eu.mondo.map.modeladapters;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;

public abstract class ModelAdapter<M, N, T> {

	protected M model;
	protected ModelIndexer<N, T> indexer;

	public abstract Iterator<N> getModelIterator();

	public abstract void init(final M model);

	public List<N> getNodes() {
		return indexer.getNodes();
	}

	public int getNumberOfNodes() {
		return indexer.getNodes().size();
	}

	public int getNumberOfEdges() {
		return indexer.getNumberOfAddedEdges();
	}

	public int getDegree(final N element) {
		return getIndegree(element) + getOutdegree(element);
	}

	public int getIndegree(final N element) {
		return indexer.getNodeIndex().column(element).size();
	}

	public int getOutdegree(final N element) {
		return indexer.getNodeIndex().row(element).size();
	}

	public Set<N> getNeighbors(final N element) {
		return Sets.union(getIncomingNeighbors(element), getOutgoingNeighbors(element));
	}

	public Set<N> getIncomingNeighbors(final N element) {
		return indexer.getNodeIndex().column(element).keySet();
	}

	public Set<N> getOutgoingNeighbors(final N element) {
		return indexer.getNodeIndex().row(element).keySet();
	}

	public M getModel() {
		return model;
	}

	public boolean isAdjacent(final N source, final N target) {
		return indexer.isAdjacentBidirectional(source, target);
	}

	public ModelIndexer<N, T> getIndexer() {
		return indexer;
	}

	public void setIndexer(ModelIndexer<N, T> indexer) {
		this.indexer = indexer;
	}

}
