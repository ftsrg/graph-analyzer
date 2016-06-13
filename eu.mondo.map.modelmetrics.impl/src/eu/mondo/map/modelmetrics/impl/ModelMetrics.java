package eu.mondo.map.modelmetrics.impl;

import eu.mondo.map.modelmetrics.ModelEvaluatorInitializer;
import eu.mondo.map.modelmetrics.impl.simple.NumberOfEdges;
import eu.mondo.map.modelmetrics.impl.simple.NumberOfNodes;

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
