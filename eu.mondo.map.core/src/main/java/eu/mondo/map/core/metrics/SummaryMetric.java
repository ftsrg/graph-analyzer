package eu.mondo.map.core.metrics;

public class SummaryMetric<T extends Comparable<? super T>> extends ScalarMetric<T> {

	@Override
	public void clear() {
		value = null;
	}

	@Override
	public String getName() {
		return "SummaryMetric";
	}

	public void calculateMinimum(final ListMetric<T> list) {
		T min = list.getValues().get(0);
		for (int i = 1; i < list.getValues().size(); i++) {
			if (list.getValues().get(i).compareTo(min) < 0) {
				min = list.getValues().get(i);
			}
		}
		value = min;
	}

	public void calculateMaximum(final ListMetric<T> list) {
		T max = list.getValues().get(0);
		for (int i = 1; i < list.getValues().size(); i++) {
			if (list.getValues().get(i).compareTo(max) > 0) {
				max = list.getValues().get(i);
			}
		}
		value = max;
	}

	public void calculateLowerQuartile() {

	}

	public void calculateMedian() {

	}

	public void calculateUpperQuartile() {

	}
}
