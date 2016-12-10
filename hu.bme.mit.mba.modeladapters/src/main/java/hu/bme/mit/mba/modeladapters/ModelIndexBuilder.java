package hu.bme.mit.mba.modeladapters;

public interface ModelIndexBuilder {

    public <N, T> void build(ModelProvider<N, T> modelProvider);

    public <N, T> void build(Triple<N, T> operation);
}
