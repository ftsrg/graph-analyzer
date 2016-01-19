package eu.mondo.map.core.metrics.models;
//package eu.mondo.map.analysis.metrics.models;
//
//import eu.mondo.map.constants.EdgeDirection;
//
//public class HigherDegreeDistributionMetric extends ModelMetric {
//
//	public HigherDegreeDistributionMetric(EdgeDirection direction) {
//		super(direction);
//
//	}
//
//	@Override
//	public void calculate() {
//		metricValue = analyzer.getNumberOfHigherDegree(direction)
//				/ analyzer.getNumberOfNodes(withOutgoingDegree);
//	}
//
//	@Override
//	protected String getIdentifier() {
//		if (direction == EdgeDirection.BOTH) {
//			return "HigherDegree";
//		} else {
//			return "HigherOutgoingDegree";
//		}
//	}
//}
