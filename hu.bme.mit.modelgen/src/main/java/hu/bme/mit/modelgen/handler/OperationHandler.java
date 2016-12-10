package hu.bme.mit.modelgen.handler;

import java.util.Iterator;

import hu.bme.mit.mba.modeladapters.ModelAdapter;
import hu.bme.mit.modelgen.model.DomainDescriptor;
import hu.bme.mit.modelgen.op.Operation;
import hu.bme.mit.modelgen.op.ResolvedOperation;

public interface OperationHandler<N, T> {

    public ModelAdapter getModelAdater();

    public void setModelAdater(ModelAdapter modelAdapter);

    public DomainDescriptor<T> getDomainDescriptor();

    public void setDomainDescriptor(DomainDescriptor<T> domainDescriptor);

    public Iterator<ResolvedOperation<N, T>> handle(Operation<N, T> operation);

}
