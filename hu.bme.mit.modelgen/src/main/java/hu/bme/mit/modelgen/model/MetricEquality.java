package hu.bme.mit.modelgen.model;

import java.util.Objects;

import hu.bme.mit.mba.modelmetrics.ModelMetric;

public class MetricEquality {

    private ModelMetric metric;
    private double equality;

    public MetricEquality(ModelMetric metric, double equality) {
        this.metric = metric;
        this.equality = equality;
    }

    public ModelMetric getMetric() {
        return metric;
    }

    public double getEquality() {
        return equality;
    }

    @Override
    public int hashCode() {
        return Objects.hash(metric, equality);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        MetricEquality other = (MetricEquality) obj;
        if (Double.doubleToLongBits(equality) != Double.doubleToLongBits(other.equality)) {
            return false;
        }
        if (metric == null) {
            if (other.metric != null) {
                return false;
            }
        } else if (!metric.equals(other.metric)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MetricEquality [metric=" + metric + ", equality=" + equality + "]";
    }

}
