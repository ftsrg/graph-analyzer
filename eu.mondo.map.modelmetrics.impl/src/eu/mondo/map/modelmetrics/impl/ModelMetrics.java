package eu.mondo.map.modelmetrics.impl;

import eu.mondo.map.modelmetrics.MetricInitializer;
import eu.mondo.map.modelmetrics.impl.scalar.NumberOfEdges;
import eu.mondo.map.modelmetrics.impl.scalar.NumberOfNodes;

public enum ModelMetrics implements MetricInitializer {

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

	}

}
