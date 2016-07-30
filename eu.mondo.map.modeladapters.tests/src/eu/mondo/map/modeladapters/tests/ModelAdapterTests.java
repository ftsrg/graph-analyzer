package eu.mondo.map.modeladapters.tests;

import static eu.mondo.map.tests.model.ModelContext.dim1;
import static eu.mondo.map.tests.model.ModelContext.dim2;
import static eu.mondo.map.tests.model.ModelContext.node1;
import static eu.mondo.map.tests.model.ModelContext.node2;
import static eu.mondo.map.tests.model.ModelContext.node3;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import eu.mondo.map.modeladapters.ModelAdapter;
import eu.mondo.map.modeladapters.TypedModelAdapter;
import eu.mondo.map.tests.model.TestModel;
import eu.mondo.map.tests.model.TestModelTypes;

public class ModelAdapterTests {

    protected CustomTypedModelAdapter adapter;
    protected TestModel model;

    @Test(dataProvider = "data")
    public void testAdapter(TestModelTypes modelType, Runnable checker) {
	runTests(modelType, checker);
    }

    @Test(dataProvider = "typedData")
    public void testTypedAdapter(TestModelTypes modelType, Runnable checker) {
	runTests(modelType, checker);
    }

    protected void runTests(TestModelTypes modelType, Runnable checker) {
	model = modelType.init();
	adapter = new CustomTypedModelAdapter();
	adapter.init(model);

	checker.run();

	adapter.getIndexer().clear();
	adapter.init(model);
	checker.run();

	adapter.init(model);
	checker.run();
    }

    @DataProvider
    public Object[][] data() {
	List<Object[]> casesList = new ArrayList<>();
	for (TestModelTypes type : TestModelTypes.values()) {
	    casesList.add(testCase(type));
	}

	Object[][] casesArray = new Object[casesList.size()][2];
	for (int i = 0; i < casesList.size(); i++) {
	    casesArray[i] = casesList.get(i);
	}
	return casesArray;
    }

    @DataProvider
    public Object[][] typedData() {
	List<Object[]> casesList = new ArrayList<>();
	for (TestModelTypes type : TestModelTypes.values()) {
	    casesList.add(typedTestCase(type));
	}

	Object[][] casesArray = new Object[casesList.size()][2];
	for (int i = 0; i < casesList.size(); i++) {
	    casesArray[i] = casesList.get(i);
	}
	return casesArray;
    }

    protected Object[] testCase(TestModelTypes modelType) {
	Runnable checker = null;
	switch (modelType) {
	case Motif3N_1:
	    checker = () -> {
		nodes(adapter, 3);
		edges(adapter, 2);
		degree(adapter, node1, 0, 2);
		degree(adapter, node2, 1, 0);
		degree(adapter, node3, 1, 0);
		neighbor(adapter, node1, node2);
		neighbor(adapter, node1, node3);
	    };
	    break;
	case Motif3N_2:
	    checker = () -> {
		nodes(adapter, 3);
		edges(adapter, 2);
		degree(adapter, node1, 1, 1);
		degree(adapter, node2, 0, 1);
		degree(adapter, node3, 1, 0);
		neighbor(adapter, node2, node1);
		neighbor(adapter, node1, node3);
	    };
	    break;
	case Motif3N_3:
	case Motif3N_3_2T:
	    checker = () -> {
		nodes(adapter, 3);
		edges(adapter, 3);
		degree(adapter, node1, 1, 2);
		degree(adapter, node2, 1, 1);
		degree(adapter, node3, 1, 0);
		neighbor(adapter, node1, node2);
		neighbor(adapter, node2, node1);
		neighbor(adapter, node1, node3);
	    };
	    break;
	case Motif3N_4:
	    checker = () -> {
		nodes(adapter, 3);
		edges(adapter, 2);
		degree(adapter, node1, 0, 1);
		degree(adapter, node2, 0, 1);
		degree(adapter, node3, 2, 0);
		neighbor(adapter, node1, node3);
		neighbor(adapter, node2, node3);
	    };
	    break;
	case Motif3N_5:
	    checker = () -> {
		nodes(adapter, 3);
		edges(adapter, 3);
		degree(adapter, node1, 0, 2);
		degree(adapter, node2, 1, 1);
		degree(adapter, node3, 2, 0);
		neighbor(adapter, node1, node2);
		neighbor(adapter, node1, node3);
		neighbor(adapter, node2, node3);
	    };
	    break;
	case Motif3N_6:
	case Motif3N_6_2T:
	    checker = () -> {
		nodes(adapter, 3);
		edges(adapter, 4);
		degree(adapter, node1, 1, 2);
		degree(adapter, node2, 1, 2);
		degree(adapter, node3, 2, 0);
		neighbor(adapter, node1, node2);
		neighbor(adapter, node2, node1);
		neighbor(adapter, node1, node3);
	    };
	    break;
	case Motif3N_7:
	case Motif3N_7_2T:
	    checker = () -> {
		nodes(adapter, 3);
		edges(adapter, 3);
		degree(adapter, node1, 2, 1);
		degree(adapter, node2, 1, 1);
		degree(adapter, node3, 0, 1);
		neighbor(adapter, node1, node2);
		neighbor(adapter, node2, node1);
		neighbor(adapter, node3, node1);
	    };
	    break;
	case Motif3N_8:
	case Motif3N_8_2T:
	    checker = () -> {
		nodes(adapter, 3);
		edges(adapter, 4);
		degree(adapter, node1, 2, 2);
		degree(adapter, node2, 1, 1);
		degree(adapter, node3, 1, 1);
		neighbor(adapter, node1, node2);
		neighbor(adapter, node2, node1);
		neighbor(adapter, node1, node3);
		neighbor(adapter, node3, node1);
	    };
	    break;
	case Motif3N_9:
	    checker = () -> {
		degree(adapter, node1, 1, 1);
		degree(adapter, node2, 1, 1);
		degree(adapter, node3, 1, 1);
		neighbor(adapter, node1, node2);
		neighbor(adapter, node2, node3);
		neighbor(adapter, node3, node1);
	    };
	    break;
	case Motif3N_10:
	case Motif3N_10_2T:
	    checker = () -> {
		degree(adapter, node1, 1, 2);
		degree(adapter, node2, 1, 1);
		degree(adapter, node3, 2, 1);
		neighbor(adapter, node1, node2);
		neighbor(adapter, node2, node3);
		neighbor(adapter, node1, node3);
		neighbor(adapter, node3, node1);
	    };
	    break;
	case Motif3N_11:
	case Motif3N_11_2T:
	    checker = () -> {
		degree(adapter, node1, 2, 1);
		degree(adapter, node2, 0, 2);
		degree(adapter, node3, 2, 1);
		neighbor(adapter, node2, node1);
		neighbor(adapter, node2, node3);
		neighbor(adapter, node1, node3);
		neighbor(adapter, node3, node1);
	    };
	    break;
	case Motif3N_12:
	case Motif3N_12_2T:
	    checker = () -> {
		nodes(adapter, 3);
		edges(adapter, 5);
		degree(adapter, node1, 2, 2);
		degree(adapter, node2, 1, 2);
		degree(adapter, node3, 2, 1);
		neighbor(adapter, node1, node2);
		neighbor(adapter, node2, node1);
		neighbor(adapter, node1, node3);
		neighbor(adapter, node3, node1);
		neighbor(adapter, node2, node3);
	    };
	    break;
	case Motif3N_13:
	case Motif3N_13_2T:
	    checker = () -> {
		nodes(adapter, 3);
		edges(adapter, 6);
		degree(adapter, node1, 2, 2);
		degree(adapter, node2, 2, 2);
		degree(adapter, node3, 2, 2);
		neighbor(adapter, node1, node2);
		neighbor(adapter, node2, node1);
		neighbor(adapter, node1, node3);
		neighbor(adapter, node3, node1);
		neighbor(adapter, node2, node3);
		neighbor(adapter, node3, node2);
	    };
	    break;
	default:
	    return null;
	}

	return new Object[] { modelType, checker };
    }

    protected Object[] typedTestCase(TestModelTypes modelType) {
	Runnable checker = null;
	switch (modelType) {
	case Motif3N_3_2T:
	    checker = () -> {
		degree(adapter, node1, dim1, 0, 2);
		degree(adapter, node1, dim2, 1, 0);
		degree(adapter, node2, dim1, 1, 0);
		degree(adapter, node2, dim2, 0, 1);
		degree(adapter, node3, dim1, 1, 0);
		degree(adapter, node3, dim2, 0, 0);
		neighbor(adapter, dim1, node1, node2);
		notNeighbor(adapter, dim2, node1, node2);
		neighbor(adapter, dim2, node2, node1);
		neighbor(adapter, dim1, node1, node3);
		notNeighbor(adapter, dim2, node1, node3);
	    };
	    break;
	case Motif3N_6_2T:
	    checker = () -> {
		degree(adapter, node1, dim1, 0, 2);
		degree(adapter, node1, dim2, 1, 0);
		degree(adapter, node2, dim1, 1, 1);
		degree(adapter, node2, dim2, 0, 1);
		degree(adapter, node3, dim1, 2, 0);
		degree(adapter, node3, dim2, 0, 0);
		neighbor(adapter, dim1, node1, node2);
		notNeighbor(adapter, dim2, node1, node2);
		neighbor(adapter, dim2, node2, node1);
		neighbor(adapter, dim1, node2, node3);
		notNeighbor(adapter, dim2, node2, node3);
		neighbor(adapter, dim1, node1, node3);
		notNeighbor(adapter, dim2, node1, node3);
	    };
	    break;
	case Motif3N_7_2T:
	    checker = () -> {
		degree(adapter, node1, dim1, 1, 1);
		degree(adapter, node1, dim2, 1, 0);
		degree(adapter, node2, dim1, 1, 0);
		degree(adapter, node2, dim2, 0, 1);
		degree(adapter, node3, dim1, 0, 1);
		degree(adapter, node3, dim2, 0, 0);
		neighbor(adapter, dim1, node1, node2);
		notNeighbor(adapter, dim2, node1, node2);
		neighbor(adapter, dim2, node2, node1);
		neighbor(adapter, dim1, node3, node1);
		notNeighbor(adapter, dim2, node3, node1);
		notNeighbor(adapter, dim1, node1, node3);
	    };
	    break;
	case Motif3N_8_2T:
	    checker = () -> {
		degree(adapter, node1, dim1, 0, 2);
		degree(adapter, node1, dim2, 2, 0);
		degree(adapter, node2, dim1, 1, 0);
		degree(adapter, node2, dim2, 0, 1);
		degree(adapter, node3, dim1, 1, 0);
		degree(adapter, node3, dim2, 0, 1);
		neighbor(adapter, dim1, node1, node2);
		notNeighbor(adapter, dim2, node1, node2);
		neighbor(adapter, dim2, node2, node1);
		notNeighbor(adapter, dim1, node2, node1);
		neighbor(adapter, dim2, node3, node1);
		notNeighbor(adapter, dim1, node3, node1);
		neighbor(adapter, dim1, node1, node3);
		notNeighbor(adapter, dim2, node1, node3);
	    };
	    break;
	case Motif3N_10_2T:
	    checker = () -> {
		degree(adapter, node1, dim1, 0, 2);
		degree(adapter, node1, dim2, 1, 0);
		degree(adapter, node2, dim1, 1, 1);
		degree(adapter, node2, dim2, 0, 0);
		degree(adapter, node3, dim1, 2, 0);
		degree(adapter, node3, dim2, 0, 1);
		neighbor(adapter, dim1, node1, node2);
		notNeighbor(adapter, dim2, node1, node2);
		notNeighbor(adapter, dim2, node2, node1);
		notNeighbor(adapter, dim1, node2, node1);
		neighbor(adapter, dim2, node3, node1);
		notNeighbor(adapter, dim1, node3, node1);
		neighbor(adapter, dim1, node1, node3);
		notNeighbor(adapter, dim2, node1, node3);
		neighbor(adapter, dim1, node2, node3);
		notNeighbor(adapter, dim2, node2, node3);
	    };
	    break;
	case Motif3N_13_2T:
	    checker = () -> {
		degree(adapter, node1, dim1, 0, 2);
		degree(adapter, node1, dim2, 2, 0);
		degree(adapter, node2, dim1, 1, 1);
		degree(adapter, node2, dim2, 1, 1);
		degree(adapter, node3, dim1, 2, 0);
		degree(adapter, node3, dim2, 0, 2);
		neighbor(adapter, dim1, node1, node2);
		neighbor(adapter, dim1, node1, node3);
		neighbor(adapter, dim2, node2, node1);
		neighbor(adapter, dim1, node2, node3);
		neighbor(adapter, dim2, node3, node2);
		neighbor(adapter, dim2, node3, node1);

		notNeighbor(adapter, dim2, node1, node2);
		notNeighbor(adapter, dim2, node1, node3);
		notNeighbor(adapter, dim1, node2, node1);
		notNeighbor(adapter, dim2, node2, node3);
		notNeighbor(adapter, dim1, node3, node2);
		notNeighbor(adapter, dim1, node3, node1);
	    };
	    break;
	default:
	    return null;
	}
	return new Object[] { modelType, checker };
    }

    protected void notNull(Object... elements) {
	for (Object el : elements) {
	    assertNotNull(el);
	}
    }

    protected <N, T> void degree(ModelAdapter<N, T> adapter, N node, int indegree, int outdegree) {
	notNull(node);
	assertEquals(indegree, adapter.getIndegree(node), node.toString());
	assertEquals(outdegree, adapter.getOutdegree(node), node.toString());
	assertEquals(indegree + outdegree, adapter.getDegree(node), node.toString());
    }

    protected <N, T> void adjacent(ModelAdapter<N, T> adapter, N source, N target) {
	notNull(source, target);
	String message = String.format("{0} and {1} are not adjacent", source.toString(), target.toString());
	assertTrue(message, adapter.isAdjacent(source, target));
	assertTrue(message, adapter.isAdjacent(target, source));
    }

    protected <N, T> void nodes(ModelAdapter<N, T> adapter, int expected) {
	assertEquals(adapter.getNumberOfNodes(), expected, "Check node");
	assertEquals(adapter.getNodes().size(), expected, "Check node");
    }

    protected <N, T> void edges(ModelAdapter<N, T> adapter, int expected) {
	assertEquals(adapter.getNumberOfEdges(), expected, "Check edge");
    }

    protected <N, T> void neighbor(ModelAdapter<N, T> adapter, N source, N target) {
	notNull(source, target);
	assertTrue(String.format("Check outgoing:  {0} -> {1}", source.toString(), target.toString()),
		adapter.getOutgoingNeighbors(source).contains(target));
	assertTrue(String.format("Check incoming: {0} <- {1}", target.toString(), source.toString()),
		adapter.getIncomingNeighbors(target).contains(source));
	assertTrue(adapter.getNeighbors(source).contains(target));
	assertTrue(adapter.getNeighbors(target).contains(source));
	adjacent(adapter, source, target);
    }

    protected <N, T> void degree(TypedModelAdapter<N, T> adapter, N node, T type, int indegree, int outdegree) {
	notNull(node, type);
	assertEquals(indegree, adapter.getIndegree(node, type), node.toString());
	assertEquals(outdegree, adapter.getOutdegree(node, type), node.toString());
	assertEquals(indegree + outdegree, adapter.getDegree(node, type), node.toString());
    }

    protected <N, T> void adjacent(TypedModelAdapter<N, T> adapter, T type, N source, N target) {
	notNull(source, target, type);
	assertTrue(String.format("Adjacent: {0} {1} {2}", source.toString(), target.toString(), type.toString()),
		adapter.isAdjacentUndirected(source, target, type));
	assertTrue(adapter.isAdjacentUndirected(target, source, type));
    }

    protected <N, T> void neighbor(TypedModelAdapter<N, T> adapter, T type, N source, N target) {
	notNull(source, target, type);
	assertTrue(adapter.getOutgoingNeighbors(source, type).contains(target));
	assertTrue(adapter.getIncomingNeighbors(target, type).contains(source));
	assertTrue(adapter.getNeighbors(source, type).contains(target));
	assertTrue(adapter.getNeighbors(target, type).contains(source));
	adjacent(adapter, type, source, target);
    }

    protected <N, T> void notNeighbor(TypedModelAdapter<N, T> adapter, T type, N source, N target) {
	notNull(source, target, type);
	assertFalse(adapter.getOutgoingNeighbors(source, type).contains(target));
	assertFalse(adapter.getIncomingNeighbors(target, type).contains(source));
    }

    protected <N, T> void types(TypedModelAdapter<N, T> adapter, int expected, N node) {
	notNull(node);
	assertEquals(expected, adapter.getNumberOfTypes(node));
    }

}
