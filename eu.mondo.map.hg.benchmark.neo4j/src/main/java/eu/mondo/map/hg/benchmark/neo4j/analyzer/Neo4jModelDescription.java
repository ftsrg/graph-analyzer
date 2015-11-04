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

package eu.mondo.map.hg.benchmark.neo4j.analyzer;

import java.util.Iterator;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.ResourceIterable;
import org.neo4j.graphdb.Transaction;
import org.neo4j.tooling.GlobalGraphOperations;

import eu.mondo.map.analysis.ModelDescription;
import eu.mondo.map.hg.benchmark.neo4j.driver.Neo4jDriver;

public class Neo4jModelDescription extends ModelDescription {

	protected GraphDatabaseService database;
	protected Neo4jDriver driver;
	protected GlobalGraphOperations graphOperations;

	protected Transaction tx;

	public Neo4jModelDescription(Neo4jDriver driver) {
		this.driver = driver;
	}

	@Override
	public void calculateMetrics() {
		database = driver.getGraphDb();
		graphOperations = GlobalGraphOperations.at(database);

		tx = database.beginTx();

		ResourceIterable<Node> nodes = graphOperations.getAllNodes();
		Iterator<Node> iterator = nodes.iterator();

		Node node;
		int degree;
		while (iterator.hasNext()) {
			node = iterator.next();
			degree = node.getDegree();
			addDegree(ALL, degree);
		}
		tx.success();
		tx.close();

	}

}
