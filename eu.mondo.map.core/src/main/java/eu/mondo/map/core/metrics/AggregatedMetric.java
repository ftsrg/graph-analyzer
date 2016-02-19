package eu.mondo.map.core.metrics;

import java.util.ArrayList;
import java.util.List;

public class AggregatedMetric<M extends ListMetric<? extends Number>> extends ScalarMetric<Double> {

	protected String listMetricName;

	@Override
	public void clear() {
		value = null;
	}

	@Override
	public String getName() {
		return "AggregatedMetric";
	}

	public void calculateAverage(final M list) {
		double sum = 0.0;
		for (int i = 0; i < list.size(); i++) {
			sum += list.get(i).doubleValue();
		}
		value = sum / list.size();
		listMetricName = "Average";
	}

	@Override
	public List<PublishedMetric> resolve() {
		List<PublishedMetric> metrics = new ArrayList<PublishedMetric>();
		metrics.add(new PublishedMetric(value.toString(), String.format("%s-%s", getName(),
				listMetricName)));
		return metrics;
	}

}
