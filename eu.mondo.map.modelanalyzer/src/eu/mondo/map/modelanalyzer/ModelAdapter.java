package eu.mondo.map.modelanalyzer;

import java.util.Iterator;

public abstract class ModelAdapter<M> {

	protected M model;
	protected ModelBaseIndexer indexer;

	public abstract Iterator<Object> getModelIterator();

	public abstract void initModel(final M model);

	public int getNumberOfNodes() {
		return 0;
	}

	public int getNumberOfEdges() {
		return 0;
	}

	public M getModel() {
		return model;
	}

	public boolean isAdjacent(final Object source, final Object target) {
		return false;
	}

	public ModelBaseIndexer getIndexer() {
		return indexer;
	}

	public void setIndexer(ModelBaseIndexer indexer) {
		this.indexer = indexer;
	}

}
