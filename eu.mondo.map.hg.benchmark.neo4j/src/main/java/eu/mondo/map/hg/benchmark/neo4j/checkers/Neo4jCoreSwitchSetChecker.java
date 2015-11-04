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

package eu.mondo.map.hg.benchmark.neo4j.checkers;

import static eu.mondo.map.hg.benchmark.neo4j.constants.Neo4jConstants.labelRoute;
import static eu.mondo.map.hg.benchmark.neo4j.constants.Neo4jConstants.labelSemaphore;
import static eu.mondo.map.hg.benchmark.neo4j.constants.Neo4jConstants.labelSwitch;
import static eu.mondo.map.hg.benchmark.neo4j.constants.Neo4jConstants.labelSwitchPosition;
import static eu.mondo.map.hg.benchmark.neo4j.constants.Neo4jConstants.relationshipTypeEntry;
import static eu.mondo.map.hg.benchmark.neo4j.constants.Neo4jConstants.relationshipTypeFollows;
import static eu.mondo.map.hg.benchmark.neo4j.constants.Neo4jConstants.relationshipTypeSwitch;
import static eu.mondo.map.hg.constants.railway.RailwayModelConstants.SIGNAL;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;

import eu.mondo.map.hg.benchmark.neo4j.driver.Neo4jDriver;
import eu.mondo.map.hg.benchmark.neo4j.matches.Neo4jSwitchSetMatch;
import eu.mondo.map.hg.constants.railway.RailwayModelConstants;
import eu.mondo.map.hg.constants.railway.RailwayQueryConstants;
import eu.mondo.map.hg.constants.railway.Signal;

public class Neo4jCoreSwitchSetChecker extends Neo4jCoreChecker<Neo4jSwitchSetMatch> {

	public Neo4jCoreSwitchSetChecker(final Neo4jDriver neoDriver) {
		super(neoDriver);
	}

	@Override
	public Collection<Neo4jSwitchSetMatch> check() {
		final Collection<Neo4jSwitchSetMatch> matches = new HashSet<>();

		final GraphDatabaseService graphDb = driver.getGraphDb();
		try (Transaction tx = graphDb.beginTx()) {
			final ResourceIterator<Node> semaphores = graphDb.findNodes(labelSemaphore);
			while (semaphores.hasNext()) {
				final Node semaphore = semaphores.next();

				// semaphore.signal = "GO"
				final Object signal = semaphore.getProperty(SIGNAL);
				if (!Signal.GO.toString().equals(signal)) {
					continue;
				}

				// (semaphore:Semaphore)<-[:entry]-(route:Route)
				final Iterable<Relationship> entries = semaphore.getRelationships(
						Direction.INCOMING, relationshipTypeEntry);
				for (final Relationship entry : entries) {
					final Node route = entry.getStartNode();
					if (!route.hasLabel(labelRoute)) {
						continue;
					}

					// (route:Route)-[:follows]->(swP:SwitchPosition)
					final Iterable<Relationship> followss = route.getRelationships(
							Direction.OUTGOING, relationshipTypeFollows);
					for (final Relationship follows : followss) {
						final Node swP = follows.getEndNode();
						if (!swP.hasLabel(labelSwitchPosition)) {
							continue;
						}

						// (swP:SwitchPosition)-[:switch]->(sw:Switch)
						final Iterable<Relationship> relationshipSwitches = swP
								.getRelationships(Direction.OUTGOING,
										relationshipTypeSwitch);

						if (!relationshipSwitches.iterator().hasNext()) {
							continue;
						}

						final Node sw = relationshipSwitches.iterator().next()
								.getEndNode();
						if (!sw.hasLabel(labelSwitch)) {
							continue;
						}

						final Object currentPosition = sw
								.getProperty(RailwayModelConstants.CURRENTPOSITION);
						final Object position = swP
								.getProperty(RailwayModelConstants.POSITION);

						if (!currentPosition.equals(position)) {
							final Map<String, Object> match = new HashMap<>();
							match.put(RailwayQueryConstants.VAR_SEMAPHORE,
									semaphore);
							match.put(RailwayQueryConstants.VAR_ROUTE, route);
							match.put(RailwayQueryConstants.VAR_SWP, swP);
							match.put(RailwayQueryConstants.VAR_SW, sw);
							matches.add(new Neo4jSwitchSetMatch(match));
						}
					}
				}
			}
		}

		return matches;
	}
}
