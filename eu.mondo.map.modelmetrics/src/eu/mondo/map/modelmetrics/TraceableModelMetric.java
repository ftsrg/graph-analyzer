package eu.mondo.map.modelmetrics;

import eu.mondo.map.base.data.BaseData;

public interface TraceableModelMetric extends ModelMetric {

	public <N, T> void trace();

	public <N, T> BaseData getTracing();
}
