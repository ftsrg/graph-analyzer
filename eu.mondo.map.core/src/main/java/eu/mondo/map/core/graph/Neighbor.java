package eu.mondo.map.core.graph;

public class Neighbor<T> {

	protected Node<T> node;
	protected String dimension;

	public Neighbor(Node<T> node, String dimension) {
		this.node = node;
		this.dimension = dimension;
	}

	public Node<T> getNode() {
		return node;
	}

	public void setNode(Node<T> node) {
		this.node = node;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

}
