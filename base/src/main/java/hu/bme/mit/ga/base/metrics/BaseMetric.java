package hu.bme.mit.ga.base.metrics;

import hu.bme.mit.ga.base.data.BaseData;

public abstract class BaseMetric<D extends BaseData> implements Metric {

    protected D data;
    protected String name;

    public BaseMetric(final String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public D getData() {
        return data;
    }

}
