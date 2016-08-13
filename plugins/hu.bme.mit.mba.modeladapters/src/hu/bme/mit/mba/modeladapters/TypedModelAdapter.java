package hu.bme.mit.mba.modeladapters;

import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.Sets;

public abstract class TypedModelAdapter<N, T> extends ModelAdapter<N, T> {

    public Set<T> getTypes() {
	return indexer.getTypeIndex().rowKeySet();
    }

    public Set<T> getTypes(final N element) {
	return indexer.getTypeIndex().column(element).keySet();
    }

    public Set<N> getNodes(final T type) {
	return indexer.getTypeIndex().row(type).keySet();
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
	Set<N> neighbors = new HashSet<>();
	for (N neighbor : getIncomingNeighbors(element)) {
	    if (indexer.getNodeIndex().get(neighbor, element).contains(type)) {
		neighbors.add(neighbor);
	    }
	}
	return neighbors;
    }

    public Set<N> getOutgoingNeighbors(final N element, final T type) {
	Set<N> neighbors = new HashSet<>();
	for (N neighbor : getOutgoingNeighbors(element)) {
	    if (indexer.nodeIndex.get(element, neighbor).contains(type)) {
		neighbors.add(neighbor);
	    }
	}
	return neighbors;
    }

    public int getDegree(final N element, final T type) {
	int degree = 0;
	for (T t : indexer.getTypeIndex().column(element).keySet()) {
	    if (t == type || t.equals(type)) {
		degree += indexer.getTypeIndex().get(t, element);
	    }
	}
	return degree;
	// return getIndegree(element, type) + getOutdegree(element, type);
    }

    public int getIndegree(final N element, final T type) {
	return getIncomingNeighbors(element, type).size();
    }

    public int getOutdegree(final N element, final T type) {
	return getOutgoingNeighbors(element, type).size();
    }

    public int getNumberOfNodes(final T type) {
	return indexer.getTypeIndex().row(type).keySet().size();
    }

    public boolean isAdjacent(final N source, final N target, final T type) {
	if (indexer.getNodeIndex().contains(source, target)) {
	    return indexer.getNodeIndex().get(source, target).contains(type);
	}
	return false;
    }

    public boolean isAdjacentUndirected(final N source, final N target, final T type) {
	if (isAdjacent(source, target, type)) {
	    return true;
	} else {
	    return isAdjacent(target, source, type);
	}
    }

}
