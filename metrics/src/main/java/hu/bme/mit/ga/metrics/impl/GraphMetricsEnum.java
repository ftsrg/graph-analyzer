package hu.bme.mit.ga.metrics.impl;

import hu.bme.mit.ga.metrics.GraphMetric;
import hu.bme.mit.ga.metrics.GraphMetricInitializer;
import hu.bme.mit.ga.metrics.impl.simple.*;
import hu.bme.mit.ga.metrics.impl.typed.*;


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

    TypedActivity {
        @Override
        public Class<TypedActivity> getMetric() {
            return TypedActivity.class;
        }

    },
    TypedDegreeEntropy {
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

    PairwiseMultiplexity {
        @Override
        public Class<hu.bme.mit.ga.metrics.impl.typed.PairwiseMultiplexity> getMetric() {
            return PairwiseMultiplexity.class;
        }

    },
    TypedClusteringCoefficientDef1E {
        @Override
        public Class<TypedClusteringCoefficientDef1> getMetric() {
            return TypedClusteringCoefficientDef1.class;
        }
    },
    TypedClusteringCoefficientDef2E {
        @Override
        public Class<TypedClusteringCoefficientDef2> getMetric() {
            return TypedClusteringCoefficientDef2.class;
        }
    },
    TypedClusteringCoefficientDef3E {
        @Override
        public Class<TypedClusteringCoefficientDef3> getMetric() {
            return TypedClusteringCoefficientDef3.class;
        }
    }
    ;


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
