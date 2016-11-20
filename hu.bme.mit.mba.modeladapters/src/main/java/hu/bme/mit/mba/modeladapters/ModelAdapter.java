package hu.bme.mit.mba.modeladapters;

import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.Sets;

public class ModelAdapter implements ModelIndexBuilder {

    protected <N, T> ModelIndexer<N, T> getIndexer() {
        return ModelIndexer.<N, T>getInstance(this);
    }

    public void clear() {
        getIndexer().clear();
    }

    public <N, T> Set<N> getNodes() {
        return this.<N, T>getIndexer().getTypeIndex().columnKeySet();
    }

    public <N, T> int getNumberOfNodes() {
        return getIndexer().getNumberOfAddedNodes();
    }

    public <N, T> int getNumberOfEdges() {
        return getIndexer().getNumberOfAddedEdges();
    }

    public <N, T> int getDegree(final N element) {
        return getIndegree(element) + getOutdegree(element);
    }

    public <N, T> int getIndegree(final N element) {
        int degree = 0;
        for (Set<T> values : this.<N, T>getIndexer().getNodeIndex().column(element).values()) {
            degree += values.size();
        }
        return degree;
    }

    public <N, T> int getOutdegree(final N element) {
        int degree = 0;
        for (Set<T> values : this.<N, T>getIndexer().getNodeIndex().row(element).values()) {
            degree += values.size();
        }
        return degree;
    }

    public <N, T> Set<N> getNeighbors(final N element) {
        return Sets.union(getIncomingNeighbors(element), getOutgoingNeighbors(element));
    }

    public <N, T> Set<N> getIncomingNeighbors(final N element) {
        return this.<N, T>getIndexer().getNodeIndex().column(element).keySet();
    }

    public <N, T> Set<N> getOutgoingNeighbors(final N element) {
        return this.<N, T>getIndexer().getNodeIndex().row(element).keySet();
    }

    public <N, T> boolean isAdjacent(final N source, final N target) {
        return getIndexer().isAdjacentBidirectional(source, target);
    }

    public <N, T> Set<T> getTypes() {
        return this.<N, T>getIndexer().getTypeIndex().rowKeySet();
    }

    public <N, T> Set<T> getTypes(final N element) {
        return this.<N, T>getIndexer().getTypeIndex().column(element).keySet();
    }

    public <N, T> Set<N> getNodes(final T type) {
        return this.<N, T>getIndexer().getTypeIndex().row(type).keySet();
    }

    public <N, T> int getNumberOfTypes() {
        return getTypes().size();
    }

    public <N, T> int getNumberOfTypes(final N element) {
        return getTypes(element).size();
    }

    public <N, T> Set<N> getNeighbors(final N element, final T type) {
        return Sets.union(getIncomingNeighbors(element, type), getOutgoingNeighbors(element, type));
    }

    public <N, T> Set<N> getIncomingNeighbors(final N element, final T type) {
        Set<N> neighbors = new HashSet<>();
        for (N neighbor : getIncomingNeighbors(element)) {
            if (getIndexer().getNodeIndex().get(neighbor, element).contains(type)) {
                neighbors.add(neighbor);
            }
        }
        return neighbors;
    }

    public <N, T> Set<N> getOutgoingNeighbors(final N element, final T type) {
        Set<N> neighbors = new HashSet<>();
        for (N neighbor : getOutgoingNeighbors(element)) {
            if (getIndexer().nodeIndex.get(element, neighbor).contains(type)) {
                neighbors.add(neighbor);
            }
        }
        return neighbors;
    }

    public <N, T> int getDegree(final N element, final T type) {
        int degree = 0;
        for (T t : this.<N, T>getIndexer().getTypeIndex().column(element).keySet()) {
            if (t == type || t.equals(type)) {
                degree += getIndexer().getTypeIndex().get(t, element);
            }
        }
        return degree;
    }

    public <N, T> int getIndegree(final N element, final T type) {
        return getIncomingNeighbors(element, type).size();
    }

    public <N, T> int getOutdegree(final N element, final T type) {
        return getOutgoingNeighbors(element, type).size();
    }

    public <N, T> int getNumberOfNodes(final T type) {
        return getIndexer().getTypeIndex().row(type).keySet().size();
    }

    public <N, T> boolean isAdjacent(final N source, final N target, final T type) {
        if (getIndexer().getNodeIndex().contains(source, target)) {
            return getIndexer().getNodeIndex().get(source, target).contains(type);
        }
        return false;
    }

    public <N, T> boolean isAdjacentUndirected(final N source, final N target, final T type) {
        if (isAdjacent(source, target, type)) {
            return true;
        } else {
            return isAdjacent(target, source, type);
        }
    }

    @Override
    public <N, T> void build(ModelProvider<N, T> modelProvider) {
        modelProvider.getOperations().forEach(op -> build(op));
    }

    @Override
    public <N, T> void build(EdgeOperation<N, T> operation) {
        this.<N, T>getIndexer().addEdge(operation.getEdgeType(), operation.getSourceNode(), operation.getTargetNode());
    }

}
