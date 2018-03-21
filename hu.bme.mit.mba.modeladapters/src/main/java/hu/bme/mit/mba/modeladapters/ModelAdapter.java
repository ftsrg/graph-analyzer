package hu.bme.mit.mba.modeladapters;

import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

import java.util.*;

public abstract class ModelAdapter<N, T> {

    protected ModelIndexer<N, T> indexer;
    protected Collection<T> dimensions;

    public ModelAdapter(Collection<T> dimensions) {
        this.dimensions = dimensions;
    }

    public Iterator<N> getModelIterator() {
        return indexer.getNodes().iterator();
    }

    public Set<N> getNodes() {
        return indexer.getNodes();
    }

    public int getNumberOfNodes() {
        return indexer.getNumberOfAddedNodes();
    }

    public int getNumberOfEdges() {
        return indexer.getNumberOfAddedEdges();
    }

    public int getDegree(final N element) {
        return getIndegree(element) + getOutdegree(element);
    }

    public int getIndegree(final N element) {
        return indexer.getInDegree(element);
    }

    public int getOutdegree(final N element) {
        return indexer.getOutDegree(element);
    }

    public Set<N> getNeighbors(final N element) {
        return Sets.union(getIncomingNeighbors(element), getOutgoingNeighbors(element));
    }

    public Set<N> getIncomingNeighbors(final N element) {
        Set<N> neighbors = new HashSet();
        for(T type:indexer.getDimensions()){
            neighbors.addAll(indexer.getIncoming(element,type));
        }
        return neighbors;
    }

    public Set<N> getOutgoingNeighbors(final N element) {
        Set<N> neighbors = new HashSet();
        for(T type:indexer.getDimensions()){
            neighbors.addAll(indexer.getOutgoing(element,type));
        }
        return neighbors;
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


    public Set<T> getTypes() {
        return indexer.getDimensions();
    }

    public Set<T> getTypes(final N element) {
        Set<T> types = new HashSet<>();

        for (T type : indexer.getDimensions()) {
            if (!indexer.getOutgoing(element, type).isEmpty() || !indexer.getIncoming(element, type).isEmpty()) {
                types.add(type);
            }
        }

        return types;
    }

    public Set<N> getNodes(final T type) {
        return indexer.getNodes(type);
    }

    public int getNumberOfTypes() {
        return getTypes().size();
    }

    public int getNumberOfTypes(final N element) {
        return getTypes(element).size();
    }

    public Set<N> getNeighbors(final N element, final T type) {
        return Sets.union(getIncomingNeighbors(element, type), getOutgoingNeighbors(element, type));
    }

    public Set<N> getIncomingNeighbors(final N element, final T type) {
        final Set<N> neighbors = new HashSet<>();
        Collection<N> outGoingNeighbors = indexer.getIncoming(element,type);
        neighbors.addAll(outGoingNeighbors);
        return neighbors;
    }

    public Set<N> getOutgoingNeighbors(final N element, final T type) {
        final Set<N> neighbors = new HashSet<>();
        Collection<N> outgoingNeighbors = indexer.getOutgoing(element,type);
        neighbors.addAll(outgoingNeighbors);
        return neighbors;
    }

    public int getDegree(final N element, final T type) {
        return getIndegree(element, type) + getOutdegree(element, type);
    }

    public int getIndegree(final N element, final T type) {
       return indexer.getIncoming(element, type).size();
    }

    public int getOutdegree(final N element, final T type) {
        return indexer.getOutgoing(element, type).size();
    }

    public int getNumberOfNodes(final T type) {
        return indexer.getNodes(type).size();
    }

    public boolean isAdjacent(final N source, final N target, final T type) {
        return indexer.isAdjacent(source, target, type);
    }

    public boolean isAdjacentUndirected(final N source, final N target, final T type) {
        return (isAdjacent(source, target, type) || isAdjacent(target, source, type));
    }

}
