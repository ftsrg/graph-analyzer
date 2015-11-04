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

import static eu.mondo.map.hg.constants.schedule.ScheduleQueryConstants.VAR_COUNT;

import java.util.HashMap;
import java.util.Map;

import org.openrdf.model.Literal;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.query.BindingSet;

import eu.mondo.map.hg.benchmark.matches.MatchProcessor;
import eu.mondo.map.hg.benchmark.matches.schedule.ScheduleCountMatch;
import eu.mondo.map.hg.benchmark.sesame.matches.SesameMatch;

public abstract class SesameScheduleCountMatchProcessor extends SesameMatch implements ScheduleCountMatch,
		MatchProcessor {

	public SesameScheduleCountMatchProcessor(BindingSet bs) {
		super(bs);
	}

	@Override
	public URI getCount() {
		return (URI) bs.getValue(VAR_COUNT);
	}

	@Override
	public URI[] toArray() {
		return new URI[] { getCount() };
	}

	@Override
	public Map<String, Object> process() {
		Map<String, Object> processed = new HashMap<String, Object>();
		Value count = bs.getBinding(VAR_COUNT).getValue();
		long value = 0;
		if (count instanceof Literal) {
			value = ((Literal) count).longValue();
		}
		processed.put(getProcessedName(), value);
		return processed;
	}

	protected abstract String getProcessedName();

}
