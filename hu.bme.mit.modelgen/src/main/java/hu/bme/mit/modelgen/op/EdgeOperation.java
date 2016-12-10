package hu.bme.mit.modelgen.op;

import java.util.Iterator;
import java.util.Optional;

import hu.bme.mit.modelgen.guide.MetricGuide;
import hu.bme.mit.modelgen.handler.BaseOperationHandler;

public class EdgeOperation<N, T> extends Operation<N, T> {

    private Optional<N> source;
    private Optional<N> target;
    private Optional<T> type;

    public EdgeOperation(MetricGuide<N, T> metricGuide) {
        super(metricGuide);
    }

    @Override
    public Iterator<ResolvedOperation<N, T>> resolve(BaseOperationHandler<N, T> handler) {
        return handler.handle(this);
    }

    public EdgeOperation<N, T> anySource() {
        source = Optional.empty();
        return this;
    }

    public EdgeOperation<N, T> anyTarget() {
        target = Optional.empty();
        return this;
    }

    public EdgeOperation<N, T> anyType() {
        type = Optional.empty();
        return this;
    }

    public EdgeOperation<N, T> source(N source) {
        this.source = Optional.ofNullable(source);
        return this;
    }

    public EdgeOperation<N, T> target(N target) {
        this.target = Optional.ofNullable(target);
        return this;
    }

    public EdgeOperation<N, T> type(T type) {
        this.type = Optional.ofNullable(type);
        return this;
    }

    public Optional<N> getSource() {
        return source;
    }

    public Optional<N> getTarget() {
        return target;
    }

    public Optional<T> getType() {
        return type;
    }

    @Override
    public String toString() {
        return "EdgeOperation [source=" + source + ", target=" + target + ", type=" + type + "]";
    }

}
