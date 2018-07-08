package hu.bme.mit.ga.metrics.impl;

import hu.bme.mit.ga.metrics.GraphMetricInitializer;
import hu.bme.mit.ga.metrics.impl.simple.ClusteringCoefficient;
import hu.bme.mit.ga.metrics.impl.simple.Degrees;
import hu.bme.mit.ga.metrics.impl.simple.Density;
import hu.bme.mit.ga.metrics.impl.simple.NumberOfEdges;
import hu.bme.mit.ga.metrics.impl.simple.NumberOfNodes;
import hu.bme.mit.ga.metrics.impl.typed.TypedActivity;
import hu.bme.mit.ga.metrics.impl.typed.TypedDegreeEntropy;
import hu.bme.mit.ga.metrics.impl.typed.EdgeOverlap;
import hu.bme.mit.ga.metrics.impl.typed.MultiplexParticipationCoefficient;
import hu.bme.mit.ga.metrics.impl.typed.NodeActivity;
import hu.bme.mit.ga.metrics.impl.typed.OneTypedClusteringCoefficient;
import hu.bme.mit.ga.metrics.impl.typed.PairwiseMultiplexity;
import hu.bme.mit.ga.metrics.GraphMetric;
import hu.bme.mit.ga.metrics.impl.typed.TypedClusteringCoefficient;

public enum GraphMetricsEnum implements GraphMetricInitializer {

    ClusteringCoefficient {
        @Override
        public Class<hu.bme.mit.ga.metrics.impl.simple.ClusteringCoefficient> getMetric() {
            return ClusteringCoefficient.class;
        }

    },

    Degrees {
        @Override
        public Class<hu.bme.mit.ga.metrics.impl.simple.Degrees> getMetric() {
            return Degrees.class;
        }

    },

    Density {
        @Override
        public Class<hu.bme.mit.ga.metrics.impl.simple.Density> getMetric() {
            return Density.class;
        }

    },

    DimensionActivity {
        @Override
        public Class<TypedActivity> getMetric() {
            return TypedActivity.class;
        }

    },
    DimensionalDegreeEntropy {
        @Override
        public Class<TypedDegreeEntropy> getMetric() {
            return TypedDegreeEntropy.class;
        }
    },
    EdgeOverlap {
        @Override
        public Class<hu.bme.mit.ga.metrics.impl.typed.EdgeOverlap> getMetric() {
            return EdgeOverlap.class;
        }
    },

    MultiplexParticipationCoefficient {
        @Override
        public Class<hu.bme.mit.ga.metrics.impl.typed.MultiplexParticipationCoefficient> getMetric() {
            return MultiplexParticipationCoefficient.class;
        }

    },

    NodeActivity {
        @Override
        public Class<hu.bme.mit.ga.metrics.impl.typed.NodeActivity> getMetric() {
            return NodeActivity.class;
        }

    },

    NumberOfEdges {
        @Override
        public Class<hu.bme.mit.ga.metrics.impl.simple.NumberOfEdges> getMetric() {
            return NumberOfEdges.class;
        }

    },
    NumberOfNodes {
        @Override
        public Class<hu.bme.mit.ga.metrics.impl.simple.NumberOfNodes> getMetric() {
            return NumberOfNodes.class;
        }

    },

    OneTypedClusteringCoefficient {
        @Override
        public Class<hu.bme.mit.ga.metrics.impl.typed.OneTypedClusteringCoefficient> getMetric() {
            return OneTypedClusteringCoefficient.class;
        }

    },

    PairwiseMultiplexity {
        @Override
        public Class<hu.bme.mit.ga.metrics.impl.typed.PairwiseMultiplexity> getMetric() {
            return PairwiseMultiplexity.class;
        }

    },
    DimensionalClusteringCoefficient {
        @Override
        public Class<TypedClusteringCoefficient> getMetric() {
            return TypedClusteringCoefficient.class;
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
