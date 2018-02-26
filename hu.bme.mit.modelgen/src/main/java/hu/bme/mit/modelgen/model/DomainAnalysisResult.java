package hu.bme.mit.modelgen.model;

import java.util.HashSet;
import java.util.Set;

public class DomainAnalysisResult<T> {

    private DomainDescriptor<T> domainDescriptor;

    private Set<MetricEquality> metricEqualities;

    public DomainAnalysisResult(DomainDescriptor<T> domainDescriptor) {
        this.domainDescriptor = domainDescriptor;
        metricEqualities = new HashSet<>();
    }

    public DomainDescriptor<T> getDomainDescriptor() {
        return domainDescriptor;
    }

    public Set<MetricEquality> getMetricEqualities() {
        return metricEqualities;
    }

    public void addMetricEquality(MetricEquality equality) {
        metricEqualities.add(equality);
    }
}
