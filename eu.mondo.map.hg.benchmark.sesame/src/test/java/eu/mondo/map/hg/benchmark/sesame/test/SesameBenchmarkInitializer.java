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

package eu.mondo.map.hg.benchmark.sesame.test;

import eu.mondo.map.hg.benchmark.rdf.RDFBenchmarkConfig;
import eu.mondo.map.hg.benchmark.sesame.SesameBenchmarkLogic;
import eu.mondo.map.hg.benchmark.test.TestBenchmarkInitializer;
import eu.mondo.map.hg.constants.Query;
import eu.mondo.map.hg.constants.ScenarioConstants;

public class SesameBenchmarkInitializer extends TestBenchmarkInitializer<SesameBenchmarkLogic> {

	@Override
	protected SesameBenchmarkLogic initializeBenchmark(final Query query, final ScenarioConstants scenario) {
		final RDFBenchmarkConfig rbc = new RDFBenchmarkConfig("Sesame", scenario, size, runIndex,
				query, iterationCount, modificationMethod, modificationConstant, model);
		return new SesameBenchmarkLogic(rbc);
	}

}
