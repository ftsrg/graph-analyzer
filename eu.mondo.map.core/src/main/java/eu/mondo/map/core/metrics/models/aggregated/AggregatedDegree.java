package eu.mondo.map.core.metrics.models.aggregated;
//package eu.mondo.map.analysis.metrics.models;
//
//import static eu.mondo.map.constants.EdgeDirection.BOTH;
//import eu.mondo.map.constants.EdgeDirection;
//
//public class MaximumDegreeMetric extends ModelMetric {
//
//	public MaximumDegreeMetric(EdgeDirection direction) {
//		super(direction);
//	}
//
//	@Override
//	public void calculate() {
//		metricValue = analyzer.getMaximumDegree(direction);
//	}
//
//	@Override
//	protected String getIdentifier() {
//		if (direction == BOTH) {
//			return "MaxDegree";
//		} else {
//			return "MaxOutgoingDegree";
//		}
//	}
//
//	public void changeMaximumDegree(final EdgeDirection direction, final double degree) {
//		metricValue = degree > metricValue ? degree : metricValue;
//	}
//}
