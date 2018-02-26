package hu.bme.mit.modelgen.handler;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.commons.collections4.iterators.EmptyIterator;
import org.apache.log4j.Logger;

import com.google.common.collect.Lists;

import hu.bme.mit.mba.modeladapters.ModelAdapter;
import hu.bme.mit.modelgen.model.DomainDescriptor;
import hu.bme.mit.modelgen.op.EdgeOperation;
import hu.bme.mit.modelgen.op.NodeOperation;
import hu.bme.mit.modelgen.op.Operation;
import hu.bme.mit.modelgen.op.ResolvedOperation;

public abstract class BaseOperationHandler<N, T> implements OperationHandler<N, T> {

    private final static Logger logger = Logger.getLogger(BaseOperationHandler.class);
    protected static final int randomSeed = 45238323;

    protected final Random random;
    protected ModelAdapter modelAdapter;
    protected DomainDescriptor<T> domainDescriptor;
    protected List<T> availableTypesPerDomain;

    public BaseOperationHandler() {
        random = new Random(randomSeed);
    }

    @Override
    public ModelAdapter getModelAdater() {
        return modelAdapter;
    }

    @Override
    public void setModelAdater(ModelAdapter modelAdapter) {
        this.modelAdapter = modelAdapter;
    }

    @Override
    public DomainDescriptor<T> getDomainDescriptor() {
        return domainDescriptor;
    }

    @Override
    public void setDomainDescriptor(DomainDescriptor<T> domainDescriptor) {
        this.domainDescriptor = domainDescriptor;
    }

    @Override
    public Iterator<ResolvedOperation<N, T>> handle(Operation<N, T> operation) {
        return operation.resolve(this);
    }

    public Iterator<ResolvedOperation<N, T>> handle(EdgeOperation<N, T> operation) {
        logger.trace("Skip handling edge operation " + operation);
        return EmptyIterator.emptyIterator();
    }

    public Iterator<ResolvedOperation<N, T>> handle(NodeOperation<N, T> operation) {
        logger.trace("Skip handling node operation " + operation);
        return EmptyIterator.emptyIterator();
    }

    protected N getRandomNode() {
        int maxNodes = modelAdapter.getNumberOfNodes();
        return Lists.newArrayList(modelAdapter.<N, T>getNodes()).get(random.nextInt(maxNodes));
    }

    protected T getRandomType() {
        int maxTypes = modelAdapter.getNumberOfTypes();
        if (maxTypes == 0) {
            return getRandomTypePerDomain();
        } else {
            return Lists.newArrayList(modelAdapter.<N, T>getTypes()).get(random.nextInt(maxTypes));
        }
    }

    protected T getRandomTypePerDomain() {
        if (availableTypesPerDomain == null) {
            availableTypesPerDomain = Lists.newArrayList(domainDescriptor.getAvailableTypes());
        }
        int maxTypes = domainDescriptor.getAvailableTypes().size();
        return availableTypesPerDomain.get(random.nextInt(maxTypes));
    }

}
