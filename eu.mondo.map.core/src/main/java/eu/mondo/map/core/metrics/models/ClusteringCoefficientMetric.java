package eu.mondo.map.core.metrics.models;

import java.util.List;

import eu.mondo.map.core.constants.EdgeDirection;

public class ClusteringCoefficientMetric extends ModelMetric {

	private String type;
	private List<Double> clusteringCoefficients;

	public ClusteringCoefficientMetric(String type) {
		super(EdgeDirection.OUTGOING);
		this.type = type;
	}

	public ClusteringCoefficientMetric() {
		super(EdgeDirection.OUTGOING);
		type = null;
	}

	@Override
	public void calculate() {
		List<Double> values;

		if (clusteringCoefficients == null) {
			metricValue = 0;
			return;
		}
		double sumCoef = 0.0;
		for (Double coef : clusteringCoefficients) {
			sumCoef += coef;
		}
		metricValue = sumCoef / clusteringCoefficients.size();

	}

	@Override
	protected String getIdentifier() {
		if (type == null) {
			return "AvgClustering";
		} else {
			return "AvgClustering" + type;
		}
	}

	@Override
	public void clear() {
		super.clear();
		clusteringCoefficients.clear();
	}

	public void addClusteringCoefficient(final int connectedNeighbors, final int numberOfNeighbors,
			final String type) {
//		if (!clusteringCoefficients.containsKey(type)) {
//			clusteringCoefficients.put(type, new ArrayList<Double>());
//		}
		if (numberOfNeighbors == 1 || numberOfNeighbors == 0) {
			return;
		}
		double coef = connectedNeighbors / numberOfNeighbors;
		coef /= (numberOfNeighbors - 1);
		clusteringCoefficients.add(coef);

//		if (!type.equals(ALL)) {
//			addClusteringCoefficient(connectedNeighbors, numberOfNeighbors, ALL);
//		}

	}

}