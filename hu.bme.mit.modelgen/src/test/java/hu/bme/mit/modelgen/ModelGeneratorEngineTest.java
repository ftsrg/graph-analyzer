package hu.bme.mit.modelgen;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections4.iterators.EmptyIterator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.google.common.collect.Iterators;
import com.google.common.collect.Table;

import hu.bme.mit.mba.modeladapters.ModelAdapter;
import hu.bme.mit.mba.modeladapters.Triple;
import hu.bme.mit.mba.modelmetrics.impl.simple.NumberOfNodes;
import hu.bme.mit.modelgen.handler.BaseOperationHandler;
import hu.bme.mit.modelgen.handler.EdgeOperationHandler;
import hu.bme.mit.modelgen.handler.NodeOperationHandler;
import hu.bme.mit.modelgen.handler.OperationHandler;
import hu.bme.mit.modelgen.model.EvolvingModelProvider;
import hu.bme.mit.modelgen.model.FakeEvolvingModelProvider;
import hu.bme.mit.modelgen.op.EdgeOperation;
import hu.bme.mit.modelgen.op.NodeOperation;
import hu.bme.mit.modelgen.op.Operation;
import hu.bme.mit.modelgen.op.ResolvedOperation;

public class ModelGeneratorEngineTest {

    @Mock
    public Scheduler<String, String> scheduler;

    @Mock
    public EdgeOperationHandler<String, String> edgeOperationHandler;

    @Mock
    public NodeOperationHandler<String, String> nodeOperationHandler;

    @Mock
    public EvolvingModelProvider<String, String> modelProvider;

    @Mock
    public ModelAdapter modelAdapter;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private ModelGeneratorEngine<String, String> sut;
    private EdgeOperation<String, String> edgeOp;
    private NodeOperation<String, String> nodeOp;
    private final String node0 = "node0";
    private final String node1 = "node1";
    private final String node2 = "node2";
    private final String type1 = "type1";
    private Table<String, String, String> adjacency;

    private void mockOperations(List<Operation<String, String>> operations) {
        when(scheduler.schedule()).thenReturn(operations);
    }

    private void mockResolvedOperation(Operation<String, String> op) {
        when(edgeOperationHandler.handle(op)).thenReturn(EmptyIterator.emptyIterator());
        when(nodeOperationHandler.handle(op)).thenReturn(EmptyIterator.emptyIterator());
    }

    private void mockResolvedOperation(OperationHandler<String, String> handler, Operation<String, String> op) {
        when(handler.handle(op)).thenReturn(EmptyIterator.emptyIterator());
    }

    private void mockResolvedOperation(OperationHandler<String, String> handler, Operation<String, String> op,
            ResolvedOperation<String, String> resolvedOp) {
        when(handler.handle(op)).thenReturn(Iterators.singletonIterator(resolvedOp));
    }

    @SuppressWarnings("unchecked")
    private void verifyNoHandle(BaseOperationHandler<String, String> handler) {
        verify(handler, Mockito.never()).handle(Mockito.any(Operation.class));
    }

    private void verifyHandle(BaseOperationHandler<String, String> handler, int expected,
            Operation<String, String> op) {
        verify(handler, times(expected)).handle(op);
    }

    private void generate() {
        NumberOfNodes maxNodes = new NumberOfNodes();
        maxNodes.getData().setValue(0);
        sut.generate(modelProvider, modelAdapter, scheduler, maxNodes);
    }

    private void checkModel(Table<String, String, String> adjacency, String source, String target, String type) {
        Assert.assertTrue(adjacency.contains(source, target));
        Assert.assertTrue(adjacency.get(source, target) == type);
    }

    @Before
    public void setUp() {
        sut = new ModelGeneratorEngine<String, String>();
        edgeOp = new EdgeOperation<>(null);
        nodeOp = new NodeOperation<>(null);
        sut.add(edgeOperationHandler);
        sut.add(nodeOperationHandler);
        modelProvider = new FakeEvolvingModelProvider();
        adjacency = ((FakeEvolvingModelProvider) modelProvider).getAdjacency();
    }

    @Test
    public void testGenerate_DelegateToHandlers() {
        mockOperations(Arrays.asList(edgeOp, nodeOp));
        mockResolvedOperation(nodeOp);
        mockResolvedOperation(edgeOp);
        generate();

        verify(scheduler, times(1)).schedule();
        verifyHandle(edgeOperationHandler, 1, edgeOp);
        verifyHandle(edgeOperationHandler, 1, nodeOp);
        verifyHandle(nodeOperationHandler, 1, edgeOp);
        verifyHandle(nodeOperationHandler, 1, nodeOp);
    }

    @Test
    public void testGenerate_WithoutOps() {
        generate();

        verify(scheduler, times(1)).schedule();
        verifyNoHandle(edgeOperationHandler);
        verifyNoHandle(nodeOperationHandler);
    }

    @Test
    public void testGenerate_WithoutHandlers() {
        sut.getOperationHandlers().clear();

        mockOperations(Arrays.asList(edgeOp, nodeOp));
        thrown.expect(IllegalStateException.class);
        generate();
    }

    @Test
    public void testGenerate_InitModel() {
        mockOperations(Arrays.asList(edgeOp, nodeOp));
        mockResolvedOperation(nodeOp);
        mockResolvedOperation(edgeOp);

        EvolvingModelProvider<String, String> spiedModelProvider = Mockito.spy(modelProvider);
        Assert.assertTrue(adjacency.isEmpty());
        Assert.assertTrue(((FakeEvolvingModelProvider) spiedModelProvider).getAdjacency().isEmpty());

        NumberOfNodes numberOfNodes = new NumberOfNodes();
        numberOfNodes.getData().setValue(0);

        sut.generate(spiedModelProvider, modelAdapter, scheduler, numberOfNodes);

        checkModel(adjacency, node0, node1, type1);

        verify(spiedModelProvider, times(1)).addEdge();

        Triple<String, String> initEdge = new Triple<String, String>(node0, node1, type1);
        verify(modelAdapter, times(1)).build(initEdge);
    }

    @Test
    public void testGenerate_AddNodeOperation() {
        mockOperations(Arrays.asList(nodeOp));
        mockResolvedOperation(edgeOperationHandler, nodeOp);
        mockResolvedOperation(nodeOperationHandler, nodeOp, new ResolvedOperation<String, String>(node1, type1));

        generate();

        checkModel(adjacency, node0, node1, type1);
        checkModel(adjacency, node2, node1, type1);

        verify(modelAdapter, times(1)).build(node2, node1, type1);
    }

    @Test
    public void testGenerate_AddEdgeOperation() {
        mockOperations(Arrays.asList(nodeOp));
        mockResolvedOperation(nodeOperationHandler, nodeOp);
        mockResolvedOperation(edgeOperationHandler, nodeOp, new ResolvedOperation<String, String>(node1, node2, type1));

        generate();

        checkModel(adjacency, node0, node1, type1);
        checkModel(adjacency, node1, node2, type1);

        verify(modelAdapter, times(1)).build(node1, node2, type1);
    }

}
