package hu.bme.mit.mba.base.metrics;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KolmogorovSmirnovTestMetric {

    public Map<Number, Double> calculate(List<? extends Number> referenceSample,
            List<? extends Number> empiricalSample) {
        DistributionMetric distribution = new DistributionMetric();
        Map<Double, Number> referenceCdfMapping = distribution.transformToCdfWithMapping(referenceSample);
        Map<Double, Number> empiricalCdfMapping = distribution.transformToCdfWithMapping(empiricalSample);

        List<Double> referenceCdf = getCdf(referenceCdfMapping);
        List<Double> empiricalCdf = getCdf(empiricalCdfMapping);
        Collections.sort(referenceCdf);
        Collections.sort(empiricalCdf);

        final int referenceSize = referenceCdf.size();
        final int empiricalSize = empiricalCdf.size();

        int referenceIndex = 0;
        int empiricalIndex = 0;
        int lastReferenceIndex = 0;
        int lastEmpiricalIndex = 0;

        long curD = 0l;
        long supD = 0l;

        Number value = 0.0;
        double p = 0.0;
        do {
            double lower = Double.compare(referenceCdf.get(referenceIndex), empiricalCdf.get(empiricalIndex)) <= 0
                    ? referenceCdf.get(referenceIndex) : empiricalCdf.get(empiricalIndex);
            while (referenceIndex < referenceSize && Double.compare(referenceCdf.get(referenceIndex), lower) == 0) {
                referenceIndex += 1;
                curD += empiricalSize;
                lastReferenceIndex = referenceIndex;
            }
            while (empiricalIndex < empiricalSize && Double.compare(empiricalCdf.get(empiricalIndex), lower) == 0) {
                empiricalIndex += 1;
                curD -= referenceSize;
                lastEmpiricalIndex = empiricalIndex;
            }
            if (curD > supD) {
                supD = curD;
                p = referenceCdf.get(lastReferenceIndex);
                value = referenceCdfMapping.get(p);
            } else if (-curD > supD) {
                supD = -curD;
                p = empiricalCdf.get(lastEmpiricalIndex);
                value = empiricalCdfMapping.get(p);
            }
        } while (referenceIndex < referenceSize && empiricalIndex < empiricalSize);

        Map<Number, Double> dMapping = new HashMap<>();
        dMapping.put(value, supD / ((double) (referenceSize * (long) empiricalSize)));

        return dMapping;
    }

    private List<Double> getCdf(Map<Double, Number> referenceCdfMapping) {
        return referenceCdfMapping.keySet().stream().map(Number::doubleValue).collect(Collectors.toList());
    }

}
