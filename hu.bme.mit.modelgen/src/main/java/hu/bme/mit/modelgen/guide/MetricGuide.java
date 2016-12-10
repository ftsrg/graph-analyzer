package hu.bme.mit.modelgen.guide;

import java.util.LinkedList;
import java.util.Set;

import hu.bme.mit.mba.modeladapters.ModelAdapter;
import hu.bme.mit.modelgen.op.Operation;
import hu.bme.mit.modelgen.op.OperationConsumer;
import hu.bme.mit.modelgen.op.OperationProvider;

public abstract class MetricGuide<N, T> implements OperationProvider<N, T> {

    protected LinkedList<OperationConsumer<N, T>> consumers;

    public MetricGuide() {
        consumers = new LinkedList<>();
    }

    @Override
    public void register(OperationConsumer<N, T> consumer) {
        consumers.addLast(consumer);
    }

    @Override
    public void provide() {
        consumers.forEach(consumer -> {
            pollOperations().forEach(op -> consumer.consume(op));
        });
    }

    protected abstract Set<Operation<N, T>> pollOperations();

    public abstract void evaluate(ModelAdapter modelAdapter);

}
