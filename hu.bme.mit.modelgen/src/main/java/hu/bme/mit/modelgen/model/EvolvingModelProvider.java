package hu.bme.mit.modelgen.model;

import hu.bme.mit.mba.modeladapters.ModelProvider;
import hu.bme.mit.mba.modeladapters.Triple;

public interface EvolvingModelProvider<N, T> extends ModelProvider<N, T> {

    public void addEdge(N source, N target, T type);

    public N addNode();

    public Triple<N, T> addEdge();

    public N addNode(N target, T type);

}
