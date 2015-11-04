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
package eu.mondo.map.hg.benchmark.jena.match;

import static eu.mondo.map.hg.constants.railway.RailwayQueryConstants.VAR_ROUTE;
import static eu.mondo.map.hg.constants.railway.RailwayQueryConstants.VAR_SENSOR;
import static eu.mondo.map.hg.constants.railway.RailwayQueryConstants.VAR_SW;
import static eu.mondo.map.hg.constants.railway.RailwayQueryConstants.VAR_SWP;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.rdf.model.Resource;

import eu.mondo.map.hg.benchmark.matches.railway.RouteSensorMatch;

public class JenaRouteSensorMatch extends JenaMatch implements RouteSensorMatch {

	public JenaRouteSensorMatch(final QuerySolution qs) {
		super(qs);
	}

	@Override
	public Resource getRoute() {
		return qs.getResource(VAR_ROUTE);
	}

	@Override
	public Resource getSensor() {
		return qs.getResource(VAR_SENSOR);
	}

	@Override
	public Resource getSwP() {
		return qs.getResource(VAR_SWP);
	}

	@Override
	public Resource getSw() {
		return qs.getResource(VAR_SW);
	}

	@Override
	public Resource[] toArray() {
		return new Resource[] { getRoute(), getSensor(), getSwP(), getSw() };
	}

}
