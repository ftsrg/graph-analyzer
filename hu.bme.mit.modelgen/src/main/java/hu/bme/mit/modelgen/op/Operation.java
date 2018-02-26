package hu.bme.mit.modelgen.op;

import java.util.Iterator;

import hu.bme.mit.modelgen.guide.MetricGuide;
import hu.bme.mit.modelgen.handler.BaseOperationHandler;

public abstract class Operation<N, T> {

    private MetricGuide<N, T> sourceGuide;

    public Operation(MetricGuide<N, T> sourceGuide) {
        this.sourceGuide = sourceGuide;
    }

    public MetricGuide<N, T> getSourceGuide() {
        return sourceGuide;
    }

    public abstract Iterator<ResolvedOperation<N, T>> resolve(BaseOperationHandler<N, T> handler);

}
