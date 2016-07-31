package eu.mondo.map.modelmetrics.impl;

import eu.mondo.map.modelmetrics.ModelMetric;
import eu.mondo.map.modelmetrics.ModelMetricInitializer;
import eu.mondo.map.modelmetrics.impl.simple.ClusteringCoefficient;
import eu.mondo.map.modelmetrics.impl.simple.Degrees;
import eu.mondo.map.modelmetrics.impl.simple.Density;
import eu.mondo.map.modelmetrics.impl.simple.NumberOfEdges;
import eu.mondo.map.modelmetrics.impl.simple.NumberOfNodes;
import eu.mondo.map.modelmetrics.impl.typed.DimensionActivity;
import eu.mondo.map.modelmetrics.impl.typed.MultiplexParticipationCoefficient;
import eu.mondo.map.modelmetrics.impl.typed.NodeActivity;
import eu.mondo.map.modelmetrics.impl.typed.OneTypedClusteringCoefficient;

public enum ModelMetrics implements ModelMetricInitializer {

    ClusteringCoefficient {

	@Override
	public ClusteringCoefficient instantiate() {
	    return new ClusteringCoefficient();
	}

    },

    Degrees {

	@Override
	public <N, T> Degrees instantiate() {
	    return new Degrees();
	}

    },

    Density {

	@Override
	public <N, T> Density instantiate() {
	    return new Density();
	}

    },

    DimensionActivity {

	@Override
	public <N, T> DimensionActivity instantiate() {
	    return new DimensionActivity();
	}

    },

    MultiplexParticipationCoefficient {

	@Override
	public <N, T> ModelMetric instantiate() {
	    return new MultiplexParticipationCoefficient();
	}

    },

    NodeActivity {

	@Override
	public <N, T> ModelMetric instantiate() {
	    return new NodeActivity();
	}

    },

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

    OneTypedClusteringCoefficient {

	@Override
	public <N, T> OneTypedClusteringCoefficient instantiate() {
	    return new OneTypedClusteringCoefficient();
	}

    }

}
