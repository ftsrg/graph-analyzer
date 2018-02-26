package hu.bme.mit.modelgen;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.common.base.Preconditions;

import hu.bme.mit.mba.modeladapters.ModelAdapter;
import hu.bme.mit.mba.modeladapters.Triple;
import hu.bme.mit.mba.modelmetrics.impl.simple.NumberOfNodes;
import hu.bme.mit.modelgen.handler.OperationHandler;
import hu.bme.mit.modelgen.model.EvolvingModelProvider;
import hu.bme.mit.modelgen.op.ResolvedOperation;

public class ModelGeneratorEngine<N, T> {

    private List<OperationHandler<N, T>> operationHandlers;

    private final static Logger logger = Logger.getLogger(ModelGeneratorEngine.class);

    public ModelGeneratorEngine() {
        operationHandlers = new ArrayList<>();
    }

    public void generate(EvolvingModelProvider<N, T> modelProvider, ModelAdapter modelAdapter,
            Scheduler<N, T> scheduler, NumberOfNodes maxNumberOfNodes) {
        Preconditions.checkState(!operationHandlers.isEmpty(),
                "The ModelGeneratorEngine does not contain any OperationHandler object");

        initModel(modelProvider, modelAdapter);

        do {
            scheduler.schedule().forEach(operation -> operationHandlers.forEach(handler -> {
                logger.debug(String.format("Handle operation %s via operation handler: %s", operation, handler));
                Iterator<ResolvedOperation<N, T>> resolvedOpsIterator = handler.handle(operation);
                modifyModel(modelProvider, modelAdapter, resolvedOpsIterator);
            }));

            scheduler.getGuideDescriptors().forEach(gd -> gd.getGuide().evaluate(modelAdapter));
        } while (maxNumberOfNodes.getData().getValue() > modelAdapter.getNumberOfNodes());
    }

    private void initModel(EvolvingModelProvider<N, T> modelProvider, ModelAdapter modelAdapter) {
        if (modelAdapter.getNumberOfNodes() == 0) {
            Triple<N, T> triple = modelProvider.addEdge();
            modelAdapter.build(triple);
        }
    }

    private void modifyModel(EvolvingModelProvider<N, T> modelProvider, ModelAdapter modelAdapter,
            Iterator<ResolvedOperation<N, T>> resolvedOpsIterator) {
        while (resolvedOpsIterator.hasNext()) {
            ResolvedOperation<N, T> op = resolvedOpsIterator.next();
            logger.debug("Modifying model with the resolved operation: " + op);
            N sourceNode;
            if (op.getSourceNode().isPresent()) {
                modelProvider.addEdge(op.getSourceNode().get(), op.getTargetNode(), op.getEdgeType());
                sourceNode = op.getSourceNode().get();
            } else {
                sourceNode = modelProvider.addNode(op.getTargetNode(), op.getEdgeType());
            }
            modelAdapter.build(sourceNode, op.getTargetNode(), op.getEdgeType());
        }
    }

    public List<OperationHandler<N, T>> getOperationHandlers() {
        return operationHandlers;
    }

    public void add(OperationHandler<N, T> operationHandler) {
        operationHandlers.add(operationHandler);
    }

    public void addAll(Collection<OperationHandler<N, T>> operationHandlers) {
        operationHandlers.addAll(operationHandlers);
    }

}
