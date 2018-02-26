package hu.bme.mit.modelgen.handler;

import java.util.Iterator;

import org.apache.commons.collections4.iterators.EmptyIterator;
import org.apache.log4j.Logger;

import hu.bme.mit.modelgen.op.EdgeOperation;
import hu.bme.mit.modelgen.op.ResolvedOperation;

public class EdgeOperationHandler<N, T> extends BaseOperationHandler<N, T> {

    private final static Logger logger = Logger.getLogger(EdgeOperationHandler.class);

    @Override
    public Iterator<ResolvedOperation<N, T>> handle(EdgeOperation<N, T> operation) {
        logger.debug("Start resolving EdgeOperation: " + operation);
        return EmptyIterator.emptyIterator();
    }

}
