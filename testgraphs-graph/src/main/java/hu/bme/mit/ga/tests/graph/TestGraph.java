package hu.bme.mit.ga.tests.graph;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestGraph {

    protected Table<String, String, Set<String>> adjacency = HashBasedTable.create();
    protected List<String> nodes = new ArrayList<>();
    protected Set<String> types = new HashSet<>();

    public void addEdge(final String type, final String sourceNode, final String targetNode) {
        types.add(type);
        newNode(sourceNode);
        newNode(targetNode);
        if (!adjacency.contains(sourceNode, targetNode)) {
            Set<String> typeSet = new HashSet<>();
            typeSet.add(type);
            adjacency.put(sourceNode, targetNode, typeSet);
        } else {
            Set<String> typeSet = adjacency.get(sourceNode, targetNode);
            typeSet.add(type);
            adjacency.put(sourceNode, targetNode, typeSet);
        }
    }

    protected void newNode(String node) {
        if (!nodes.contains(node)) {
            nodes.add(node);
        }
    }

    public Table<String, String, Set<String>> getAdjacency() {
        return adjacency;
    }

    public List<String> getNodes() {
        return nodes;
    }

}
