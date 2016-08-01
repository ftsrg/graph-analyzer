package eu.mondo.map.modelanalyzer.tests;

import static eu.mondo.map.modelmetrics.impl.ModelMetrics.NumberOfEdges;
import static eu.mondo.map.modelmetrics.impl.ModelMetrics.NumberOfNodes;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import eu.mondo.map.base.metrics.Metric;
import eu.mondo.map.modelanalyzer.ModelAnalyzer;
import eu.mondo.map.modelmetrics.impl.ModelMetrics;

public class ModelAnalyzerTests {

    protected ModelAnalyzer analyzer;

    public ModelAnalyzerTests() {
	PropertyConfigurator.configure("src/log4j.properties");
	Logger.getRootLogger().setLevel(Level.OFF);
    }

    @BeforeMethod
    public void init() {
	analyzer = new ModelAnalyzer();
    }

    protected void checkSize(int expected) {
	AssertJUnit.assertEquals(expected, analyzer.getMetrics().keySet().size());
	AssertJUnit.assertEquals(expected, analyzer.getMetricsInOrder().size());
    }

    protected void checkMetric(ModelMetrics metric) {
	Metric metricObject = analyzer.getMetric(metric);
	AssertJUnit.assertNotNull(metricObject);
	AssertJUnit.assertTrue(metric.instantiate().getClass().isInstance(metricObject));
    }

    @Test
    public void testSummaryMetrics() {
	// analyzer.useSummary(new SummaryMetric<Integer, DegreeList);
	AssertJUnit.fail("Not tested yet");
    }

    @Test
    public void testAggregatedMetrics() {
	// analyzer.useSummary(new SummaryMetric<Integer, DegreeList<?>>());
	AssertJUnit.fail("Not tested yet");
    }

    @Test
    public void testUse() {
	analyzer.use(NumberOfEdges);
	checkSize(1);
    }

    @Test
    public void testUseWithName() {
	String name = "CustomName";
	analyzer.use(NumberOfEdges, name);
	AssertJUnit.assertEquals(name, analyzer.getMetric(ModelMetrics.NumberOfEdges).getName());
    }

    @Test
    public void testMultipleDifferentUse() {
	analyzer.use(NumberOfEdges);
	analyzer.use(NumberOfNodes);
	checkSize(2);
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void testMultipleSameUse() {
	analyzer.use(NumberOfEdges);
	analyzer.use(NumberOfEdges);
    }

    @Test
    public void testGetMetric() {
	analyzer.use(NumberOfEdges);
	analyzer.use(NumberOfNodes);
	checkMetric(NumberOfEdges);
	checkMetric(NumberOfNodes);
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void testIncorrectUseAll() {
	analyzer.use(NumberOfEdges);
	analyzer.useAll();
    }

    @Test
    public void testUseAll() {
	analyzer.useAll();
	checkSize(ModelMetrics.values().length);

	checkMetric(NumberOfNodes);
	checkMetric(NumberOfEdges);
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void testIncorrectOmit() {
	analyzer.omit(NumberOfNodes);
    }

    @Test
    public void testOmittingAbsenceMetric() {
	analyzer.use(NumberOfEdges).omit(NumberOfNodes);
	checkSize(1);
	checkMetric(NumberOfEdges);
    }

    @Test
    public void testOmittingExistingMetric() {
	analyzer.use(NumberOfNodes).omit(NumberOfNodes);
	checkSize(0);
    }

    @Test
    public void testOmitWithAll() {
	analyzer.useAll().omit(NumberOfNodes);
	checkSize(ModelMetrics.values().length - 1);
	AssertJUnit.assertNull(analyzer.getMetric(NumberOfNodes));

	analyzer.clear().useAll().omit(NumberOfEdges);

	checkSize(ModelMetrics.values().length - 1);
	AssertJUnit.assertNull(analyzer.getMetric(NumberOfEdges));

	analyzer.clear().useAll().omit(NumberOfNodes).omit(NumberOfEdges);
	checkSize(ModelMetrics.values().length - 2);
    }

    @Test
    public void testOrder() {
	AssertJUnit.fail("Not tested yet");
    }

}
