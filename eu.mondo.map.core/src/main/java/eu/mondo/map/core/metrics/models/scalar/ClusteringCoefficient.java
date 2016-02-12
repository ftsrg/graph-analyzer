package eu.mondo.map.core.metrics.models.scalar;

import java.util.List;

import eu.mondo.map.core.metrics.ScalarMetric;

public class ClusteringCoefficient extends ScalarMetric<Double> {

//	protected double metricValue;
	private List<Double> clusteringCoefficients;

//	@Override
	public void calculate() {
		List<Double> values;

		if (clusteringCoefficients == null) {
			value = 0.0;
			return;
		}
		double sumCoef = 0.0;
		for (Double coef : clusteringCoefficients) {
			sumCoef += coef;
		}
		value = sumCoef / clusteringCoefficients.size();

	}

//	@Override
//	protected String getIdentifier() {
//		if (type == null) {
//			return "AvgClustering";
//		} else {
//			return "AvgClustering" + type;
//		}
//	}

//	@Override
//	public void clear() {
//		super.clear();
//		clusteringCoefficients.clear();
//	}

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

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}