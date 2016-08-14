package hu.bme.mit.mba.modelmetrics.impl;

import hu.bme.mit.mba.modelmetrics.ModelMetric;
import hu.bme.mit.mba.modelmetrics.ModelMetricInitializer;
import hu.bme.mit.mba.modelmetrics.impl.simple.ClusteringCoefficient;
import hu.bme.mit.mba.modelmetrics.impl.simple.Degrees;
import hu.bme.mit.mba.modelmetrics.impl.simple.Density;
import hu.bme.mit.mba.modelmetrics.impl.simple.NumberOfEdges;
import hu.bme.mit.mba.modelmetrics.impl.simple.NumberOfNodes;
import hu.bme.mit.mba.modelmetrics.impl.typed.DimensionActivity;
import hu.bme.mit.mba.modelmetrics.impl.typed.MultiplexParticipationCoefficient;
import hu.bme.mit.mba.modelmetrics.impl.typed.NodeActivity;
import hu.bme.mit.mba.modelmetrics.impl.typed.OneTypedClusteringCoefficient;
import hu.bme.mit.mba.modelmetrics.impl.typed.PairwiseMultiplexity;

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

    },

    PairwiseMultiplexity {

        @Override
        public <N, T> ModelMetric instantiate() {
            return new PairwiseMultiplexity();
        }

    }

}
