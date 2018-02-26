package hu.bme.mit.modelgen.handler;

import java.util.Iterator;

import org.apache.commons.collections4.iterators.SingletonIterator;
import org.apache.log4j.Logger;

import hu.bme.mit.modelgen.op.NodeOperation;
import hu.bme.mit.modelgen.op.ResolvedOperation;

public class NodeOperationHandler<N, T> extends BaseOperationHandler<N, T> {

    private final static Logger logger = Logger.getLogger(NodeOperationHandler.class);

    @Override
    public Iterator<ResolvedOperation<N, T>> handle(NodeOperation<N, T> operation) {
        logger.debug("Start resolving NodeOperation: " + operation);

        ResolvedOperation.Builder<N, T> builder = new ResolvedOperation.Builder<>();
        if (operation.getTarget().isPresent()) {
            builder.target(operation.getTarget().get());
        } else {
            builder.target(getRandomNode());
        }

        if (operation.getType().isPresent()) {
            builder.type(operation.getType().get());
        } else {
            builder.type(getRandomType());
        }

        return new SingletonIterator<ResolvedOperation<N, T>>(builder.build());
    }

}
