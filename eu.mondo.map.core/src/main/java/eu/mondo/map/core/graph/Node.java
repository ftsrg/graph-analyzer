package eu.mondo.map.core.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Node<T> {

	protected T object;
	protected Set<String> dimensions;
	protected List<Neighbor<T>> incomingNeighbors;
	protected List<Neighbor<T>> outgoingNeighbors;

	public Node(T object) {
		this.object = object;
		dimensions = new HashSet<String>();
		incomingNeighbors = new ArrayList<Neighbor<T>>();
		outgoingNeighbors = new ArrayList<Neighbor<T>>();
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

	public Set<String> getDimensions() {
		return dimensions;
	}

	public void setDimensions(Set<String> dimensions) {
		this.dimensions = dimensions;
	}

	public List<Neighbor<T>> getIncomingNeighbor() {
		return incomingNeighbors;
	}

	public void setIncomingNeighbor(List<Neighbor<T>> incomingNeighbor) {
		this.incomingNeighbors = incomingNeighbor;
	}

	public List<Neighbor<T>> getOutgoingNeighbor() {
		return outgoingNeighbors;
	}

	public void setOutgoingNeighbor(List<Neighbor<T>> outgoingNeighbor) {
		this.outgoingNeighbors = outgoingNeighbor;
	}

	public int numberOfDimensions() {
		return dimensions.size();
	}

	public boolean hasDimension(String dimension) {
		return dimensions.contains(dimension);
	}

	public boolean addDimension(String dimension) {
		return dimensions.add(dimension);
	}

	public int getDegree() {
		return incomingNeighbors.size() + outgoingNeighbors.size();
	}

	public boolean addIncomingNeighbor(final Node<T> neighbor, final String dimension) {
		return incomingNeighbors.add(new Neighbor<T>(neighbor, dimension));
	}

	public boolean addOutgoingNeighbor(final Node<T> neighbor, final String dimension) {
		return outgoingNeighbors.add(new Neighbor<T>(neighbor, dimension));
	}

}
