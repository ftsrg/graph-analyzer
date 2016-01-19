package eu.mondo.map.core.metrics.models;

import static eu.mondo.map.core.constants.EdgeDirection.BOTH;
import eu.mondo.map.core.constants.EdgeDirection;

public class DensityMetric extends ModelMetric {

	protected double nodes;
	protected double edges;

	public DensityMetric(EdgeDirection direction) {
		super(direction);
	}

	@Override
	public void calculate() {
//		double numOfNodes = analyzer.getNumberOfNodes(withOutgoingDegree);
		metricValue = edges / nodes;
		metricValue /= (nodes - 1);
	}

	@Override
	protected String getIdentifier() {
		if (direction == BOTH) {
			return "Density";
		} else {
			return "DensityWithOutgoing";
		}
	}

	@Override
	public void clear() {
		super.clear();
		nodes = 0;
		edges = 0;
	}

	public double getNodes() {
		return nodes;
	}

	public void setNodes(double nodes) {
		this.nodes = nodes;
	}

	public double getEdges() {
		return edges;
	}

	public void setEdges(double edges) {
		this.edges = edges;
	}

}
