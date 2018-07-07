package hu.bme.mit.mba.modelmetrics;

public interface ModelMetricInitializer {

    default ModelMetric instantiate() throws InstantiationException, IllegalAccessException {
        return getMetric().newInstance();
    }

    Class<? extends ModelMetric> getMetric();

}
