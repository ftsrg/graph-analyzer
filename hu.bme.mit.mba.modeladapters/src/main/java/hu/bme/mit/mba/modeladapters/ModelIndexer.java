package hu.bme.mit.mba.modeladapters;

import com.google.common.collect.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ModelIndexer<N, T> {
    private Map<T, Multimap<N, N>> outgoing = new HashMap<>();
    private Map<T, Multimap<N, N>> incoming = new HashMap<>();
    private Set<T> dimensions = new HashSet<>();
    private Set<N> nodes = new HashSet<>();

    protected int numberOfAddedEdges;

    public ModelIndexer(final List<N> nodes, final Collection<T> dimensions) {
        this.nodes.addAll(nodes);
        for (T type : dimensions) {
            this.dimensions.add(type);
            outgoing.put(type, ArrayListMultimap.create());
            incoming.put(type, ArrayListMultimap.create());
        }
        numberOfAddedEdges = 0;
    }

    public boolean isAdjacent(final N source, final N target) {
        for (T d : dimensions) {
            if (outgoing.get(d).containsEntry(source, target)) {
                return true;
            }
        }
        return false;
    }

    public boolean isAdjacent(final N source, final N target, final T type) {
        return incoming.get(type).containsEntry(source, target);
    }

    public boolean isAdjacentBidirectional(final N source, final N target) {
        return isAdjacent(source, target) || isAdjacent(target, source);
    }

    public int getOutDegree(N element) {
        int outDegree = 0;
        for (T dim : dimensions) {
            outDegree += outgoing.get(dim).get(element).size();
        }
        return outDegree;

    }

    public int getInDegree(N element) {
        int inDegree = 0;
        for (T dim : dimensions) {
            inDegree += incoming.get(dim).get(element).size();
        }
        return inDegree;
    }


    public void addEdge(final T type, final N sourceNode, final N targetNode) {
        if (!nodes.contains(sourceNode)) {
            nodes.add(sourceNode);
        }
        if (!nodes.contains(targetNode)) {
            nodes.add(targetNode);
        }
        if (outgoing.get(type) == null) {
            dimensions.add(type);
            outgoing.put(type, ArrayListMultimap.create());
            incoming.put(type, ArrayListMultimap.create());
        }
        outgoing.get(type).put(sourceNode, targetNode);
        incoming.get(type).put(targetNode, sourceNode);
        numberOfAddedEdges++;
    }

    public int getNumberOfAddedEdges() {
        return numberOfAddedEdges;
    }

    public int getNumberOfAddedNodes() {
        return nodes.size();
    }

    public Set<N> getNodes() {
        return nodes;
    }

    public Set<T> getDimensions() {
        return dimensions;
    }

    public Collection<N> getOutgoing(N node, T type) {
        return outgoing.get(type).get(node);
    }

    public Collection<N> getIncoming(N node, T type) {
        return incoming.get(type).get(node);
    }

    public Set<N> getNodes(T type) {
        Set<N> nodeSet = new HashSet<>();
        nodeSet.addAll(incoming.get(type).keySet());
        nodeSet.addAll(outgoing.get(type).keySet());
        return nodeSet;
    }
}
