package hu.bme.mit.mba.tests.model;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class TestModel {

    protected Table<String, String, Set<String>> adjacency = HashBasedTable.create();
    protected List<String> nodes = new ArrayList<>();
    protected Set<String> dimensions = new HashSet<>();

    public void addEdge(final String type, final String sourceNode, final String targetNode) {
        dimensions.add(type);
        newNode(sourceNode);
        newNode(targetNode);
        if (!adjacency.contains(sourceNode, targetNode)) {
            Set<String> dimSet = new HashSet<>();
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

    public void clear() {
        adjacency.clear();
    }

    public Iterator<String> getNodeIterator() {
        return nodes.iterator();
    }

    public List<String> getNodes() {
        return nodes;
    }

    public Collection<String> getDimensions() {
        return dimensions;
    }

}
