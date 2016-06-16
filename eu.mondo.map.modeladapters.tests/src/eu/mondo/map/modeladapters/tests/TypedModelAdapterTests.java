package eu.mondo.map.modeladapters.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TypedModelAdapterTests extends ModelAdapterTests {

	protected CustomTypedModelAdapter typedAdapter;

	@Override
	@Before
	public void init() {
		adapter = new CustomTypedModelAdapter();
		typedAdapter = (CustomTypedModelAdapter) adapter;
		typedAdapter.init(null);
	}

	public void degree(String element, String type, int indegree, int outdegree, int degree) {
		assertEquals(indegree, typedAdapter.getIndegree(element, type));
		assertEquals(outdegree, typedAdapter.getOutdegree(element, type));
		assertEquals(degree, typedAdapter.getDegree(element, type));
	}

	public void adjacent(String type, String source, String target) {
		assertTrue(typedAdapter.isAdjacent(source, target));
		assertTrue(typedAdapter.isAdjacent(target, source));
	}

	public void neighbor(String type, String source, String target) {
		assertTrue(typedAdapter.getOutgoingNeighbors(source, type).contains(target));
		assertTrue(typedAdapter.getIncomingNeighbors(target, type).contains(source));
		assertTrue(typedAdapter.getNeighbors(source, type).contains(target));
		assertTrue(typedAdapter.getNeighbors(target, type).contains(source));
	}

	public void notNeighbor(String type, String source, String target) {
		assertFalse(typedAdapter.getOutgoingNeighbors(source, type).contains(target));
		assertFalse(typedAdapter.getIncomingNeighbors(target, type).contains(source));
	}

	public void types(int expected, String node) {
		assertEquals(expected, typedAdapter.getNumberOfTypes(node));
	}

	@Test
	public void oneTypedEdge() {
		edge(t1, n1, n2);
		degree(n1, t1, 0, 1, 1);
		degree(n1, t2, 0, 0, 0);
		degree(n2, t1, 1, 0, 1);
		degree(n2, t2, 0, 0, 0);
		adjacent(t1, n1, n2);
		neighbor(t1, n1, n2);
	}

	@Test
	public void twoTypedEdge() {
		edge(t1, n1, n2);
		edge(t1, n1, n3);
		edge(t2, n2, n3);

		degree(n1, t1, 0, 2, 2);
		degree(n1, t2, 0, 0, 0);
		degree(n2, t1, 1, 0, 1);
		degree(n2, t2, 0, 1, 1);
		degree(n3, t1, 1, 0, 1);
		degree(n3, t2, 1, 0, 1);
		adjacent(t1, n1, n2);
		adjacent(t1, n3, n1);
		adjacent(t1, n1, n3);
		adjacent(t2, n2, n3);
		neighbor(t1, n1, n3);
		neighbor(t2, n2, n3);
		notNeighbor(t2, n1, n2);
		notNeighbor(t1, n2, n3);
		notNeighbor(t2, n1, n3);
		notNeighbor(t2, n3, n2);

		types(1, n1);
		types(2, n2);
		types(2, n3);
	}

}
