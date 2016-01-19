package eu.mondo.map.core.metrics.models;

import eu.mondo.map.core.constants.EdgeDirection;

public class DegreeMetric extends ModelMetric {

	public DegreeMetric(EdgeDirection direction) {
		super(direction);
	}

//	@Override
//	public void calculate() {
//		metricValue = analyzer.getAverageDegree(direction);
//	}

	@Override
	protected String getIdentifier() {
		switch (direction) {
		case OUTGOING:
			return "AvgDegree";
		case BOTH:
			return "AvgOutgoingDegree";
		default:
			throw new IllegalArgumentException("Illegal direction!");
		}
	}

	public void calculateAverageDegree(final EdgeDirection direction, final double numberOfEdges,
			final double numberOfNodes, final double numberOfNodesWithOutgoingDegrees) {
		switch (direction) {
		case BOTH:
			metricValue = numberOfEdges / numberOfNodes;
			break;
		case OUTGOING:
			metricValue = numberOfEdges / numberOfNodesWithOutgoingDegrees;
			break;
		default:
			throw new UnsupportedOperationException();
		}
	}
}
