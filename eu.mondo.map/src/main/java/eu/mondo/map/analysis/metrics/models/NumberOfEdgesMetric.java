package eu.mondo.map.analysis.metrics.models;

import eu.mondo.map.constants.EdgeDirection;

public class NumberOfEdgesMetric extends ModelMetric {

	public NumberOfEdgesMetric() {
		super(EdgeDirection.BOTH);
	}

//	@Override
//	public void calculate() {
//		metricValue = analyzer.getNumberOfEdges();
//	}

	@Override
	protected String getIdentifier() {
		return "NumOfEdges";
	}

	public void setNumberOfEdges(final double numberOfEdges) {
		metricValue = numberOfEdges;
	}

	public void increaseEdges() {
		metricValue++;
	}

	public void increase(final double value) {
		metricValue += value;
	}
}
