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

package eu.mondo.map.hg.benchmark.sesame.checkers;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.RepositoryException;

import eu.mondo.map.hg.benchmark.checker.Checker;
import eu.mondo.map.hg.benchmark.config.BenchmarkConfig;
import eu.mondo.map.hg.benchmark.sesame.driver.SesameDriver;
import eu.mondo.map.hg.benchmark.sesame.matches.SesameMatch;
import eu.mondo.map.hg.constants.Query;

public class SesameChecker extends Checker<SesameMatch> {

	protected final SesameDriver driver;
	protected Query query;
	protected String queryDefinition;

	public SesameChecker(final SesameDriver driver, final BenchmarkConfig bc) throws IOException {
		this.driver = driver;
		this.query = bc.getQuery();

		final String queryPath = bc.getWorkspacePath()
				+ "/eu.mondo.map.hg.benchmark.rdf/src/main/resources/queries/"
				+ bc.getQuery() + ".sparql";
		this.queryDefinition = FileUtils.readFileToString(new File(queryPath));
	}

	@Override
	public Collection<SesameMatch> check() throws RepositoryException, MalformedQueryException,
			QueryEvaluationException {
		return driver.runQuery(query, queryDefinition);
	}

	public String getQueryDefinition() {
		return queryDefinition;
	}

	public void setQueryDefinition(String queryDefinition) {
		this.queryDefinition = queryDefinition;
	}

}
