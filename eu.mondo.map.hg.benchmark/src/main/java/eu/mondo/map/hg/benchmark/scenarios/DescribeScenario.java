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
import eu.mondo.map.hg.benchmark.phases.DestroyPhase;
import eu.mondo.map.hg.benchmark.phases.InitializationPhase;
import eu.mondo.map.hg.benchmark.phases.ReadPhase;
import eu.mondo.map.hg.benchmark.phases.analysis.DescriptionInitializationPhase;
import eu.mondo.map.hg.benchmark.phases.analysis.MetricsInitializationPhase;
import eu.mondo.map.hg.benchmark.phases.analysis.ModelMetricsCalculationPhase;
import eu.mondo.sam.core.phases.SequencePhase;

public class DescribeScenario extends Scenario<BenchmarkCase<?, ?, ?>> {

	@Override
	public void build() {
		SequencePhase seq = new SequencePhase();

		// @formatter:off
		seq.addPhases(new InitializationPhase("Init"),
				new DescriptionInitializationPhase("DescInit"), 
				new ReadPhase("Read"),
				new MetricsInitializationPhase("InitMetrics"), 
				new ModelMetricsCalculationPhase("CalcModelMetrics"),
				new DestroyPhase("Destroy")
		);
		rootPhase = seq;
		// @formatter:on
	}

	@Override
	public String getName() {
		return "Describe";
	}

}
