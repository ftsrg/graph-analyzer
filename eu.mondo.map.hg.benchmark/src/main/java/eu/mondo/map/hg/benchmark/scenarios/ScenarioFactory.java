package eu.mondo.map.hg.benchmark.scenarios;

import eu.mondo.map.hg.constants.ScenarioConstants;

public class ScenarioFactory {

	public static Scenario<?> getScenario(final ScenarioConstants scenarioName) {
		switch (scenarioName) {
		case ANALYZE:
			return new AnalyzeScenario();
		case BATCH:
			return new BatchScenario();
		case DESCRIBE:
			return new DescribeScenario();
		default:
			throw new UnsupportedOperationException("Invalid scenario.");
		}
	}
}
