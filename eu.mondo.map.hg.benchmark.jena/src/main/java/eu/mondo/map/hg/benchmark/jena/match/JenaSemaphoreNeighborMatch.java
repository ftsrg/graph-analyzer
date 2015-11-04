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

import static eu.mondo.map.hg.constants.railway.RailwayQueryConstants.VAR_ROUTE1;
import static eu.mondo.map.hg.constants.railway.RailwayQueryConstants.VAR_ROUTE2;
import static eu.mondo.map.hg.constants.railway.RailwayQueryConstants.VAR_SEMAPHORE;
import static eu.mondo.map.hg.constants.railway.RailwayQueryConstants.VAR_SENSOR1;
import static eu.mondo.map.hg.constants.railway.RailwayQueryConstants.VAR_SENSOR2;
import static eu.mondo.map.hg.constants.railway.RailwayQueryConstants.VAR_TE1;
import static eu.mondo.map.hg.constants.railway.RailwayQueryConstants.VAR_TE2;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.rdf.model.Resource;

import eu.mondo.map.hg.benchmark.matches.railway.SemaphoreNeighborMatch;

public class JenaSemaphoreNeighborMatch extends JenaMatch implements SemaphoreNeighborMatch {

	public JenaSemaphoreNeighborMatch(final QuerySolution qs) {
		super(qs);
	}

	@Override
	public Resource getSemaphore() {
		return qs.getResource(VAR_SEMAPHORE);
	}

	@Override
	public Resource getRoute1() {
		return qs.getResource(VAR_ROUTE1);
	}

	@Override
	public Resource getRoute2() {
		return qs.getResource(VAR_ROUTE2);
	}

	@Override
	public Resource getSensor1() {
		return qs.getResource(VAR_SENSOR1);
	}

	@Override
	public Resource getSensor2() {
		return qs.getResource(VAR_SENSOR2);
	}

	@Override
	public Resource getTe1() {
		return qs.getResource(VAR_TE1);
	}

	@Override
	public Resource getTe2() {
		return qs.getResource(VAR_TE2);
	}

	@Override
	public Resource[] toArray() {
		return new Resource[] { getSemaphore(), getRoute1(), getRoute2(), getSensor1(), getSensor2(),
				getTe1(), getTe2() };
	}

}
