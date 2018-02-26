package hu.bme.mit.modelgen.model;

import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import hu.bme.mit.mba.modeladapters.Triple;

public class FakeEvolvingModelProvider implements EvolvingModelProvider<String, String> {

    private Table<String, String, String> adjacency;

    private int nodeIndex;

    public FakeEvolvingModelProvider() {
        adjacency = HashBasedTable.create();
    }

    public Table<String, String, String> getAdjacency() {
        return adjacency;
    }

    @Override
    public List<Triple<String, String>> getOperations() {
        LinkedList<Triple<String, String>> operations = new LinkedList<>();
        adjacency.cellSet()
                .forEach(cell -> operations.add(new Triple<>(cell.getRowKey(), cell.getColumnKey(), cell.getValue())));
        return operations;
    }

    @Override
    public void addEdge(String source, String target, String type) {
        adjacency.put(source, target, type);
    }

    @Override
    public String addNode(String target, String type) {
        String newNode = "node" + nodeIndex;
        addEdge(newNode, target, type);
        nodeIndex++;
        return newNode;
    }

    @Override
    public String addNode() {
        String newNode = "node" + nodeIndex;
        nodeIndex++;
        return newNode;
    }

    @Override
    public Triple<String, String> addEdge() {
        String newSource = addNode();
        String newTarget = addNode();
        addEdge(newSource, newTarget, "type1");
        return new Triple<String, String>(newSource, newTarget, "type1");
    }

}
