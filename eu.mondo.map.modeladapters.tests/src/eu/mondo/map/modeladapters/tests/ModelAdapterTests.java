package eu.mondo.map.modeladapters.tests;

import static eu.mondo.map.tests.model.ModelContext.dim1;
import static eu.mondo.map.tests.model.ModelContext.dim2;
import static eu.mondo.map.tests.model.ModelContext.node1;
import static eu.mondo.map.tests.model.ModelContext.node2;
import static eu.mondo.map.tests.model.ModelContext.node3;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import eu.mondo.map.modeladapters.ModelAdapter;
import eu.mondo.map.modeladapters.TypedModelAdapter;
import eu.mondo.map.tests.model.TestModel;
import eu.mondo.map.tests.model.TestModelTypes;

public class ModelAdapterTests {

    protected TypedModelAdapter<TestModel, String, String> adapter;
    protected TestModel model;

    @Test(dataProvider = "data")
    public void testAdapter(TestModelTypes modelType, Consumer<ModelAdapter<TestModel, String, String>> checker) {
	runTests(modelType, checker);
    }

    @Test(dataProvider = "typedData")
    public void testTypedAdapter(TestModelTypes modelType, Consumer<ModelAdapter<TestModel, String, String>> checker) {
	runTests(modelType, checker);
    }

    protected void runTests(TestModelTypes modelType, Consumer<ModelAdapter<TestModel, String, String>> checker) {
	model = modelType.init();
	adapter = new TestTypedModelAdapter();
	adapter.init(model);

	checker.accept(adapter);

	adapter.getIndexer().clear();
	adapter.init(model);
	checker.accept(adapter);

	adapter.init(model);
	checker.accept(adapter);
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
	Consumer<ModelAdapter<TestModel, String, String>> checker = null;
	switch (modelType) {
	case Motif3N_1:
	    checker = (adapter) -> {
		nodes(3);
		edges(2);
		degree(node1, 0, 2);
		degree(node2, 1, 0);
		degree(node3, 1, 0);
		neighbor(node1, node2);
		neighbor(node1, node3);
	    };
	    break;
	case Motif3N_2:
	    checker = (adapter) -> {
		nodes(3);
		edges(2);
		degree(node1, 1, 1);
		degree(node2, 0, 1);
		degree(node3, 1, 0);
		neighbor(node2, node1);
		adjacent(node1, node2);
		neighbor(node1, node3);
	    };
	    break;
	case Motif3N_3:
	case Motif3N_3_2T:
	    checker = (adapter) -> {
		nodes(3);
		edges(3);
		degree(node1, 1, 2);
		degree(node2, 1, 1);
		degree(node3, 1, 0);
		neighbor(node1, node2);
		neighbor(node2, node1);
		neighbor(node1, node3);
	    };
	    break;
	case Motif3N_4:
	    checker = (adapter) -> {
		nodes(3);
		edges(2);
		degree(node1, 0, 1);
		degree(node2, 0, 1);
		degree(node3, 2, 0);
		neighbor(node1, node3);
		neighbor(node2, node3);
	    };
	    break;
	case Motif3N_5:
	    checker = (adapter) -> {
		nodes(3);
		edges(3);
		degree(node1, 0, 2);
		degree(node2, 1, 1);
		degree(node3, 2, 0);
		neighbor(node1, node2);
		neighbor(node1, node3);
		neighbor(node2, node3);
	    };
	    break;
	case Motif3N_6:
	case Motif3N_6_2T:
	    checker = (adapter) -> {
		nodes(3);
		edges(4);
		degree(node1, 1, 2);
		degree(node2, 1, 2);
		degree(node3, 2, 0);
		neighbor(node1, node2);
		neighbor(node2, node1);
		neighbor(node1, node3);
	    };
	    break;
	case Motif3N_7:
	case Motif3N_7_2T:
	    checker = (adapter) -> {
		nodes(3);
		edges(3);
		degree(node1, 2, 1);
		degree(node2, 1, 1);
		degree(node3, 0, 1);
		neighbor(node1, node2);
		neighbor(node2, node1);
		neighbor(node3, node1);
	    };
	    break;
	case Motif3N_8:
	case Motif3N_8_2T:
	    checker = (adapter) -> {
		nodes(3);
		edges(4);
		degree(node1, 2, 2);
		degree(node2, 1, 1);
		degree(node3, 1, 1);
		neighbor(node1, node2);
		neighbor(node2, node1);
		neighbor(node1, node3);
		neighbor(node3, node1);
	    };
	    break;
	case Motif3N_9:
	    checker = (adapter) -> {
		degree(node1, 1, 1);
		degree(node2, 1, 1);
		degree(node3, 1, 1);
		neighbor(node1, node2);
		neighbor(node2, node3);
		neighbor(node3, node1);
	    };
	    break;
	case Motif3N_10:
	case Motif3N_10_2T:
	    checker = (adapter) -> {
		degree(node1, 1, 2);
		degree(node2, 1, 1);
		degree(node3, 2, 1);
		neighbor(node1, node2);
		neighbor(node2, node3);
		neighbor(node1, node3);
		neighbor(node3, node1);
	    };
	    break;
	case Motif3N_11:
	case Motif3N_11_2T:
	    checker = (adapter) -> {
		degree(node1, 2, 1);
		degree(node2, 0, 2);
		degree(node3, 2, 1);
		neighbor(node2, node1);
		neighbor(node2, node3);
		neighbor(node1, node3);
		neighbor(node3, node1);
	    };
	    break;
	case Motif3N_12:
	case Motif3N_12_2T:
	    checker = (adapter) -> {
		nodes(3);
		edges(5);
		degree(node1, 2, 2);
		degree(node2, 1, 2);
		degree(node3, 2, 1);
		neighbor(node1, node2);
		neighbor(node2, node1);
		neighbor(node1, node3);
		neighbor(node3, node1);
		neighbor(node2, node3);
	    };
	    break;
	case Motif3N_13:
	case Motif3N_13_2T:
	    checker = (adapter) -> {
		nodes(3);
		edges(6);
		degree(node1, 2, 2);
		degree(node2, 2, 2);
		degree(node3, 2, 2);
		neighbor(node1, node2);
		neighbor(node2, node1);
		neighbor(node1, node3);
		neighbor(node3, node1);
		neighbor(node2, node3);
		neighbor(node3, node2);
	    };
	    break;
	default:
	    return null;
	}

	return new Object[] { modelType, checker };
    }

    protected Object[] typedTestCase(TestModelTypes modelType) {
	Consumer<ModelAdapter<TestModel, String, String>> checker = null;
	switch (modelType) {
	case Motif3N_3_2T:
	    checker = (adapter) -> {
		degree(node1, dim1, 0, 2);
		degree(node1, dim2, 1, 0);
		degree(node2, dim1, 1, 0);
		degree(node2, dim2, 0, 1);
		degree(node3, dim1, 1, 0);
		degree(node3, dim2, 0, 0);
		neighbor(dim1, node1, node2);
		notNeighbor(dim2, node1, node2);
		neighbor(dim2, node2, node1);
		neighbor(dim1, node1, node3);
		notNeighbor(dim2, node1, node3);
	    };
	    break;
	case Motif3N_6_2T:
	    checker = (adapter) -> {
		degree(node1, dim1, 0, 2);
		degree(node1, dim2, 1, 0);
		degree(node2, dim1, 1, 1);
		degree(node2, dim2, 0, 1);
		degree(node3, dim1, 2, 0);
		degree(node3, dim2, 0, 0);
		neighbor(dim1, node1, node2);
		notNeighbor(dim2, node1, node2);
		neighbor(dim2, node2, node1);
		neighbor(dim1, node2, node3);
		notNeighbor(dim2, node2, node3);
		neighbor(dim1, node1, node3);
		notNeighbor(dim2, node1, node3);
	    };
	    break;
	case Motif3N_7_2T:
	    checker = (adapter) -> {
		degree(node1, dim1, 1, 1);
		degree(node1, dim2, 1, 0);
		degree(node2, dim1, 1, 0);
		degree(node2, dim2, 0, 1);
		degree(node3, dim1, 0, 1);
		degree(node3, dim2, 0, 0);
		neighbor(dim1, node1, node2);
		notNeighbor(dim2, node1, node2);
		neighbor(dim2, node2, node1);
		neighbor(dim1, node3, node1);
		notNeighbor(dim2, node3, node1);
		notNeighbor(dim1, node1, node3);
	    };
	    break;
	case Motif3N_8_2T:
	    checker = (adapter) -> {
		degree(node1, dim1, 0, 2);
		degree(node1, dim2, 2, 0);
		degree(node2, dim1, 1, 0);
		degree(node2, dim2, 0, 1);
		degree(node3, dim1, 1, 0);
		degree(node3, dim2, 0, 1);
		neighbor(dim1, node1, node2);
		notNeighbor(dim2, node1, node2);
		neighbor(dim2, node2, node1);
		notNeighbor(dim1, node2, node1);
		neighbor(dim2, node3, node1);
		notNeighbor(dim1, node3, node1);
		neighbor(dim1, node1, node3);
		notNeighbor(dim2, node1, node3);
	    };
	    break;
	case Motif3N_10_2T:
	    checker = (adapter) -> {
		degree(node1, dim1, 0, 2);
		degree(node1, dim2, 1, 0);
		degree(node2, dim1, 1, 1);
		degree(node2, dim2, 0, 0);
		degree(node3, dim1, 2, 0);
		degree(node3, dim2, 0, 1);
		neighbor(dim1, node1, node2);
		notNeighbor(dim2, node1, node2);
		notNeighbor(dim2, node2, node1);
		notNeighbor(dim1, node2, node1);
		neighbor(dim2, node3, node1);
		notNeighbor(dim1, node3, node1);
		neighbor(dim1, node1, node3);
		notNeighbor(dim2, node1, node3);
		neighbor(dim1, node2, node3);
		notNeighbor(dim2, node2, node3);
	    };
	    break;
	case Motif3N_13_2T:
	    checker = (adapter) -> {
		degree(node1, dim1, 0, 2);
		degree(node1, dim2, 2, 0);
		degree(node2, dim1, 1, 1);
		degree(node2, dim2, 1, 1);
		degree(node3, dim1, 2, 0);
		degree(node3, dim2, 0, 2);
		neighbor(dim1, node1, node2);
		neighbor(dim1, node1, node3);
		neighbor(dim2, node2, node1);
		neighbor(dim1, node2, node3);
		neighbor(dim2, node3, node2);
		neighbor(dim2, node3, node1);

		notNeighbor(dim2, node1, node2);
		notNeighbor(dim2, node1, node3);
		notNeighbor(dim1, node2, node1);
		notNeighbor(dim2, node2, node3);
		notNeighbor(dim1, node3, node2);
		notNeighbor(dim1, node3, node1);
	    };
	    break;
	default:
	    return null;
	}
	return new Object[] { modelType, checker };
    }

    protected void degree(String element, int indegree, int outdegree) {
	assertEquals(indegree, adapter.getIndegree(element), element);
	assertEquals(outdegree, adapter.getOutdegree(element), element);
	assertEquals(indegree + outdegree, adapter.getDegree(element), element);
    }

    protected void adjacent(String source, String target) {
	assertTrue(adapter.isAdjacent(source, target));
	assertTrue(adapter.isAdjacent(target, source));
    }

    protected void nodes(int expected) {
	assertEquals(adapter.getNumberOfNodes(), expected);
	assertEquals(adapter.getNodes().size(), expected);
    }

    protected void edges(int expected) {
	assertEquals(adapter.getNumberOfEdges(), expected);
    }

    protected void neighbor(String source, String target) {
	assertTrue(adapter.getOutgoingNeighbors(source).contains(target));
	assertTrue(adapter.getIncomingNeighbors(target).contains(source));
	assertTrue(adapter.getNeighbors(source).contains(target));
	assertTrue(adapter.getNeighbors(target).contains(source));
	adjacent(source, target);
    }

    protected void degree(String element, String type, int indegree, int outdegree) {
	assertEquals(indegree, adapter.getIndegree(element, type), element);
	assertEquals(outdegree, adapter.getOutdegree(element, type), element);
	assertEquals(indegree + outdegree, adapter.getDegree(element, type), element);
    }

    protected void adjacent(String type, String source, String target) {
	assertTrue(adapter.isAdjacent(source, target));
	assertTrue(adapter.isAdjacent(target, source));
    }

    protected void neighbor(String type, String source, String target) {
	assertTrue(adapter.getOutgoingNeighbors(source, type).contains(target));
	assertTrue(adapter.getIncomingNeighbors(target, type).contains(source));
	assertTrue(adapter.getNeighbors(source, type).contains(target));
	assertTrue(adapter.getNeighbors(target, type).contains(source));
	adjacent(type, source, target);
    }

    protected void notNeighbor(String type, String source, String target) {
	assertFalse(adapter.getOutgoingNeighbors(source, type).contains(target));
	assertFalse(adapter.getIncomingNeighbors(target, type).contains(source));
    }

    protected void types(int expected, String node) {
	assertEquals(expected, adapter.getNumberOfTypes(node));
    }

}
