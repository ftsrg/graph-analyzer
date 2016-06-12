package eu.mondo.map.modelmetrics;

import eu.mondo.map.modelmetrics.scalar.NumberOfEdges;
import eu.mondo.map.modelmetrics.scalar.NumberOfNodes;

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
