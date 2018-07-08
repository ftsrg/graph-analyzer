package hu.bme.mit.ga.base.metrics;

import hu.bme.mit.ga.base.data.BaseData;

public interface Metric {

    String getName();

    BaseData getData();

}
