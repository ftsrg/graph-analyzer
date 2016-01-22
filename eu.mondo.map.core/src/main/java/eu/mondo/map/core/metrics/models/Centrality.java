package eu.mondo.map.core.metrics.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import eu.mondo.map.core.constants.EdgeDirection;

public class CentralityMetric extends ModelMetric {

	protected Map<String, Integer> betweennessValues;
	protected double pairs;

	public CentralityMetric() {
		super(EdgeDirection.OUTGOING);
		betweennessValues = new HashMap<String, Integer>();
	}

	@Override
	public void calculate() {
//		double pairs = analyzer.getShortestPathMetric().getPairs();
		metricValue = 0;
		for (Entry<String, Integer> entry : betweennessValues.entrySet()) {
			if (entry.getValue() > metricValue) {
				metricValue = entry.getValue();
			}
		}
		metricValue /= pairs;

	}

	@Override
	protected String getIdentifier() {
		return "Betweenness";
	}

	@Override
	public void clear() {
		super.clear();
		betweennessValues.clear();
		pairs = 0;
	}

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

}
