package eu.mondo.map.base.metrics;

public class SummaryMetric<V extends Number, M extends ListMetric<V>> extends ScalarMetric<V> {

	public SummaryMetric() {
		super("SummaryMetric");
	}

	public void calculateLowerQuartile() {

	}

	// public void calculateMaximum(final M list) {
	// V max = list.get(0);
	// for (int i = 1; i < list.size(); i++) {
	// if (list.get(i).compareTo(max) > 0) {
	// max = list.get(i);
	// }
	// }
	// value = max;
	// }

	public void calculateMedian() {

	}

	// public void calculateMinimum(final M list) {
	// V min = list.get(0);
	// for (int i = 1; i < list.size(); i++) {
	// if (list.get(i).compareTo(min) < 0) {
	// min = list.get(i);
	// }
	// }
	// value = min;
	// }

	public void calculateUpperQuartile() {

	}

	@Override
	public void clear() {

	}

}
