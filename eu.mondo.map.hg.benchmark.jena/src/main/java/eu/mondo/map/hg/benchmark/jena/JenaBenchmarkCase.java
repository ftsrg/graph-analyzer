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

package eu.mondo.map.hg.benchmark.jena;

import java.io.IOException;

import com.hp.hpl.jena.rdf.model.Resource;

import eu.mondo.map.hg.benchmark.benchmarkcases.BenchmarkCase;
import eu.mondo.map.hg.benchmark.benchmarkcases.layers.AnalyzedBenchmarkCase;
import eu.mondo.map.hg.benchmark.benchmarkcases.layers.VersatileBenchmarkCase;
import eu.mondo.map.hg.benchmark.jena.analyzer.JenaModelAnalyzer;
import eu.mondo.map.hg.benchmark.jena.analyzer.JenaQueryAnalyzer;
import eu.mondo.map.hg.benchmark.jena.checkers.JenaChecker;
import eu.mondo.map.hg.benchmark.jena.driver.JenaDriver;
import eu.mondo.map.hg.benchmark.jena.match.JenaMatch;
import eu.mondo.map.hg.benchmark.rdf.RDFBenchmarkConfig;

public class JenaBenchmarkCase extends BenchmarkCase<JenaMatch, Resource, JenaDriver> implements
		AnalyzedBenchmarkCase, VersatileBenchmarkCase {

	protected JenaDriver jenaDriver;
	protected RDFBenchmarkConfig rbc;
	protected JenaModelAnalyzer jenaModelAnalyzer;
	protected JenaQueryAnalyzer jenaQueryAnalyzer;
	protected JenaChecker jenaChecker;

	protected RDFBenchmarkConfig getRDFBenchmarkConfig() {
		return (RDFBenchmarkConfig) benchmarkConfig;
	}

	@Override
	protected void init() throws IOException {
		rbc = (RDFBenchmarkConfig) benchmarkConfig;
		driver = jenaDriver = new JenaDriver();
		checker = jenaChecker = new JenaChecker(jenaDriver, benchmarkConfig);

	}

	@Override
	public void initAnalyzer() {
		modelAnalyzer = jenaModelAnalyzer = new JenaModelAnalyzer(jenaDriver);
		jenaModelAnalyzer.setBenchmarkConfig(rbc);
		queryAnalyzer = jenaQueryAnalyzer = new JenaQueryAnalyzer();
		jenaQueryAnalyzer.setQueryString(jenaChecker.getQueryDefinition());
	}

	@Override
	public void modify() throws IOException {
		if (benchmarkConfig.isVersatile()) {
			final String query = queryInitializer
					.resolveQuery(rbc.getWorkspacePath()
							+ "/hu.bme.mit.trainbenchmark.benchmark.rdf/src/main/resources/queries/",
							".sparql");
			jenaChecker.setQueryDefinition(query);
			jenaChecker.setQuery(query);
			jenaQueryAnalyzer.setQueryString(query);
		}

	}

}
