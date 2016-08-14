package hu.bme.mit.mba.modeladapters;

import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class ModelIndexer<N, T> {

    protected Table<N, N, Set<T>> nodeIndex;
    protected Table<T, N, Integer> typeIndex;
    protected int numberOfAddedEdges;
    protected int numberOfAddedNodes;

    public ModelIndexer() {
        nodeIndex = HashBasedTable.create();
        typeIndex = HashBasedTable.create();
        numberOfAddedEdges = 0;
        numberOfAddedNodes = 0;
    }

    public boolean isAdjacent(final N source, final N target) {
        return nodeIndex.contains(source, target);
    }

    public boolean isAdjacentBidirectional(final N source, final N target) {
        return isAdjacent(source, target) || isAdjacent(target, source);
    }

    protected void newNode(final N node) {
        if (!nodeIndex.containsColumn(node) && !nodeIndex.containsRow(node)) {
            numberOfAddedNodes++;
        }
    }

    public void addEdge(final T type, final N sourceNode, final N targetNode) {
        newNode(sourceNode);
        if (sourceNode != targetNode) {
            newNode(targetNode);
        }
        if (!nodeIndex.contains(sourceNode, targetNode)) {
            Set<T> dimSet = new HashSet<T>();
            dimSet.add(type);
            nodeIndex.put(sourceNode, targetNode, dimSet);
        } else {
            Set<T> dimSet = nodeIndex.get(sourceNode, targetNode);
            dimSet.add(type);
            nodeIndex.put(sourceNode, targetNode, dimSet);
        }
        numberOfAddedEdges++;

        putNodeOnType(type, sourceNode);
        putNodeOnType(type, targetNode);
    }

    protected void putNodeOnType(final T type, final N node) {
        if (!typeIndex.contains(type, node)) {
            typeIndex.put(type, node, 1);
        } else {
            int occurrence = typeIndex.get(type, node);
            occurrence++;
            typeIndex.put(type, node, occurrence);
        }
    }

    public void clear() {
        nodeIndex.clear();
        typeIndex.clear();
        numberOfAddedEdges = 0;
        numberOfAddedNodes = 0;
    }

    public Table<N, N, Set<T>> getNodeIndex() {
        return nodeIndex;
    }

    public Table<T, N, Integer> getTypeIndex() {
        return typeIndex;
    }

    public int getNumberOfAddedEdges() {
        return numberOfAddedEdges;
    }

    public int getNumberOfAddedNodes() {
        return numberOfAddedNodes;
    }

}
