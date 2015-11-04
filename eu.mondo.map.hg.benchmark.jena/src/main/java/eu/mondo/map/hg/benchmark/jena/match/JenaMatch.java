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

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.rdf.model.Resource;

import eu.mondo.map.hg.benchmark.jena.match.schedule.JenaNavigationsMatch;
import eu.mondo.map.hg.benchmark.jena.match.schedule.JenaStationsPathMatch;
import eu.mondo.map.hg.constants.Query;

public abstract class JenaMatch {

	protected QuerySolution qs;

	public JenaMatch(final QuerySolution qs) {
		this.qs = qs;
	}

	public abstract Resource[] toArray();

	public static JenaMatch createMatch(final Query query, final QuerySolution qs) {
		switch (query) {
		case CONNECTEDSEGMENTS:
			return new JenaConnectedSegmentsMatch(qs);
		case POSLENGTH:
			return new JenaPosLengthMatch(qs);
		case ROUTESENSOR:
			return new JenaRouteSensorMatch(qs);
		case SEMAPHORENEIGHBOR:
			return new JenaSemaphoreNeighborMatch(qs);
		case SWITCHSENSOR:
			return new JenaSwitchSensorMatch(qs);
		case SWITCHSET:
			return new JenaSwitchSetMatch(qs);
		case STATIONSPATH:
			return new JenaStationsPathMatch(qs);
		case SCHEDULENAVIGATIONS:
			return new JenaNavigationsMatch(qs);
		default:
			throw new UnsupportedOperationException("Pattern not supported: " + query);
		}
	}

}
