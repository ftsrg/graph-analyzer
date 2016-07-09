package eu.mondo.map.modelmetrics.impl.typed;

import java.util.HashMap;
import java.util.Map;

import eu.mondo.map.base.data.MapData;
import eu.mondo.map.base.graph.Network;
import eu.mondo.map.base.graph.Node;

public class PairwiseMultiplexity extends MapData<String, Double> {

	protected Map<DimensionPair, Integer> dimensionPairs;

	public PairwiseMultiplexity() {
		super("PairwiseMultiplexity");
		dimensionPairs = new HashMap<DimensionPair, Integer>();
	}

	public void calculate(final Network<?> network, final String firstDimension,
			final String secondDimension) {
		calculate(network, firstDimension, secondDimension, false);
	}

	public void calculateExclusive(final Network<?> network, final String firstDimension,
			final String secondDimension) {
		calculate(network, firstDimension, secondDimension, true);
	}

	public void calculateAllPairs(final Network<?> network) {
		findDimensionPairs(network);
		calculateAllPairs(false, network);
	}

	public void calculateAllPairs(final Network<?> network, boolean lazy) {
		if (!lazy || dimensionPairs.isEmpty()) {
			findDimensionPairs(network);
		}
		calculateAllPairs(false, network);
	}

	public void calculateAllPairsExclusive(final Network<?> network) {
		findDimensionPairs(network);
		calculateAllPairs(true, network);
	}

	public void calculateAllPairsExclusive(final Network<?> network, boolean lazy) {
		if (!lazy || dimensionPairs.isEmpty()) {
			findDimensionPairs(network);
		}
		calculateAllPairs(true, network);
	}

	protected void calculateAllPairs(final boolean exclusive, final Network<?> network) {
		for (DimensionPair pair : dimensionPairs.keySet()) {
			calculate(network, pair.dim1, pair.dim2, exclusive);
		}
	}

	protected void findDimensionPairs(final Network<?> network) {
		dimensionPairs.clear();
		for (Node<?> node : network.getNodes()) {
			for (String dim1 : node.getDimensionsAsSet()) {
				for (String dim2 : node.getDimensionsAsSet()) {
					if (!dim1.equals(dim2)) {
						DimensionPair pair = new DimensionPair(dim1, dim2);
						if (!dimensionPairs.containsKey(pair)) {
							dimensionPairs.put(pair, 1);
						}
					}
				}
			}
		}
	}

	protected double calculate(final Network<?> network, final String firstDimension,
			final String secondDimension, final boolean exclusive) {
		int firstSizeofNodes = network.getNumberOfNodes(firstDimension);
		int secondSizeofNodes = network.getNumberOfNodes(secondDimension);
		int nodesInIntersection = 0;

		if (firstSizeofNodes < secondSizeofNodes) {
			for (Node<?> node : network.getNodes(firstDimension)) {
				if (node.hasDimension(secondDimension)) {
					nodesInIntersection++;
				}
			}
		} else {
			for (Node<?> node : network.getNodes(secondDimension)) {
				if (node.hasDimension(firstDimension)) {
					nodesInIntersection++;
				}
			}
		}
		double value = 0.0;
		if (exclusive) {
			int union = firstSizeofNodes + secondSizeofNodes;
			union -= nodesInIntersection;
			if (union == 0) {
				value = 0.0;
			} else {
				value = nodesInIntersection / (double) union;
			}
		} else {
			value = nodesInIntersection / (double) network.getNumberOfNodes();
		}
		putValue(value, firstDimension, secondDimension, exclusive);
		return value;
	}

	protected void putValue(final double value, final String dim1, final String dim2,
			final boolean exclusive) {
		if (exclusive) {
			typedValues.put(String.format("%s-%s-exclusive", dim1, dim2), value);
		} else {
			typedValues.put(String.format("%s-%s", dim1, dim2), value);
		}

	}

	protected static class DimensionPair {

		public String dim1;
		public String dim2;

		public DimensionPair(String dim1, String dim2) {
			this.dim1 = dim1;
			this.dim2 = dim2;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((dim1 == null) ? 0 : dim1.hashCode());
			result = prime * result + ((dim2 == null) ? 0 : dim2.hashCode());
			return result;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof DimensionPair)) {
				return false;
			}
			DimensionPair other = (DimensionPair) obj;
			if (dim1 == null) {
				if (other.dim1 != null) {
					return false;
				}
			} else if (!dim1.equals(other.dim1)) {
				return false;
			}
			if (dim2 == null) {
				if (other.dim2 != null) {
					return false;
				}
			} else if (!dim2.equals(other.dim2)) {
				return false;
			}
			return true;
		}

	}
}
