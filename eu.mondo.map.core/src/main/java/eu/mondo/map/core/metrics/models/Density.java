package eu.mondo.map.core.metrics.models;

public class Density extends AggregatedMetric<Double> {

	protected double nodes;
	protected double edges;

//	protected double metricValue;

//	@Override
	public void calculate() {
//		double numOfNodes = analyzer.getNumberOfNodes(withOutgoingDegree);
		value = edges / nodes;
		value /= (nodes - 1);
	}

//	@Override
//	protected String getIdentifier() {
//		return "";
//	}

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
