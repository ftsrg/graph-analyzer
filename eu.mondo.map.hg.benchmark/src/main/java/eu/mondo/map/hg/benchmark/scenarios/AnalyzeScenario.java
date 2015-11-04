/*******************************************************************************
 * Copyright (c) 2010-2015, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/

package eu.mondo.map.hg.benchmark.scenarios;

import eu.mondo.map.hg.benchmark.benchmarkcases.BenchmarkCase;
import eu.mondo.map.hg.benchmark.phases.CheckPhase;
import eu.mondo.map.hg.benchmark.phases.DestroyPhase;
import eu.mondo.map.hg.benchmark.phases.InitializationPhase;
import eu.mondo.map.hg.benchmark.phases.MatchProcessingPhase;
import eu.mondo.map.hg.benchmark.phases.QueryInitializationPhase;
import eu.mondo.map.hg.benchmark.phases.ReadPhase;
import eu.mondo.map.hg.benchmark.phases.analysis.AnalyzerInitializationPhase;
import eu.mondo.map.hg.benchmark.phases.analysis.MetricsInitializationPhase;
import eu.mondo.map.hg.benchmark.phases.analysis.ModelMetricsCalculationPhase;
import eu.mondo.map.hg.benchmark.phases.analysis.QueryMetricsCalculationPhase;
import eu.mondo.map.hg.benchmark.queries.QueryInitializer;
import eu.mondo.sam.core.phases.IterationPhase;
import eu.mondo.sam.core.phases.SequencePhase;

public class AnalyzeScenario extends Scenario<BenchmarkCase<?, ?, ?>> {

	@Override
	public void build() {
		SequencePhase seq = new SequencePhase();
		SequencePhase sequenceInIteration = new SequencePhase();
		// @formatter:off
		sequenceInIteration.addPhases(
				new QueryInitializationPhase("QueryInit"), 
				new CheckPhase("Check"), 
				new QueryMetricsCalculationPhase("CalcQueryMetrics"),
				new MatchProcessingPhase("QueryResults")
		);
		// @formatter:on

		QueryInitializer initializer = benchmarkCase.getQueryInitializer();
		int maxIteration = initializer.getQueryBuilder() == null ? 1 : initializer.getQueryBuilder()
				.getNumberOfQueries();

		IterationPhase queryIteration = new IterationPhase(maxIteration);
		queryIteration.setPhase(sequenceInIteration);

		// @formatter:off
		seq.addPhases(new InitializationPhase("Init"),
				new AnalyzerInitializationPhase("AnalyzerInit"), 
				new ReadPhase("Read"),
				new MetricsInitializationPhase("InitMetrics"), 
				new ModelMetricsCalculationPhase("CalcModelMetrics"), 
				queryIteration, 
				new DestroyPhase("Destroy")
		);
		rootPhase = seq;
		// @formatter:on

	}

	@Override
	public String getName() {
		return "Analyze";
	}

}
