package hu.bme.mit.mba.modelmetrics;

public interface ModelMetricInitializer {

    public <N, T> ModelMetric instantiate();

}
