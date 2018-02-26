package hu.bme.mit.mba.base.metrics;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.ParetoDistribution;
import org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;

@RunWith(DataProviderRunner.class)
public class KolmogorovSmirnovTestMetricTest {

    private KolmogorovSmirnovTestMetric sut;
    private List<Double> reference;
    private List<Double> empirical;
    private Map<Number, Double> result;
    private DistributionMetric distribution;
    private Random random;

    @Before
    public void setUp() {
        sut = new KolmogorovSmirnovTestMetric();
        distribution = new DistributionMetric();
        random = new Random();
    }

    private List<Double> getSample(double[] arraySample) {
        return Arrays.stream(arraySample).boxed().collect(Collectors.toList());
    }

    private List<Double> getRandomSample(int size) {
        double[] sample = new double[size];
        for (int i = 0; i < size; i++) {
            sample[i] = random.nextInt(10);
        }
        return Arrays.stream(sample).boxed().collect(Collectors.toList());
    }

    private void calculate() {
        result = sut.calculate(reference, empirical);
    }

    private void check(Number variable, Double difference) {
        Assert.assertEquals(difference, result.get(variable));
    }

    private void check(Number variable) {
        Assert.assertTrue(result.containsKey(variable));
    }

    private void checkWithMath3() {
        KolmogorovSmirnovTest ks = new KolmogorovSmirnovTest();
        double d = ks.kolmogorovSmirnovStatistic(
                distribution.transformToCdf(reference).stream().mapToDouble(Double::doubleValue).toArray(),
                distribution.transformToCdf(empirical).stream().mapToDouble(Double::doubleValue).toArray());
        Assert.assertTrue("expected: " + d + " actual: " + result, result.containsValue(d));
    }

    @Test
    @DataProvider({ "1.0, 2.0, 3.0", "1, 2, 3, 4, 5, 6, 7, 8, 9, 10" })
    public void testCalculate_ZeroDifference(double... sample) {
        reference = getSample(sample);
        empirical = getSample(sample);

        calculate();
        check(0.0, 0.0);
        checkWithMath3();

        empirical = reference.stream().map(x -> x * 2).collect(Collectors.toList());
        calculate();
        check(0.0, 0.0);
        checkWithMath3();
    }

    @Test
    @DataProvider({ "1, 2, 3, 4, 5, 6, 7, 8, 9, 10" })
    public void testCalculate_hasDifference(double... sample) {
        reference = getSample(sample);
        empirical = getSample(sample);
        empirical.set(0, 3.0);

        calculate();
        check(3.0);
        checkWithMath3();
    }

    @Test
    @DataProvider({ "10", "100", "1000" })
    public void testCalculate_NormalAndPareto(int size) {
        NormalDistribution normal = new NormalDistribution();
        ParetoDistribution pareto = new ParetoDistribution();
        reference = getSample(normal.sample(size));
        empirical = getSample(pareto.sample(size * 2));

        calculate();
        checkWithMath3();

        reference = getSample(normal.sample(size * 3));
        empirical = getSample(pareto.sample(size));
        calculate();
        checkWithMath3();
    }

    @Test
    @DataProvider({ "10", "50", "100", "1000" })
    public void testCalculate_WithRandomSamples(int size) {
        reference = getRandomSample(size);
        empirical = getRandomSample(size);
        calculate();
        checkWithMath3();
    }

}
