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
package eu.mondo.map.hg.benchmark.neo4j.matches;

import java.util.Map;

import org.neo4j.graphdb.Node;

import eu.mondo.map.hg.benchmark.matches.railway.RouteSensorMatch;
import eu.mondo.map.hg.constants.railway.RailwayQueryConstants;

public class Neo4jRouteSensorMatch extends Neo4jMatch implements RouteSensorMatch {

	public Neo4jRouteSensorMatch(final Map<String, Object> match) {
		super(match);
	}

	@Override
	public Node getRoute() {
		return (Node) match.get(RailwayQueryConstants.VAR_ROUTE);
	}

	@Override
	public Node getSensor() {
		return (Node) match.get(RailwayQueryConstants.VAR_SENSOR);
	}

	@Override
	public Node getSwP() {
		return (Node) match.get(RailwayQueryConstants.VAR_SWP);
	}

	@Override
	public Node getSw() {
		return (Node) match.get(RailwayQueryConstants.VAR_SW);
	}

	@Override
	public Node[] toArray() {
		return new Node[] { getRoute(), getSensor(), getSwP(), getSw() };
	}

}
