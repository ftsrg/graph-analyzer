package hu.bme.mit.ga.metrics.tests;

import hu.bme.mit.ga.metrics.GraphMetric;
import hu.bme.mit.ga.metrics.impl.GraphMetricsEnum;
import hu.bme.mit.ga.metrics.impl.simple.ClusteringCoefficient;
import hu.bme.mit.ga.metrics.impl.simple.Degrees;
import hu.bme.mit.ga.metrics.impl.simple.Density;
import hu.bme.mit.ga.metrics.impl.simple.NumberOfEdges;
import hu.bme.mit.ga.metrics.impl.simple.NumberOfNodes;
import hu.bme.mit.ga.metrics.impl.typed.DimensionActivity;
import hu.bme.mit.ga.metrics.impl.typed.MultiplexParticipationCoefficient;
import hu.bme.mit.ga.metrics.impl.typed.NodeActivity;
import hu.bme.mit.ga.metrics.impl.typed.OneTypedClusteringCoefficient;
import hu.bme.mit.ga.metrics.impl.typed.PairwiseMultiplexity;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class GraphMetricsEnumTest {

    @Test
    public void testMetricsEnum() throws InstantiationException, IllegalAccessException {
        for (GraphMetricsEnum metric : GraphMetricsEnum.values()) {
            GraphMetric metricObj = metric.instantiate();
            AssertJUnit.assertNotNull("Cannot instantiate: " + metric.toString(), metricObj);
            Assert.assertTrue(metric.getMetric().isInstance(metricObj));

            Assert.assertTrue(GraphMetricsEnum.getEnum(metricObj) == metric);

            switch (metric) {
                case ClusteringCoefficient:
                    isSameInstance(metric, new ClusteringCoefficient());
                    break;
                case Degrees:
                    isSameInstance(metric, new Degrees());
                    break;
                case Density:
                    isSameInstance(metric, new Density());
                    break;
                case DimensionActivity:
                    isSameInstance(metric, new DimensionActivity());
                    break;
                case MultiplexParticipationCoefficient:
                    isSameInstance(metric, new MultiplexParticipationCoefficient());
                    break;
                case NodeActivity:
                    isSameInstance(metric, new NodeActivity());
                    break;
                case NumberOfEdges:
                    isSameInstance(metric, new NumberOfEdges());
                    break;
                case NumberOfNodes:
                    isSameInstance(metric, new NumberOfNodes());
                    break;
                case OneTypedClusteringCoefficient:
                    isSameInstance(metric, new OneTypedClusteringCoefficient());
                    break;
                case PairwiseMultiplexity:
                    isSameInstance(metric, new PairwiseMultiplexity());
                    break;
                default:
                    Assert.fail("The instantiation of metric " + metric + " is not checked.");
            }
        }
    }

    private void isSameInstance(GraphMetricsEnum metricEnum, GraphMetric metricObj) {
        assertTrue(metricEnum.getMetric().isInstance(metricObj));
    }

}
