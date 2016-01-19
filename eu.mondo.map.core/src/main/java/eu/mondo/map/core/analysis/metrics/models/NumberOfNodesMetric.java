package eu.mondo.map.core.analysis.metrics.models;

import eu.mondo.map.core.constants.EdgeDirection;

public class NumberOfNodesMetric extends ModelMetric {

	public NumberOfNodesMetric() {
		super(EdgeDirection.BOTH);
	}

//	@Override
//	public void calculate() {
//		metricValue = analyzer.getNumberOfNodes();
//	}

	@Override
	protected String getIdentifier() {
		return "NumOfNodes";
	}

	public void setNumberOfNodes(final double numberOfNodes) {
		metricValue = numberOfNodes;
	}

	public void increaseNodes() {
		metricValue++;
	}
}
