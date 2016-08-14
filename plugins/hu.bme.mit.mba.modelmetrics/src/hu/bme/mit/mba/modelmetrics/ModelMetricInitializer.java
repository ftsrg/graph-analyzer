package hu.bme.mit.mba.modelmetrics;

public interface ModelMetricInitializer {

    default public <N, T> ModelMetric instantiate() throws InstantiationException, IllegalAccessException {
        return getMetric().newInstance();
    }

    public Class<? extends ModelMetric> getMetric();

}
