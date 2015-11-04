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

package eu.mondo.map.hg.benchmark.neo4j;

import java.io.IOException;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;

import eu.mondo.map.hg.benchmark.benchmarkcases.BenchmarkCase;
import eu.mondo.map.hg.benchmark.benchmarkcases.layers.AnalyzedBenchmarkCase;
import eu.mondo.map.hg.benchmark.benchmarkcases.layers.DescribedBenchmarkCase;
import eu.mondo.map.hg.benchmark.benchmarkcases.layers.VersatileBenchmarkCase;
import eu.mondo.map.hg.benchmark.neo4j.analyzer.Neo4jModelAnalyzer;
import eu.mondo.map.hg.benchmark.neo4j.analyzer.Neo4jModelDescription;
import eu.mondo.map.hg.benchmark.neo4j.analyzer.Neo4jQueryAnalyzer;
import eu.mondo.map.hg.benchmark.neo4j.checkers.Neo4jCoreChecker;
import eu.mondo.map.hg.benchmark.neo4j.checkers.Neo4jCypherChecker;
import eu.mondo.map.hg.benchmark.neo4j.config.Neo4jBenchmarkConfig;
import eu.mondo.map.hg.benchmark.neo4j.driver.Neo4jDriver;
import eu.mondo.map.hg.benchmark.neo4j.matches.Neo4jMatch;

public class Neo4jBenchmarkCase extends BenchmarkCase<Neo4jMatch, Node, Neo4jDriver> implements
		AnalyzedBenchmarkCase, DescribedBenchmarkCase, VersatileBenchmarkCase {

	protected Neo4jBenchmarkConfig nbc;

	protected GraphDatabaseService graphDb;
	protected String dbPath;

	protected Neo4jDriver neoDriver;

	@Override
	public void init() throws Exception {
		this.nbc = (Neo4jBenchmarkConfig) benchmarkConfig;

		dbPath = benchmarkConfig.getWorkspacePath() + "/models/neo4j-dbs/railway-database";
		driver = neoDriver = new Neo4jDriver(dbPath);
		neoDriver.setBenchmarkConfig(nbc);

		if (nbc.isJavaApi()) {
			checker = Neo4jCoreChecker.newInstance(neoDriver, benchmarkConfig.getQuery());
		} else {
			checker = Neo4jCypherChecker.newInstance(neoDriver, benchmarkConfig);
		}

	}

	@Override
	public void initAnalyzer() {
		modelAnalyzer = new Neo4jModelAnalyzer(neoDriver);
		queryAnalyzer = new Neo4jQueryAnalyzer();
	}

	@Override
	public void initDescription() {
		modelAnalyzer = new Neo4jModelDescription(neoDriver);

	}

	@Override
	public void modify() throws IOException {
		if (benchmarkConfig.isVersatile()) {
			final String query = queryInitializer.resolveQuery(nbc.getWorkspacePath()
					+ "/eu.mondo.map.hg.benchmark.neo4j/src/main/resources/queries/",
					".cypher");
			if (checker instanceof Neo4jCypherChecker) {
				((Neo4jCypherChecker) checker).setQueryDefinition(query);
			}
		}

	}

}
