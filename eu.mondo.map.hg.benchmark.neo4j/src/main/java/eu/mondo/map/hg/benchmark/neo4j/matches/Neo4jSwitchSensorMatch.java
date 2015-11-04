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

import static eu.mondo.map.hg.constants.railway.RailwayQueryConstants.VAR_SW;

import java.util.Map;

import org.neo4j.graphdb.Node;

import eu.mondo.map.hg.benchmark.matches.railway.SwitchSensorMatch;

public class Neo4jSwitchSensorMatch extends Neo4jMatch implements SwitchSensorMatch {

	public Neo4jSwitchSensorMatch(final Map<String, Object> match) {
		super(match);
	}

	@Override
	public Node getSw() {
		return (Node) match.get(VAR_SW);
	}

	@Override
	public Node[] toArray() {
		return new Node[] { getSw() };
	}

}
