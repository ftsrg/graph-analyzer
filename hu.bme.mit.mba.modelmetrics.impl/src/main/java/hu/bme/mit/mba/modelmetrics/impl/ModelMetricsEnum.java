package hu.bme.mit.mba.modelmetrics.impl;

import hu.bme.mit.mba.modelmetrics.ModelMetric;
import hu.bme.mit.mba.modelmetrics.ModelMetricInitializer;
import hu.bme.mit.mba.modelmetrics.impl.simple.ClusteringCoefficient;
import hu.bme.mit.mba.modelmetrics.impl.simple.Degrees;
import hu.bme.mit.mba.modelmetrics.impl.simple.Density;
import hu.bme.mit.mba.modelmetrics.impl.simple.NumberOfEdges;
import hu.bme.mit.mba.modelmetrics.impl.simple.NumberOfNodes;
import hu.bme.mit.mba.modelmetrics.impl.typed.*;

public enum ModelMetricsEnum implements ModelMetricInitializer {

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

    public static ModelMetricsEnum getEnum(ModelMetric metric) {
        for (ModelMetricsEnum m : values()) {
            if (m.getMetric().isInstance(metric)) {
                return m;
            }
        }
        throw new IllegalArgumentException(
            "Does not exist a value in ModelMetricsEnum that belongs to metric " + metric);
    }

}
