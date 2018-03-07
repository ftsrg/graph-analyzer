package hu.bme.mit.mba.modeladapters;

import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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
        return getIncomingNeighbors(element).size();
    }

    public int getOutdegree(final N element) {
        return getOutgoingNeighbors(element).size();
    }

    public Set<N> getNeighbors(final N element) {
        return Sets.union(getIncomingNeighbors(element), getOutgoingNeighbors(element));
    }

    public Set<N> getIncomingNeighbors(final N element) {
        final Set<N> neighbors = new HashSet<>();
        final int[][][] m = indexer.getNodeMatrix();
        final int target = indexer.getNodeIndex(element);

        for (int d = 0; d < m.length; d++) {
            for (int source = 0; source < m[d].length; source++) {
                if (m[d][source][target] != 0) {
                    neighbors.add(indexer.getNodeByIndex(source));
                };
            }
        }
        return neighbors;
    }

    public Set<N> getOutgoingNeighbors(final N element) {
        final Set<N> neighbors = new HashSet<>();
        final int[][][] m = indexer.getNodeMatrix();
        final int source = indexer.getNodeIndex(element);

        for (int d = 0; d < m.length; d++) {
            for (int target = 0; target < m[d].length; target++) {
                if (m[d][source][target] != 0) {
                    neighbors.add(indexer.getNodeByIndex(source));
                };
            }
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
        final int[][][] m = indexer.getNodeMatrix();
        final int target = indexer.getNodeIndex(element);
        final int d = indexer.getDimensionIndex(type);

        for (int source = 0; source < m[d].length; source++) {
            if (m[d][source][target] != 0) {
                neighbors.add(indexer.getNodeByIndex(source));
            };
        }
        return neighbors;
    }

    public Set<N> getOutgoingNeighbors(final N element, final T type) {
        final Set<N> neighbors = new HashSet<>();
        final int[][][] m = indexer.getNodeMatrix();
        final int source = indexer.getNodeIndex(element);
        final int d = indexer.getDimensionIndex(type);

        for (int target = 0; target < m[d].length; target++) {
            if (m[d][source][target] != 0) {
                neighbors.add(indexer.getNodeByIndex(target));
            };
        }
        return neighbors;
    }

    public int getDegree(final N element, final T type) {
        return getIndegree(element, type) + getOutdegree(element, type);
    }

    public int getIndegree(final N element, final T type) {
        int countNeighbors = 0;
        final int[][][] m = indexer.getNodeMatrix();
        final int target = indexer.getNodeIndex(element);
        final int d = indexer.getDimensionIndex(type);

        for (int source = 0; source < m[d].length; source++) {
            countNeighbors += m[d][source][target];
        }
        return countNeighbors;
    }

    public int getOutdegree(final N element, final T type) {
        int countNeighbors = 0;
        final int[][][] m = indexer.getNodeMatrix();
        final int source = indexer.getNodeIndex(element);
        final int d = indexer.getDimensionIndex(type);

        for (int target = 0; target < m[d].length; target++) {
            countNeighbors += m[d][source][target];
        }
        return countNeighbors;
    }

    public int getNumberOfNodes(final T type) {
        Set<Integer> nodes = new HashSet<>();
        final int[][][] m = indexer.getNodeMatrix();
        final int d = indexer.getDimensionIndex(type);

        for (int i = 0; i < m[d].length; i++) {
            boolean out = false;
            boolean in = false;
            for (int j = 0; j < m[d].length; j++) {
                if (m[d][i][j] != 0) { out = true; }
                if (m[d][j][i] != 0) { in  = true; }
            }
            if (out || in) nodes.add(i);
        }

        return nodes.size();
    }

    public boolean isAdjacent(final N source, final N target, final T type) {
        return indexer.isAdjacent(source, target, type);
    }

    public boolean isAdjacentUndirected(final N source, final N target, final T type) {
        return (isAdjacent(source, target, type) || isAdjacent(target, source, type));
    }

}
