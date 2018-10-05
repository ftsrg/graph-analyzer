package hu.bme.mit.ga.base.metrics;

import hu.bme.mit.ga.base.data.BaseData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class BaseMetric<D extends BaseData> implements Metric {

    protected D data;

    protected List<Map<String,Object>> performanceData = new ArrayList<>();
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

    public List<Map<String, Object>> getPerformanceData() {
        return performanceData;
    }

}
