package eu.mondo.map.core.metrics;

import java.util.List;

public interface Publishing extends Metric {

	public List<PublishedMetric> resolve();

	public String getName();
}
