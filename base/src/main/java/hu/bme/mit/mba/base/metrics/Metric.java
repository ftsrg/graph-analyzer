package hu.bme.mit.mba.base.metrics;

import hu.bme.mit.mba.base.data.BaseData;

public interface Metric {

    String getName();

    BaseData getData();

}
