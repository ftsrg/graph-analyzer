package eu.mondo.map.modelmetrics.impl;

import eu.mondo.map.modelmetrics.ModelMetricInitializer;
import eu.mondo.map.modelmetrics.impl.simple.ClusteringCoefficient;
import eu.mondo.map.modelmetrics.impl.simple.NumberOfEdges;
import eu.mondo.map.modelmetrics.impl.simple.NumberOfNodes;

public enum ModelMetrics implements ModelMetricInitializer {

	NumberOfEdges {

		@Override
		public NumberOfEdges instantiate() {
			return new NumberOfEdges();
		}

	},
	NumberOfNodes {

		@Override
		public NumberOfNodes instantiate() {
			return new NumberOfNodes();
		}

	},

	ClusteringCoefficient {

		@Override
		public ClusteringCoefficient instantiate() {
			return new ClusteringCoefficient();
		}

	}

}
