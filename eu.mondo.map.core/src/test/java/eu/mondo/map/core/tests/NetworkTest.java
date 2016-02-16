package eu.mondo.map.core.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.mondo.map.core.graph.Network;
import eu.mondo.map.core.graph.Node;

public class NetworkTest {

	protected Network<String> network;
	protected Node<String> node;
	protected Node<String> node2;
	protected Node<String> node3;
	protected Node<String> node4;

	@Before
	public void init() {
		network = new Network<String>();
	}

	@Test
	public void addNode() {
		node = network.addNode("node1");
		Assert.assertEquals(0, node.getId());
		Assert.assertEquals(0, node.getDegree());
		Assert.assertEquals(0, node.getDimensions().size());
		Assert.assertEquals("node1", node.getObject());

		Assert.assertEquals(1, network.getNodes().size());
		Assert.assertEquals(node, network.getNodes().get(0));
		Assert.assertEquals(node, network.getNodesOnObjects().get("node1"));

	}

	@Test
	public void addSameNode() {
		node = network.addNode("node1");
		node2 = network.addNode("node1");
		Assert.assertEquals(node, node2);

		node3 = network.addNode("node1", true);
		Assert.assertNotEquals(node, node3);

		node4 = network.addNode("node1", false);
		Assert.assertEquals(node3, node4);
	}

	@Test
	public void addEdge() {
		network.addEdge("dim1", "node1", "node2");
		node = network.getNode("node1");
		node2 = network.getNode("node2");
		Assert.assertEquals(0, node.getId());
		Assert.assertEquals(1, node2.getId());

		Assert.assertEquals(1, node.getDegree());
		Assert.assertEquals("node2", node.getOutgoingNeighbor().get(0));

		Assert.assertEquals(2, network.getNodes().size());
		Assert.assertEquals(2, network.getNodesOnDimensions().get("dim1").size());
		Assert.assertEquals(2, network.getNodesOnObjects().size());

		Assert.assertEquals(true, node.hasDimension("dim1"));
		Assert.assertEquals(true, node2.hasDimension("dim1"));
	}

	@Test
	public void addOneNodeOneEdge() {
		node = network.addNode("node1");
		network.addEdge("dim1", "node1", "node2");

		Assert.assertEquals(1, node.getDegree());
		Assert.assertEquals(node, network.getNodes().get(0));
	}

	@Test
	public void addNeighbors() {
		node = network.addNode("node1");
		node2 = network.addNode("node2");
		network.addEdge("dim1", "node1", "node2");

		Assert.assertEquals(node, network.getNodes().get(0));
		Assert.assertEquals(node2, network.getNodes().get(1));
		Assert.assertEquals(2, network.getNodes().size());

		Assert.assertEquals("node2", node.getOutgoingNeighbor().get(0));
		Assert.assertEquals("node1", node2.getIncomingNeighbor().get(0));
		Assert.assertEquals(1, node.getDegree());
		Assert.assertEquals(1, node2.getDegree());

		Assert.assertEquals(true, node.hasDimension("dim1"));
		Assert.assertEquals(true, node2.hasDimension("dim1"));
	}

	@Test
	public void addCrossNeighbors() {
		node = network.addNode("node1");
		node2 = network.addNode("node2");
		network.addEdge("dim1", "node1", "node2");
		network.addEdge("dim1", "node2", "node1");

		Assert.assertEquals("node2", node.getOutgoingNeighbor().get(0));
		Assert.assertEquals("node1", node2.getOutgoingNeighbor().get(0));
		Assert.assertEquals("node2", node.getIncomingNeighbor().get(0));
		Assert.assertEquals("node1", node2.getIncomingNeighbor().get(0));

		Assert.assertEquals(1, node.getDimensions().size());
		Assert.assertEquals(1, node2.getDimensions().size());

		Assert.assertEquals(2, network.getNodesOnDimensions().get("dim1").size());
		Assert.assertEquals(node, network.getNodesOnDimensions().get("dim1").get(0));
		Assert.assertEquals(node2, network.getNodesOnDimensions().get("dim1").get(1));

	}

	@Test
	public void addCrossNeighborsWithDifferentDimensions() {
		node = network.addNode("node1");
		node2 = network.addNode("node2");
		network.addEdge("dim1", "node1", "node2");
		network.addEdge("dim2", "node2", "node1");

		Assert.assertEquals("node2", node.getOutgoingNeighbor().get(0));
		Assert.assertEquals("node1", node2.getOutgoingNeighbor().get(0));
		Assert.assertEquals("node2", node.getIncomingNeighbor().get(0));
		Assert.assertEquals("node1", node2.getIncomingNeighbor().get(0));

		Assert.assertEquals(2, node.getDimensions().size());
		Assert.assertEquals(2, node2.getDimensions().size());

		Assert.assertEquals(2, network.getNodesOnDimensions().get("dim1").size());
		Assert.assertEquals(node, network.getNodesOnDimensions().get("dim1").get(0));
		Assert.assertEquals(node2, network.getNodesOnDimensions().get("dim1").get(1));

		Assert.assertEquals(2, network.getNodesOnDimensions().get("dim2").size());
		Assert.assertEquals(node2, network.getNodesOnDimensions().get("dim2").get(0));
		Assert.assertEquals(node, network.getNodesOnDimensions().get("dim2").get(1));

	}

}
