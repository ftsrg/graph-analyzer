package hu.bme.mit.mba.modelmetrics;

import hu.bme.mit.mba.base.data.BaseData;

public interface TraceableModelMetric extends ModelMetric {

    <N, T> void trace();

    <N, T> BaseData getTracing();
}
