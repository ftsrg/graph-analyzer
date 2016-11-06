package hu.bme.mit.mba.modelmetrics;

import hu.bme.mit.mba.base.data.BaseData;

public interface TraceableModelMetric extends ModelMetric {

    public <N, T> void trace();

    public <N, T> BaseData getTracing();
}
