package hu.bme.mit.modelgen.op;

import java.util.Iterator;
import java.util.Optional;

import hu.bme.mit.modelgen.guide.MetricGuide;
import hu.bme.mit.modelgen.handler.BaseOperationHandler;

public class NodeOperation<N, T> extends Operation<N, T> {

    private Optional<N> target;
    private Optional<T> type;

    public NodeOperation(MetricGuide<N, T> sourceGuide) {
        super(sourceGuide);
    }

    @Override
    public Iterator<ResolvedOperation<N, T>> resolve(BaseOperationHandler<N, T> handler) {
        return handler.handle(this);
    }

    public NodeOperation<N, T> anyTarget() {
        target = Optional.empty();
        return this;
    }

    public NodeOperation<N, T> anyType() {
        type = Optional.empty();
        return this;
    }

    public NodeOperation<N, T> target(N target) {
        this.target = Optional.ofNullable(target);
        return this;
    }

    public NodeOperation<N, T> type(T type) {
        this.type = Optional.ofNullable(type);
        return this;
    }

    public Optional<N> getTarget() {
        return target;
    }

    public Optional<T> getType() {
        return type;
    }

    @Override
    public String toString() {
        return "NodeOperation [target=" + target + ", type=" + type + "]";
    }

}
