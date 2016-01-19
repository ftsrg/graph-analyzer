package eu.mondo.map.core.analysis.metrics.models;

import static eu.mondo.map.core.constants.EdgeDirection.BOTH;
import eu.mondo.map.core.constants.EdgeDirection;

public class DegreeDistributionMetric extends ModelMetric {

	public DegreeDistributionMetric(EdgeDirection direction) {
		super(direction);
	}

//	@Override
//	public void calculate() {
//		metricValue = analyzer.getNumberOfAverageDegree(direction)
//				/ analyzer.getNumberOfNodes(withOutgoingDegree);
//	}

	@Override
	protected String getIdentifier() {
		if (direction == BOTH) {
			return "AvgDegreeDist";
		} else {
			return "AvgOutgoingDegreeDist";
		}
	}
}
