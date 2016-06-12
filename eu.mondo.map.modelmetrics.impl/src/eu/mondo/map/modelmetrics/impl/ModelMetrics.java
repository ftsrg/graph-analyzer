package eu.mondo.map.modelmetrics.impl;

import eu.mondo.map.modelmetrics.ModelEvaluatorInitializer;
import eu.mondo.map.modelmetrics.impl.scalar.NumberOfEdges;
import eu.mondo.map.modelmetrics.impl.scalar.NumberOfNodes;

public enum ModelMetrics implements ModelEvaluatorInitializer {

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
