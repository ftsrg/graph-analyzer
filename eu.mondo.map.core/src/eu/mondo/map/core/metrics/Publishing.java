package eu.mondo.map.core.metrics;

import java.util.List;

// TODO this should be Publisher or Publishable ?
public interface Publishing {

	public List<PublishedMetric> resolve();

}
