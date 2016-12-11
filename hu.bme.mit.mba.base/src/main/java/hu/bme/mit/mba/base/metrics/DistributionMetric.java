package hu.bme.mit.mba.base.metrics;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.math3.stat.Frequency;

public class DistributionMetric {

    public List<Double> transformToCdf(List<? extends Number> sample) {
        List<Double> cdf = transformToCdfWithMapping(sample).keySet().stream().map(Number::doubleValue)
                .collect(Collectors.toList());
        Collections.sort(cdf);
        return cdf;
    }

    public Map<Double, Number> transformToCdfWithMapping(List<? extends Number> sample) {
        Map<Double, Number> mapping = new HashMap<>();
        List<Double> doubleList = sample.stream().map(Number::doubleValue).collect(Collectors.toList());

        Frequency frequency = new Frequency();
        for (Double x : doubleList) {
            frequency.addValue(x);
        }

        for (Double x : doubleList) {
            mapping.put(frequency.getCumPct(x), x);
        }

        return mapping;
    }

}
