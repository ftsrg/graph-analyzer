package eu.mondo.map.analysis.metrics.models;

import static eu.mondo.map.constants.EdgeDirection.BOTH;
import eu.mondo.map.constants.EdgeDirection;

public class AverageDegreeDistributionMetric extends ModelMetric {

	public AverageDegreeDistributionMetric(EdgeDirection direction) {
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
