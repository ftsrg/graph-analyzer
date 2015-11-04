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

package eu.mondo.map.hg.benchmark.sesame;

import java.io.IOException;

import org.openrdf.model.URI;

import eu.mondo.map.hg.benchmark.benchmarkcases.BenchmarkCase;
import eu.mondo.map.hg.benchmark.benchmarkcases.layers.AnalyzedBenchmarkCase;
import eu.mondo.map.hg.benchmark.benchmarkcases.layers.DescribedBenchmarkCase;
import eu.mondo.map.hg.benchmark.benchmarkcases.layers.VersatileBenchmarkCase;
import eu.mondo.map.hg.benchmark.rdf.RDFBenchmarkConfig;
import eu.mondo.map.hg.benchmark.sesame.analyzer.SesameModelAnalyzer;
import eu.mondo.map.hg.benchmark.sesame.analyzer.SesameModelDescription;
import eu.mondo.map.hg.benchmark.sesame.analyzer.SesameQueryAnalyzer;
import eu.mondo.map.hg.benchmark.sesame.checkers.SesameChecker;
import eu.mondo.map.hg.benchmark.sesame.driver.SesameDriver;
import eu.mondo.map.hg.benchmark.sesame.matches.SesameMatch;

public class SesameBenchmarkCase extends BenchmarkCase<SesameMatch, URI, SesameDriver> implements
		VersatileBenchmarkCase, AnalyzedBenchmarkCase, DescribedBenchmarkCase {

	protected SesameDriver sesameDriver;
	protected RDFBenchmarkConfig rbc;
	protected SesameModelAnalyzer sesameModelAnalyzer;
	protected SesameQueryAnalyzer sesameQueryAnalyzer;
	protected SesameModelDescription sesameDescription;
	protected SesameChecker sesameChecker;

	@Override
	protected void init() throws IOException {
		this.rbc = (RDFBenchmarkConfig) benchmarkConfig;

		driver = sesameDriver = new SesameDriver();
		sesameChecker = (SesameChecker) (checker = new SesameChecker(sesameDriver, benchmarkConfig));

	}

	@Override
	public void initAnalyzer() {
		modelAnalyzer = sesameModelAnalyzer = new SesameModelAnalyzer(sesameDriver);
		sesameModelAnalyzer.setBenchmarkConfig(rbc);
		queryAnalyzer = sesameQueryAnalyzer = new SesameQueryAnalyzer();
		sesameQueryAnalyzer.setQueryString(sesameChecker.getQueryDefinition());
	}

	@Override
	public void initDescription() {
		modelAnalyzer = sesameDescription = new SesameModelDescription(sesameDriver);
		sesameDescription.setBenchmarkConfig(rbc);
	}

	@Override
	public void modify() throws IOException {
		if (benchmarkConfig.isVersatile()) {
			final String query = queryInitializer
					.resolveQuery(rbc.getWorkspacePath()
							+ "/hu.bme.mit.trainbenchmark.benchmark.rdf/src/main/resources/queries/",
							".sparql");
			sesameChecker.setQueryDefinition(query);
			sesameQueryAnalyzer.setQueryString(query);
		}
	}
}
