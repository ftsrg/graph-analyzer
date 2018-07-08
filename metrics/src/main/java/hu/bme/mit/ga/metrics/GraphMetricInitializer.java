package hu.bme.mit.ga.metrics;

public interface GraphMetricInitializer {

    default GraphMetric instantiate() throws InstantiationException, IllegalAccessException {
        return getMetric().newInstance();
    }

    Class<? extends GraphMetric> getMetric();

}
