package eu.mondo.map.core.metrics.models.aggregated;

import java.util.HashMap;
import java.util.Map;

import eu.mondo.map.core.metrics.AggregatedMetric;

public class Centrality extends AggregatedMetric<Double> {

	protected Map<String, Integer> betweennessValues;
	protected double pairs;

//	protected double metricValue;

	public Centrality() {
		betweennessValues = new HashMap<String, Integer>();
	}

//	@Override
	public void calculate() {
//		double pairs = analyzer.getShortestPathMetric().getPairs();
//		metricValue = 0;
//		for (Entry<String, Integer> entry : betweennessValues.entrySet()) {
//			if (entry.getValue() > metricValue) {
//				metricValue = entry.getValue();
//			}
//		}
//		metricValue /= pairs;

	}

//	@Override
//	protected String getIdentifier() {
//		return "Betweenness";
//	}

	public void addBetweenness(final String nodeID) {
		if (!betweennessValues.containsKey(nodeID)) {
			betweennessValues.put(nodeID, 1);
		} else {
			int value = betweennessValues.get(nodeID);
			value++;
			betweennessValues.put(nodeID, value);
		}
	}

	public double getPairs() {
		return pairs;
	}

	public void setPairs(double pairs) {
		this.pairs = pairs;
	}

	public Map<String, Integer> getBetweennessValues() {
		return betweennessValues;
	}

	public void setBetweennessValues(Map<String, Integer> betweennessValues) {
		this.betweennessValues = betweennessValues;
	}

	public void increasePairs() {
		pairs++;
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
