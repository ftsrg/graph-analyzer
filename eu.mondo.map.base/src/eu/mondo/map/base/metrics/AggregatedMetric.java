package eu.mondo.map.base.metrics;

import eu.mondo.map.base.data.ListData;
import eu.mondo.map.base.data.ScalarData;

public class AggregatedMetric<D extends ListData<? extends Number>> extends BaseMetric<ScalarData<Double>> {

	protected String listMetricName;
	boolean useDefault;

	public AggregatedMetric() {
		super("AggregatedMetric");
		useDefault = true;
	}

	public void calculateAverage(final D list) {
		double sum = 0.0;
		for (int i = 0; i < list.size(); i++) {
			sum += list.get(i).doubleValue();
		}
		data.setValue(sum / list.size());
		listMetricName = "Average";
	}

	@Override
	public void setName(final String name) {
		super.setName(name);
		useDefault = false;
	}

}
