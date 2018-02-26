package hu.bme.mit.modelgen.guide;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest;
import org.apache.log4j.Logger;

import hu.bme.mit.mba.modeladapters.ModelAdapter;
import hu.bme.mit.mba.modelmetrics.impl.simple.Degrees;
import hu.bme.mit.modelgen.op.NodeOperation;
import hu.bme.mit.modelgen.op.Operation;

public class DegreesMetricGuide<N, T> extends MetricGuide<N, T> {

    private static final Logger logger = Logger.getLogger(DegreesMetricGuide.class);

    private Degrees metric;
    private Degrees goalMetric;

    public DegreesMetricGuide(Degrees goalMetric) {
        this.goalMetric = goalMetric;
        metric = new Degrees();
    }

    public Degrees getMetric() {
        return metric;
    }

    public Degrees getGoalMetric() {
        return goalMetric;
    }

    @Override
    protected Set<Operation<N, T>> pollOperations() {
        KolmogorovSmirnovTest smirnovTest = new KolmogorovSmirnovTest();

        if (metric.getData().getValues().size() > 2) {
            double[] xDoubleArray = goalMetric.getData().getValues().stream().mapToDouble(Integer::doubleValue)
                    .toArray();
            double[] yDoubleArray = metric.getData().getValues().stream().mapToDouble(Integer::doubleValue).toArray();

            double kolmogorov = smirnovTest.kolmogorovSmirnovTest(xDoubleArray, yDoubleArray);
            logger.debug("Kolmogorov-Smirnov test value: " + kolmogorov);
        } else {
            logger.debug("Kolmogorov-Smirnov test is skipped, the number of values is insufficient: "
                    + metric.getData().getValues().size());
        }

        Set<Operation<N, T>> operations = new HashSet<>();
        operations.add(new NodeOperation<N, T>(this).anyTarget().anyType());
        operations.add(new NodeOperation<N, T>(this).anyTarget().anyType());
        operations.add(new NodeOperation<N, T>(this).anyTarget().anyType());

        return operations;
    }

    @Override
    public void evaluate(ModelAdapter modelAdapter) {
        metric.evaluate(modelAdapter);
    }

}
