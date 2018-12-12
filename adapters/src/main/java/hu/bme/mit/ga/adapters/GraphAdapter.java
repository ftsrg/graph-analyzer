package hu.bme.mit.ga.adapters;

public abstract class GraphAdapter<N, T> {

    protected GraphIndexer<N, T> indexer;

    public GraphIndexer<N, T> getIndexer() {
        return indexer;
    }

}
