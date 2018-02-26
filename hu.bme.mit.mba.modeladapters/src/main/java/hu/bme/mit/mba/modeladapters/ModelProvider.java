package hu.bme.mit.mba.modeladapters;

import java.util.List;

public interface ModelProvider<N, T> {

    public List<Triple<N, T>> getOperations();

}
