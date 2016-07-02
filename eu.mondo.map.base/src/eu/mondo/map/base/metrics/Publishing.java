package eu.mondo.map.base.metrics;

import java.util.List;

// TODO this should be Publisher or Publishable ?
public interface Publishing {

	public List<PublishedMetric> resolve();

}
