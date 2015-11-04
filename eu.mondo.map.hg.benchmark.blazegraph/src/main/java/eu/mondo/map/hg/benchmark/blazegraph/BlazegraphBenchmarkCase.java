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

package eu.mondo.map.hg.benchmark.blazegraph;

import java.io.IOException;

import org.openrdf.repository.RepositoryException;

import eu.mondo.map.hg.benchmark.blazegraph.driver.BlazegraphDriver;
import eu.mondo.map.hg.benchmark.rdf.RDFBenchmarkConfig;
import eu.mondo.map.hg.benchmark.sesame.SesameBenchmarkCase;
import eu.mondo.map.hg.benchmark.sesame.checkers.SesameChecker;

public class BlazegraphBenchmarkCase extends SesameBenchmarkCase {

	@Override
	protected void init() throws IOException {
		this.rbc = (RDFBenchmarkConfig) benchmarkConfig;

		try {
			driver = sesameDriver = new BlazegraphDriver(rbc);
		} catch (RepositoryException e) {
			throw new IOException(e);
		}
		sesameChecker = (SesameChecker) (checker = new SesameChecker(sesameDriver, benchmarkConfig));

//		transformation = SesameTransformation.newInstance(sesameDriver, benchmarkConfig.getQuery(),
//				benchmarkConfig.getScenario());
	}

}
