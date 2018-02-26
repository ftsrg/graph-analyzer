package hu.bme.mit.modelgen.guide;

import java.util.LinkedList;

import hu.bme.mit.modelgen.op.Operation;
import hu.bme.mit.modelgen.op.OperationConsumer;

//TODO change name
public class MetricGuideDescriptor<N, T> implements OperationConsumer<N, T> {

    private MetricGuide<N, T> guide;
    private Priorities priority;
    private LinkedList<Operation<N, T>> activeOperations;

    public MetricGuideDescriptor(MetricGuide<N, T> guide, Priorities priority) {
        this.guide = guide;
        this.priority = priority;
        activeOperations = new LinkedList<>();
        this.guide.register(this);
    }

    public MetricGuide<N, T> getGuide() {
        return guide;
    }

    public Priorities getPriority() {
        return priority;
    }

    @Override
    public void consume(Operation<N, T> operation) {
        activeOperations.add(operation);
    }

    public boolean hasActiveOperations() {
        return !activeOperations.isEmpty();
    }

    public Operation<N, T> pollActiveOperation() {
        return activeOperations.poll();
    }

    @Override
    public String toString() {
        return "MetricGuideDescriptor [guide=" + guide + ", priority=" + priority + ", number of activeOperations="
                + activeOperations.size() + "]";
    }

}
