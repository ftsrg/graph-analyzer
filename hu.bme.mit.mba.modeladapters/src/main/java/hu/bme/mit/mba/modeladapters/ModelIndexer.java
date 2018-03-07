package hu.bme.mit.mba.modeladapters;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ModelIndexer<N, T> {

    protected BiMap<N, Integer> nodeMapping = HashBiMap.create();
    protected BiMap<T, Integer> dimensionMapping = HashBiMap.create();

    protected int[][][] nodeMatrix;

    protected int numberOfAddedEdges;

    public ModelIndexer(final List<N> nodes, final Collection<T> dimensions) {
        int n = 0;
        for (N node : nodes) {
            nodeMapping.put(node, n);
            n++;
        }
        int d = 0;
        for (T type : dimensions) {
            dimensionMapping.put(type, d);
            d++;
        }

        nodeMatrix = new int[dimensionMapping.size()][getNumberOfAddedNodes()][getNumberOfAddedNodes()];
        numberOfAddedEdges = 0;
    }

    public boolean isAdjacent(final N source, final N target) {
        for (int d: dimensionMapping.values()) {
            if (nodeMatrix[d][nodeMapping.get(source)][nodeMapping.get(target)] != 0) {
                return true;
            }
        }
        return false;
    }

    public boolean isAdjacent(final N source, final N target, final T type) {
        return nodeMatrix[dimensionMapping.get(type)][nodeMapping.get(source)][nodeMapping.get(target)] != 0;
    }

    public boolean isAdjacentBidirectional(final N source, final N target) {
        return isAdjacent(source, target) || isAdjacent(target, source);
    }

    public void addEdge(final T type, final N sourceNode, final N targetNode) {
        nodeMatrix[dimensionMapping.get(type)][nodeMapping.get(sourceNode)][nodeMapping.get(targetNode)]++;
        numberOfAddedEdges++;
    }


    public int[][][] getNodeMatrix() {
        return nodeMatrix;
    }

    public int getNumberOfAddedEdges() {
        return numberOfAddedEdges;
    }

    public int getNumberOfAddedNodes() {
        return nodeMapping.size();
    }

    public Set<N> getNodes() {
        return nodeMapping.keySet();
    }

    public Set<T> getDimensions() {
        return dimensionMapping.keySet();
    }

    public int getNodeIndex(N node) {
        return nodeMapping.get(node);
    }

    public N getNodeByIndex(int i) {
        return nodeMapping.inverse().get(i);
    }

    public int getDimensionIndex(T type) {
        return dimensionMapping.get(type);
    }

    public Set<N> getOutgoing(N node, T type) {
        final int d = getDimensionIndex(type);
        final int n = getNodeIndex(node);
        final Integer[] vector = Arrays.stream(nodeMatrix[d][n]).boxed().toArray(Integer[]::new);
        return vectorToNodes(vector);
    }

    public Set<N> getIncoming(N node, T type) {
        final int d = getDimensionIndex(type);
        final int n = getNodeIndex(node);
        final Integer[] vector = Arrays.stream(nodeMatrix[d]).map(row -> row[n]).toArray(Integer[]::new);
        return vectorToNodes(vector);
    }

    private Set<N> vectorToNodes(Integer[] vector) {
        return IntStream.range(0, vector.length)
            .filter(i -> vector[i] != 0)
            .mapToObj(i -> nodeMapping.inverse().get(i))
            .collect(Collectors.toSet());
    }


    public Set<N> getNodes(T type) {
        final Set<N> nodes = new HashSet<>();
        final Integer d = dimensionMapping.get(type);
        final int[][] m = this.nodeMatrix[d];

        for (final int n: IntStream.range(0, m.length).boxed().collect(Collectors.toList())) {
            if (Arrays.stream(nodeMatrix[d][n]).filter(x -> x != 0).findAny().isPresent() ||
                Arrays.stream(nodeMatrix[d]).map(row -> row[n]).filter(x -> x != 0).findAny().isPresent()
                ) {
                nodes.add(nodeMapping.inverse().get(n));
            }
        }
        return nodes;
    }

}
