package hu.bme.mit.ga.adapters.tests;

import hu.bme.mit.ga.adapters.GraphAdapter;
import hu.bme.mit.ga.tests.graph.TestGraph;
import hu.bme.mit.ga.tests.graph.TestGraphInstances;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static hu.bme.mit.ga.tests.graph.TestGraphConstants.type1;
import static hu.bme.mit.ga.tests.graph.TestGraphConstants.type2;
import static hu.bme.mit.ga.tests.graph.TestGraphConstants.node1;
import static hu.bme.mit.ga.tests.graph.TestGraphConstants.node2;
import static hu.bme.mit.ga.tests.graph.TestGraphConstants.node3;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

public class GraphAdapterTest {

    protected TestGraphAdapter adapter;
    protected TestGraph model;

    @Test(dataProvider = "data")
    public void testAdapter(TestGraphInstances modelType, Runnable checker) throws IOException {
        runTests(modelType, checker);
    }

    @Test(dataProvider = "typedData")
    public void testTypedAdapter(TestGraphInstances modelType, Runnable checker) throws IOException {
        runTests(modelType, checker);
    }

    protected void runTests(TestGraphInstances modelType, Runnable checker) throws IOException {
        model = modelType.init();
        adapter = new TestGraphAdapter();
        adapter.init(model);

        checker.run();
    }

    @DataProvider
    public Object[][] data() {
        List<Object[]> casesList = new ArrayList<>();
        for (TestGraphInstances type : TestGraphInstances.values()) {
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
        for (TestGraphInstances type : TestGraphInstances.values()) {
            casesList.add(typedTestCase(type));
        }

        Object[][] casesArray = new Object[casesList.size()][2];
        for (int i = 0; i < casesList.size(); i++) {
            casesArray[i] = casesList.get(i);
        }
        return casesArray;
    }

    protected Object[] testCase(TestGraphInstances modelType) {
        Runnable checker = () -> {
        };
        switch (modelType) {
            case Loop_1T:
                checker = () -> {
                    nodes(adapter, 1);
                    edges(adapter, 1);
                    degree(adapter, node1, 1, 1);
                    neighbor(adapter, node1, node1);
                };
                break;
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
        }

        return new Object[]{modelType, checker};
    }

    protected Object[] typedTestCase(TestGraphInstances modelType) {
        Runnable checker = () -> {
        };
        switch (modelType) {
            case Loop_2T:
                checker = () -> {
                    degree(adapter, node1, type1, 1, 1);
                    degree(adapter, node1, type2, 1, 1);
                    neighbor(adapter, type1, node1, node1);
                    neighbor(adapter, type2, node1, node1);
                    types(adapter, 2, node1);
                    types(adapter, 2);
                    nodes(adapter, type1, 1);
                    nodes(adapter, type2, 1);
                };
                break;
            case Motif3N_3_2T:
                checker = () -> {
                    degree(adapter, node1, type1, 0, 2);
                    degree(adapter, node1, type2, 1, 0);
                    degree(adapter, node2, type1, 1, 0);
                    degree(adapter, node2, type2, 0, 1);
                    degree(adapter, node3, type1, 1, 0);
                    degree(adapter, node3, type2, 0, 0);

                    neighbor(adapter, type1, node1, node2);
                    neighbor(adapter, type2, node2, node1);
                    neighbor(adapter, type1, node1, node3);

                    notNeighbor(adapter, type2, node1, node2);
                    notNeighbor(adapter, type2, node1, node3);
                    types(adapter, 2, node1);
                    types(adapter, 2, node2);
                    types(adapter, 1, node3);
                    types(adapter, 2);
                    nodes(adapter, type1, 3);
                    nodes(adapter, type2, 2);
                };
                break;
            case Motif3N_6_2T:
                checker = () -> {
                    degree(adapter, node1, type1, 0, 2);
                    degree(adapter, node1, type2, 1, 0);
                    degree(adapter, node2, type1, 1, 1);
                    degree(adapter, node2, type2, 0, 1);
                    degree(adapter, node3, type1, 2, 0);
                    degree(adapter, node3, type2, 0, 0);

                    neighbor(adapter, type1, node1, node2);
                    neighbor(adapter, type2, node2, node1);
                    neighbor(adapter, type1, node2, node3);
                    neighbor(adapter, type1, node1, node3);

                    notNeighbor(adapter, type2, node1, node2);
                    notNeighbor(adapter, type2, node2, node3);
                    notNeighbor(adapter, type2, node1, node3);
                    types(adapter, 2, node1);
                    types(adapter, 2, node2);
                    types(adapter, 1, node3);
                    types(adapter, 2);
                    nodes(adapter, type1, 3);
                    nodes(adapter, type2, 2);
                };
                break;
            case Motif3N_7_2T:
                checker = () -> {
                    degree(adapter, node1, type1, 1, 1);
                    degree(adapter, node1, type2, 1, 0);
                    degree(adapter, node2, type1, 1, 0);
                    degree(adapter, node2, type2, 0, 1);
                    degree(adapter, node3, type1, 0, 1);
                    degree(adapter, node3, type2, 0, 0);

                    neighbor(adapter, type1, node1, node2);
                    neighbor(adapter, type2, node2, node1);
                    neighbor(adapter, type1, node3, node1);

                    notNeighbor(adapter, type2, node1, node2);
                    notNeighbor(adapter, type2, node3, node1);
                    notNeighbor(adapter, type1, node1, node3);
                    types(adapter, 2, node1);
                    types(adapter, 2, node2);
                    types(adapter, 1, node3);
                    types(adapter, 2);
                    nodes(adapter, type1, 3);
                    nodes(adapter, type2, 2);
                };
                break;
            case Motif3N_8_2T:
                checker = () -> {
                    degree(adapter, node1, type1, 0, 2);
                    degree(adapter, node1, type2, 2, 0);
                    degree(adapter, node2, type1, 1, 0);
                    degree(adapter, node2, type2, 0, 1);
                    degree(adapter, node3, type1, 1, 0);
                    degree(adapter, node3, type2, 0, 1);

                    neighbor(adapter, type1, node1, node2);
                    neighbor(adapter, type2, node2, node1);
                    neighbor(adapter, type2, node3, node1);
                    neighbor(adapter, type1, node1, node3);

                    notNeighbor(adapter, type2, node1, node2);
                    notNeighbor(adapter, type1, node2, node1);
                    notNeighbor(adapter, type1, node3, node1);
                    notNeighbor(adapter, type2, node1, node3);
                    types(adapter, 2, node1);
                    types(adapter, 2, node2);
                    types(adapter, 2, node3);
                    types(adapter, 2);
                    nodes(adapter, type1, 3);
                    nodes(adapter, type2, 3);
                };
                break;
            case Motif3N_10_2T:
                checker = () -> {
                    degree(adapter, node1, type1, 0, 2);
                    degree(adapter, node1, type2, 1, 0);
                    degree(adapter, node2, type1, 1, 1);
                    degree(adapter, node2, type2, 0, 0);
                    degree(adapter, node3, type1, 2, 0);
                    degree(adapter, node3, type2, 0, 1);
                    neighbor(adapter, type1, node1, node2);
                    neighbor(adapter, type2, node3, node1);
                    neighbor(adapter, type1, node1, node3);
                    neighbor(adapter, type1, node2, node3);

                    notNeighbor(adapter, type2, node1, node2);
                    notNeighbor(adapter, type2, node2, node1);
                    notNeighbor(adapter, type1, node2, node1);
                    notNeighbor(adapter, type1, node3, node1);
                    notNeighbor(adapter, type2, node1, node3);
                    notNeighbor(adapter, type2, node2, node3);
                    types(adapter, 2, node1);
                    types(adapter, 1, node2);
                    types(adapter, 2, node3);
                    types(adapter, 2);
                    nodes(adapter, type1, 3);
                    nodes(adapter, type2, 2);
                };
                break;
            case Motif3N_13_2T:
                checker = () -> {
                    degree(adapter, node1, type1, 0, 2);
                    degree(adapter, node1, type2, 2, 0);
                    degree(adapter, node2, type1, 1, 1);
                    degree(adapter, node2, type2, 1, 1);
                    degree(adapter, node3, type1, 2, 0);
                    degree(adapter, node3, type2, 0, 2);
                    neighbor(adapter, type1, node1, node2);
                    neighbor(adapter, type1, node1, node3);
                    neighbor(adapter, type2, node2, node1);
                    neighbor(adapter, type1, node2, node3);
                    neighbor(adapter, type2, node3, node2);
                    neighbor(adapter, type2, node3, node1);

                    notNeighbor(adapter, type2, node1, node2);
                    notNeighbor(adapter, type2, node1, node3);
                    notNeighbor(adapter, type1, node2, node1);
                    notNeighbor(adapter, type2, node2, node3);
                    notNeighbor(adapter, type1, node3, node2);
                    notNeighbor(adapter, type1, node3, node1);
                    types(adapter, 2, node1);
                    types(adapter, 2, node2);
                    types(adapter, 2, node3);
                    types(adapter, 2);
                    nodes(adapter, type1, 3);
                    nodes(adapter, type2, 3);
                };
                break;
            default:
        }
        return new Object[]{modelType, checker};
    }

    protected void notNull(Object... elements) {
        for (Object el : elements) {
            assertNotNull(el);
        }
    }

    protected <N, T> void degree(GraphAdapter<N, T> adapter, N node, int indegree, int outdegree) {
        notNull(node);
        assertEquals(indegree, adapter.getIndexer().getIndegree(node), node.toString());
        assertEquals(outdegree, adapter.getIndexer().getOutdegree(node), node.toString());
        assertEquals(indegree + outdegree, adapter.getIndexer().getDegree(node), node.toString());
    }

    protected <N, T> void adjacent(GraphAdapter<N, T> adapter, N source, N target) {
        notNull(source, target);
        String message = String.format("%s and %s are not adjacent", source.toString(), target.toString());
        assertTrue(message, adapter.getIndexer().isAdjacentUndirected(source, target));
        assertTrue(message, adapter.getIndexer().isAdjacentUndirected(target, source));
    }

    protected <N, T> void nodes(GraphAdapter<N, T> adapter, int expected) {
        assertEquals(adapter.getIndexer().getNumberOfNodes(), expected, "Check node");
        assertEquals(adapter.getIndexer().getNodes().size(), expected, "Check node");
    }

    protected <N, T> void edges(GraphAdapter<N, T> adapter, int expected) {
        assertEquals(adapter.getIndexer().getNumberOfEdges(), expected, "Check edge");
    }

    protected <N, T> void neighbor(GraphAdapter<N, T> adapter, N source, N target) {
        notNull(source, target);
        assertTrue(String.format("Check outgoing:  %s -> %s", source.toString(), target.toString()),
            adapter.getIndexer().getOutgoingNeighbors(source).contains(target));
        assertTrue(String.format("Check incoming: %s <- %s", target.toString(), source.toString()),
            adapter.getIndexer().getIncomingNeighbors(target).contains(source));
        assertTrue(adapter.getIndexer().getNeighbors(source).contains(target));
        assertTrue(adapter.getIndexer().getNeighbors(target).contains(source));
        adjacent(adapter, source, target);
    }

    protected <N, T> void degree(GraphAdapter<N, T> adapter, N node, T type, int indegree, int outdegree) {
        notNull(node, type);
        assertEquals(indegree, adapter.getIndexer().getIndegree(node, type), node.toString());
        assertEquals(outdegree, adapter.getIndexer().getOutdegree(node, type), node.toString());
        assertEquals(indegree + outdegree, adapter.getIndexer().getDegree(node, type), node.toString());
    }

    protected <N, T> void adjacent(GraphAdapter<N, T> adapter, T type, N source, N target) {
        notNull(source, target, type);
        assertTrue(String.format("Adjacent: %s %s %s", source.toString(), target.toString(), type.toString()),
            adapter.getIndexer().isAdjacentUndirected(source, target, type));
        assertTrue(adapter.getIndexer().isAdjacentUndirected(target, source, type));
    }

    protected <N, T> void neighbor(GraphAdapter<N, T> adapter, T type, N source, N target) {
        notNull(source, target, type);
        assertTrue(adapter.getIndexer().getOutgoing(source, type).contains(target));
        assertTrue(adapter.getIndexer().getIncoming(target, type).contains(source));
        assertTrue(adapter.getIndexer().getNeighbors(source, type).contains(target));
        assertTrue(adapter.getIndexer().getNeighbors(target, type).contains(source));
        adjacent(adapter, type, source, target);
    }

    protected <N, T> void notNeighbor(GraphAdapter<N, T> adapter, T type, N source, N target) {
        notNull(source, target, type);
        assertFalse(adapter.getIndexer().getOutgoing(source, type).contains(target));
        assertFalse(adapter.getIndexer().getIncoming(target, type).contains(source));
    }

    protected <N, T> void types(GraphAdapter<N, T> adapter, int expected, N node) {
        notNull(node);
        assertEquals(adapter.getIndexer().getNumberOfTypes(node), expected);
    }

    protected <N, T> void types(GraphAdapter<N, T> adapter, int expected) {
        assertEquals(adapter.getIndexer().getNumberOfTypes(), expected);
    }

    protected <N, T> void nodes(GraphAdapter<N, T> adapter, T type, int expected) {
        notNull(type);
        assertEquals(adapter.getIndexer().getNumberOfNodes(type), expected);
        assertEquals(adapter.getIndexer().getNodes(type).size(), expected);
    }

}
