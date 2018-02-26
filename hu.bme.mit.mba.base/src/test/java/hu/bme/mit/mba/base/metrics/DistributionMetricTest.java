package hu.bme.mit.mba.base.metrics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;

@RunWith(DataProviderRunner.class)
public class DistributionMetricTest {

    private DistributionMetric sut;
    private Random random;

    private List<Double> transform(double[] sample) {
        return sut.transformToCdf(Arrays.stream(sample).boxed().collect(Collectors.toList()));
    }

    private Map<Double, Number> transformWithMapping(double[] sample) {
        return sut.transformToCdfWithMapping(Arrays.stream(sample).boxed().collect(Collectors.toList()));
    }

    private void check(double[] expected, List<Double> sample) {
        Assert.assertEquals(expected.length, sample.size());
        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals("" + sample, expected[i], sample.get(i), 0.001);
        }
    }

    private void check(Double key, Number value, Map<Double, Number> sample) {
        Assert.assertEquals(value, sample.get(key));

    }

    @Before
    public void setUp() {
        sut = new DistributionMetric();
        random = new Random();
    }

    @Test
    @DataProvider({ "1.0, 2.0", "1, 2" })
    public void testTransformToCdf_TwoVariables(double... sample) {
        List<Double> cdf = transform(sample);
        check(new double[] { 0.5, 1.0 }, cdf);

        Map<Double, Number> mapping = transformWithMapping(sample);
        check(0.5, 1.0, mapping);
        check(1.0, 2.0, mapping);
    }

    @Test
    @DataProvider({ "10", "50", "100", "1000" })
    public void testTransformToCdf_sortedResult(int size) {
        double[] sample = new double[size];
        for (int i = 0; i < size; i++) {
            sample[i] = random.nextDouble();
        }
        List<Double> cdf = transform(sample);
        List<Double> sortedLCdf = new ArrayList<>(cdf);
        Collections.sort(sortedLCdf);

        Assert.assertArrayEquals(sortedLCdf.toArray(), cdf.toArray());
    }

    @Test
    public void testTransformToCdf_notFiltered() {
        double[] sample = { 1.0, 1.0, 2.0 };
        List<Double> cdf = transform(sample);
        check(new double[] { 0.666, 1.0 }, cdf);
    }

}
