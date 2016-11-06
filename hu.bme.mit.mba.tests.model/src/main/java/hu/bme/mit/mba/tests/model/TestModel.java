package hu.bme.mit.mba.tests.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class TestModel {

    protected Table<String, String, Set<String>> adjacency;
    protected List<String> nodes;

    public TestModel() {
        adjacency = HashBasedTable.create();
        nodes = new ArrayList<>();
    }

    public void addEdge(final String type, final String sourceNode, final String targetNode) {
        newNode(sourceNode);
        newNode(targetNode);
        if (!adjacency.contains(sourceNode, targetNode)) {
            Set<String> dimSet = new HashSet<String>();
            dimSet.add(type);
            adjacency.put(sourceNode, targetNode, dimSet);
        } else {
            Set<String> dimSet = adjacency.get(sourceNode, targetNode);
            dimSet.add(type);
            adjacency.put(sourceNode, targetNode, dimSet);
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

    public boolean isAdjacent(final String sourceNode, final String targetNode) {
        return adjacency.contains(sourceNode, targetNode);
    }

    public boolean isAdjacent(final String sourceNode, final String targetNode, final String dimension) {
        if (adjacency.contains(sourceNode, targetNode)) {
            return adjacency.get(sourceNode, targetNode).contains(dimension);
        }
        return false;
    }

    public void clear() {
        adjacency.clear();
    }

    public Iterator<String> getNodeIterator() {
        return nodes.iterator();
    }
}
