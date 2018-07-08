package hu.bme.mit.mba.modelmetrics;

public interface GraphMetricInitializer {

    default GraphMetric instantiate() throws InstantiationException, IllegalAccessException {
        return getMetric().newInstance();
    }

    Class<? extends GraphMetric> getMetric();

}
