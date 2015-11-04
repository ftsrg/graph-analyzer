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

package eu.mondo.map.hg.benchmark.jena.analyzer;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;

import eu.mondo.map.hg.benchmark.jena.driver.JenaDriver;
import eu.mondo.map.hg.benchmark.rdf.analyzer.RDFModelAnalyzer;

public class JenaModelAnalyzer extends RDFModelAnalyzer {

	protected JenaDriver driver;

	public JenaModelAnalyzer(JenaDriver driver) {
		this.driver = driver;
	}

	@Override
	public void calculateMetrics() {
		Model model = driver.getModel();

		QueryExecution queryExecution = QueryExecutionFactory.create(getTriples, model);
		ResultSet resultSet = queryExecution.execSelect();

		calculateEdges(resultSet);

		queryExecution = QueryExecutionFactory.create(getDegreesQuery, model);
		resultSet = queryExecution.execSelect();

		calculateDegrees(resultSet);

		queryExecution = QueryExecutionFactory.create(getDegreesQuery, model);
		resultSet = queryExecution.execSelect();
		calculateNumberOfDegrees(resultSet);

	}

	private void calculateNumberOfDegrees(ResultSet resultSet) {
//		double degree = 0;
//		int roundedDegree = roundAverageDegree(EdgeDirection.BOTH);
//		int roundedOutgoingDegree = roundAverageDegree(EdgeDirection.OUTGOING);
//		QuerySolution solution;
//
//		while (resultSet.hasNext()) {
//			solution = resultSet.next();
//			degree = solution.getLiteral("outdegree").getDouble();
//			changeNumberOfDegrees(EdgeDirection.OUTGOING, degree, roundedOutgoingDegree);
//			degree += solution.getLiteral("indegree").getDouble();
//			changeNumberOfDegrees(EdgeDirection.BOTH, degree, roundedDegree);
//		}
	}

	private void calculateDegrees(ResultSet resultSet) {
//		double degree;
//		QuerySolution solution;
//
//		while (resultSet.hasNext()) {
//			solution = resultSet.next();
//			degree = solution.getLiteral("outdegree").getDouble();
//			numberOfNodesWithOutgoingDegrees += degree > 0 ? 1 : 0;
//			numberOfNodes++;
//
//			changeMaximumDegree(EdgeDirection.OUTGOING, degree);
//
//			degree += solution.getLiteral("indegree").getDouble();
//			changeMaximumDegree(EdgeDirection.BOTH, degree);
//		}
//		calculateAverageDegree(EdgeDirection.BOTH);
//		calculateAverageDegree(EdgeDirection.OUTGOING);
	}

	private void calculateEdges(ResultSet resultSet) {
//		QuerySolution solution;
//		while (resultSet.hasNext()) {
//			solution = resultSet.next();
//			numberOfEdges = solution.getLiteral("triples").getDouble();
//		}
	}

}
