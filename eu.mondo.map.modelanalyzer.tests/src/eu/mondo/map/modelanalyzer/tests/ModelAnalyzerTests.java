package eu.mondo.map.modelanalyzer.tests;

import org.junit.Before;
import org.junit.Test;

import eu.mondo.map.core.metrics.SummaryMetric;
import eu.mondo.map.modelanalyzer.ModelAnalyzer;
import eu.mondo.map.modelmetrics.ModelMetrics;
import eu.mondo.map.modelmetrics.composite.DegreeList;

public class ModelAnalyzerTests {

	protected ModelAnalyzer analyzer;

	@Before
	public void init() {
		analyzer = new ModelAnalyzer();
	}

	@Test
	public void testSummaryMetrics() {
		analyzer.useSummary(new SummaryMetric<Integer, DegreeList<?>>());
	}

	@Test
	public void testUse() {
		analyzer.use(ModelMetrics.NumberOfEdges);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUseAll() {
		analyzer.use(ModelMetrics.NumberOfEdges);
		analyzer.useAll();
	}
}
