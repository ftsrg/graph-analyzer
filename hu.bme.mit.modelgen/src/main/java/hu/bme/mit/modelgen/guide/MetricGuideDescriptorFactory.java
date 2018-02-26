package hu.bme.mit.modelgen.guide;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import hu.bme.mit.mba.modelmetrics.impl.simple.Degrees;
import hu.bme.mit.modelgen.model.MetricEquality;

public class MetricGuideDescriptorFactory {

    private static final Logger logger = Logger.getLogger(MetricGuideDescriptorFactory.class);

    private PriorityFactory priorityProvider;

    public MetricGuideDescriptorFactory() {
        this.priorityProvider = new PriorityFactory();
    }

    public <N, T> List<MetricGuideDescriptor<N, T>> getGuideDescriptors(Iterable<MetricEquality> equalities) {
        List<MetricGuideDescriptor<N, T>> guideDescriptors = new ArrayList<>();
        equalities.forEach(equality -> guideDescriptors.add(resolveMetric(equality)));

        return guideDescriptors;
    }

    private <N, T> MetricGuideDescriptor<N, T> resolveMetric(MetricEquality metricEquality) {
        double equality = metricEquality.getEquality();
        Priorities priority = priorityProvider.resolvePriority(equality);
        logger.info("Resolved priority of metric " + metricEquality.getMetric() + ": " + priority);

        MetricGuide<N, T> metricGuide = null;
        // TODO separate MetricGuideFactory?
        if (metricEquality.getMetric() instanceof Degrees) {
            metricGuide = new DegreesMetricGuide<N, T>((Degrees) metricEquality.getMetric());
        }
        MetricGuideDescriptor<N, T> guideDescriptor = new MetricGuideDescriptor<N, T>(metricGuide, priority);
        logger.debug("Created GuideDescriptor: " + guideDescriptor);

        return guideDescriptor;
    }

}
