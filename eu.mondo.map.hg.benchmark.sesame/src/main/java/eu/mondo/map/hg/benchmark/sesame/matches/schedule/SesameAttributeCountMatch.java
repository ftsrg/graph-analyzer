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

package eu.mondo.map.hg.benchmark.sesame.matches.schedule;

import static eu.mondo.map.hg.constants.schedule.ScheduleQueryConstants.VAR_ATTRIBUTE;
import static eu.mondo.map.hg.constants.schedule.ScheduleQueryConstants.VAR_COUNT;

import org.openrdf.model.URI;
import org.openrdf.query.BindingSet;

import eu.mondo.map.hg.benchmark.matches.schedule.ScheduleAttributeCountMatch;
import eu.mondo.map.hg.benchmark.sesame.matches.SesameMatch;

public class SesameAttributeCountMatch extends SesameMatch implements ScheduleAttributeCountMatch {

	public SesameAttributeCountMatch(BindingSet bs) {
		super(bs);
	}

	@Override
	public URI[] toArray() {
		return new URI[] { getAttribute(), getCount() };
	}

	@Override
	public URI getAttribute() {
		return (URI) bs.getValue(VAR_ATTRIBUTE);
	}

	@Override
	public URI getCount() {
		return (URI) bs.getValue(VAR_COUNT);
	}

}
