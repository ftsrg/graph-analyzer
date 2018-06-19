package hu.bme.mit.mba.base.metrics;

import hu.bme.mit.mba.base.data.BaseData;

public interface Metric {

    /**
     * Clears the value of metric.
     */
    public void clear();

    public String getDefaultName();

    public String getName();

    public void setName(final String name);

    public BaseData getData();

}
