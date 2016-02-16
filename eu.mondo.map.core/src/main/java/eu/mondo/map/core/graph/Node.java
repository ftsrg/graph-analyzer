package eu.mondo.map.core.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Node<T> {

	protected int id;
	protected T object;
	protected Set<String> dimensions;
	protected List<T> incomingNeighbors;
	protected List<T> outgoingNeighbors;

	public Node(int id, T object) {
		this.id = id;
		this.object = object;
		dimensions = new HashSet<String>();
		incomingNeighbors = new ArrayList<T>();
		outgoingNeighbors = new ArrayList<T>();
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<String> getDimensions() {
		return dimensions;
	}

	public void setDimensions(Set<String> dimensions) {
		this.dimensions = dimensions;
	}

	public List<T> getIncomingNeighbor() {
		return incomingNeighbors;
	}

	public void setIncomingNeighbor(List<T> incomingNeighbor) {
		this.incomingNeighbors = incomingNeighbor;
	}

	public List<T> getOutgoingNeighbor() {
		return outgoingNeighbors;
	}

	public void setOutgoingNeighbor(List<T> outgoingNeighbor) {
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

	public boolean addIncomingNeighbor(final T neighbor) {
		return incomingNeighbors.add(neighbor);
	}

	public boolean addOutgoingNeighbor(final T neighbor) {
		return outgoingNeighbors.add(neighbor);
	}

}
