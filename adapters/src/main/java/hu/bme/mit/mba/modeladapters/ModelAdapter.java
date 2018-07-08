package hu.bme.mit.mba.modeladapters;

public abstract class ModelAdapter<N, T> {

    protected ModelIndexer<N, T> indexer = new ModelIndexer<>();

    public ModelIndexer<N, T> getIndexer() {
        return indexer;
    }

}
