package hu.bme.mit.mba.base.metrics;

import hu.bme.mit.mba.base.data.BaseData;

public abstract class BaseMetric<D extends BaseData> implements Metric {

    protected D data;
    protected String name;
    protected String defaultName;

    public BaseMetric(final String defaultName) {
        this.defaultName = defaultName;
        name = defaultName;
    }

    @Override
    public String getDefaultName() {
        return defaultName;
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
