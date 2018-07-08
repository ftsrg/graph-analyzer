package hu.bme.mit.mba.modelmetrics.impl;

import hu.bme.mit.mba.modelmetrics.GraphMetricInitializer;
import hu.bme.mit.mba.modelmetrics.GraphMetric;
import hu.bme.mit.mba.modelmetrics.impl.simple.ClusteringCoefficient;
import hu.bme.mit.mba.modelmetrics.impl.simple.Degrees;
import hu.bme.mit.mba.modelmetrics.impl.simple.Density;
import hu.bme.mit.mba.modelmetrics.impl.simple.NumberOfEdges;
import hu.bme.mit.mba.modelmetrics.impl.simple.NumberOfNodes;
import hu.bme.mit.mba.modelmetrics.impl.typed.DimensionActivity;
import hu.bme.mit.mba.modelmetrics.impl.typed.DimensionalClusteringCoefficient;
import hu.bme.mit.mba.modelmetrics.impl.typed.DimensionalDegreeEntropy;
import hu.bme.mit.mba.modelmetrics.impl.typed.EdgeOverlap;
import hu.bme.mit.mba.modelmetrics.impl.typed.MultiplexParticipationCoefficient;
import hu.bme.mit.mba.modelmetrics.impl.typed.NodeActivity;
import hu.bme.mit.mba.modelmetrics.impl.typed.OneTypedClusteringCoefficient;
import hu.bme.mit.mba.modelmetrics.impl.typed.PairwiseMultiplexity;

public enum GraphMetricsEnum implements GraphMetricInitializer {

    ClusteringCoefficient {
        @Override
        public Class<ClusteringCoefficient> getMetric() {
            return ClusteringCoefficient.class;
        }

    },

    Degrees {
        @Override
        public Class<Degrees> getMetric() {
            return Degrees.class;
        }

    },

    Density {
        @Override
        public Class<Density> getMetric() {
            return Density.class;
        }

    },

    DimensionActivity {
        @Override
        public Class<DimensionActivity> getMetric() {
            return DimensionActivity.class;
        }

    },
    DimensionalDegreeEntropy {
        @Override
        public Class<DimensionalDegreeEntropy> getMetric() {
            return DimensionalDegreeEntropy.class;
        }
    },
    EdgeOverlap {
        @Override
        public Class<EdgeOverlap> getMetric() {
            return EdgeOverlap.class;
        }
    },

    MultiplexParticipationCoefficient {
        @Override
        public Class<MultiplexParticipationCoefficient> getMetric() {
            return MultiplexParticipationCoefficient.class;
        }

    },

    NodeActivity {
        @Override
        public Class<NodeActivity> getMetric() {
            return NodeActivity.class;
        }

    },

    NumberOfEdges {
        @Override
        public Class<NumberOfEdges> getMetric() {
            return NumberOfEdges.class;
        }

    },
    NumberOfNodes {
        @Override
        public Class<NumberOfNodes> getMetric() {
            return NumberOfNodes.class;
        }

    },

    OneTypedClusteringCoefficient {
        @Override
        public Class<OneTypedClusteringCoefficient> getMetric() {
            return OneTypedClusteringCoefficient.class;
        }

    },

    PairwiseMultiplexity {
        @Override
        public Class<PairwiseMultiplexity> getMetric() {
            return PairwiseMultiplexity.class;
        }

    },
    DimensionalClusteringCoefficient {
        @Override
        public Class<DimensionalClusteringCoefficient> getMetric() {
            return DimensionalClusteringCoefficient.class;
        }
    };

    public static GraphMetricsEnum getEnum(GraphMetric metric) {
        for (GraphMetricsEnum m : values()) {
            if (m.getMetric().isInstance(metric)) {
                return m;
            }
        }
        throw new IllegalArgumentException(
            "Does not exist a value in GraphMetricsEnum that belongs to metric " + metric);
    }

}
