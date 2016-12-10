package hu.bme.mit.modelgen.model;

import java.util.Collection;
import java.util.Set;

import hu.bme.mit.mba.modelmetrics.impl.simple.NumberOfEdges;
import hu.bme.mit.mba.modelmetrics.impl.simple.NumberOfNodes;

public class DomainDescriptor<T> {

    private Set<T> availableTypes;
    private NumberOfNodes maxNumberOfNodes;
    private NumberOfEdges maxNumberOfEdges;

    public DomainDescriptor(Set<T> availableTypes, NumberOfNodes maxNumberOfNodes, NumberOfEdges edges) {
        this.availableTypes = availableTypes;
        this.maxNumberOfNodes = maxNumberOfNodes;
        this.maxNumberOfEdges = edges;
    }

    public NumberOfNodes getMaxNumberOfNodes() {
        return maxNumberOfNodes;
    }

    public NumberOfEdges getMaxNumberOfEdges() {
        return maxNumberOfEdges;
    }

    public Set<T> getAvailableTypes() {
        return availableTypes;
    }

    public void addType(T type) {
        availableTypes.add(type);
    }

    public void addTypes(Collection<? extends T> types) {
        availableTypes.addAll(types);
    }

}
