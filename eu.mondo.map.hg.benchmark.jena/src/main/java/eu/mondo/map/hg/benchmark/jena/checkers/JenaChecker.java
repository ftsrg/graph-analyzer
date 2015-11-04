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
package eu.mondo.map.hg.benchmark.jena.checkers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;

import eu.mondo.map.hg.benchmark.checker.Checker;
import eu.mondo.map.hg.benchmark.config.BenchmarkConfig;
import eu.mondo.map.hg.benchmark.jena.driver.JenaDriver;
import eu.mondo.map.hg.benchmark.jena.match.JenaMatch;

public class JenaChecker extends Checker<JenaMatch> {

	protected JenaDriver jenaDriver;
	protected Query query;
	protected eu.mondo.map.hg.constants.Query tbQuery;
	protected String queryDefinition;

	public JenaChecker(final JenaDriver jenaDriver, final BenchmarkConfig bc) throws IOException {
		super();
		this.jenaDriver = jenaDriver;
		final String queryPath = bc.getWorkspacePath()
				+ "/eu.mondo.map.hg.benchmark.rdf/src/main/resources/queries/"
				+ bc.getQuery() + ".sparql";
		tbQuery = bc.getQuery();
		this.queryDefinition = FileUtils.readFileToString(new File(queryPath));
		if (!bc.isVersatile()) {
			query = QueryFactory.read(queryPath);
		}
	}

	@Override
	public Collection<JenaMatch> check() throws IOException {
		final List<JenaMatch> matches = new ArrayList<>();
		try (QueryExecution queryExecution = QueryExecutionFactory.create(query,
				jenaDriver.getModel())) {
			final ResultSet resultSet = queryExecution.execSelect();

			while (resultSet.hasNext()) {
				final QuerySolution qs = resultSet.next();
				final JenaMatch match = JenaMatch.createMatch(tbQuery, qs);
				matches.add(match);
			}
		}

		return matches;
	}

	public void setQueryDefinition(String queryDefinition) {
		this.queryDefinition = queryDefinition;
	}

	public String getQueryDefinition() {
		return queryDefinition;
	}

	public void setQuery(String queryDefinition) {
		query = QueryFactory.create(queryDefinition);
	}
}
