package eu.mondo.map.modeladapters.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import eu.mondo.map.modeladapters.ModelAdapter;

public class ModelAdapterTests {

	protected ModelAdapter<String, String, String> adapter;
	protected String t1 = "t1";
	protected String t2 = "t2";
	protected String t3 = "t3";

	protected String n1 = "n1";
	protected String n2 = "n2";
	protected String n3 = "n3";
	protected String n4 = "n4";
	protected String n5 = "n5";
	protected String n6 = "n6";
	protected String n7 = "n7";
	protected String n8 = "n8";

	@Before
	public void init() {
		adapter = new CustomModelAdapter();
		adapter.init(null);
	}

	protected void edge(String type, String source, String target) {
		adapter.getIndexer().addEdge(type, source, target);
	}

	public void degree(String element, int indegree, int outdegree, int degree) {
		assertEquals(indegree, adapter.getIndegree(element));
		assertEquals(outdegree, adapter.getOutdegree(element));
		assertEquals(degree, adapter.getDegree(element));
	}

	public void adjacent(String source, String target) {
		assertTrue(adapter.isAdjacent(source, target));
		assertTrue(adapter.isAdjacent(target, source));
	}

	public void neighbor(String source, String target) {
		assertTrue(adapter.getOutgoingNeighbors(source).contains(target));
		assertTrue(adapter.getIncomingNeighbors(target).contains(source));
		assertTrue(adapter.getNeighbors(source).contains(target));
		assertTrue(adapter.getNeighbors(target).contains(source));
	}

	public void nodes(int expected) {
		assertEquals(expected, adapter.getNumberOfNodes());
	}

	public void edges(int expected) {
		assertEquals(expected, adapter.getNumberOfEdges());
	}

	@Test
	public void oneEdge() {
		edge(t1, n1, n2);
		degree(n1, 0, 1, 1);
		degree(n2, 1, 0, 1);
		adjacent(n1, n2);
		neighbor(n1, n2);
		nodes(2);
		edges(1);
	}

	@Test
	public void triangle() {
		edge(t1, n1, n2);
		edge(t1, n2, n3);
		edge(t1, n3, n1);

		degree(n1, 1, 1, 2);
		degree(n2, 1, 1, 2);
		degree(n3, 1, 1, 2);
		neighbor(n1, n2);
		neighbor(n2, n3);
		neighbor(n3, n1);
		nodes(3);
		edges(3);
	}

	@Test
	public void multiplexTriangle1() {
		edge(t1, n1, n2);
		edge(t1, n2, n3);
		edge(t1, n3, n1);
		edge(t2, n1, n2);

		degree(n1, 1, 2, 3);
		degree(n2, 2, 1, 3);
		degree(n3, 1, 1, 2);
		neighbor(n1, n2);
		neighbor(n2, n3);
		neighbor(n3, n1);
		nodes(3);
		edges(4);
	}

	@Test
	public void multiplexTriangle2() {

	}
}
