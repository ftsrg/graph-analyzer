package eu.mondo.map.modelmetrics;

public interface ModelMetricInitializer {

	public <N, T> AbstractModelMetric<?> instantiate();

}
