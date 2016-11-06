package hu.bme.mit.mba.modelmetrics.tests;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import hu.bme.mit.mba.modelmetrics.ModelMetric;
import hu.bme.mit.mba.modelmetrics.impl.ModelMetricsEnum;
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

public class ModelMetricsEnumTest {

    @Test
    public void testMetricsEnum() throws InstantiationException, IllegalAccessException {
        for (ModelMetricsEnum metric : ModelMetricsEnum.values()) {
            ModelMetric metricObj = metric.instantiate();
            AssertJUnit.assertNotNull("Cannot instantiate: " + metric.toString(), metricObj);
            Assert.assertTrue(metric.getMetric().isInstance(metricObj));

            Assert.assertTrue(ModelMetricsEnum.getEnum(metricObj) == metric);

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

    private void isSameInstance(ModelMetricsEnum metricEnum, ModelMetric metricObj) {
        assertTrue(metricEnum.getMetric().isInstance(metricObj));
    }

}
