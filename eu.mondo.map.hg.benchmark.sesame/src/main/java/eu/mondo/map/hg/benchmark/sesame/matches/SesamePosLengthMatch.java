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
package eu.mondo.map.hg.benchmark.sesame.matches;

import static eu.mondo.map.hg.constants.railway.RailwayQueryConstants.VAR_LENGTH;
import static eu.mondo.map.hg.constants.railway.RailwayQueryConstants.VAR_SEGMENT;

import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.query.BindingSet;

import eu.mondo.map.hg.benchmark.matches.railway.PosLengthMatch;

public class SesamePosLengthMatch extends SesameMatch implements PosLengthMatch {

	public SesamePosLengthMatch(final BindingSet bs) {
		super(bs);
	}

	@Override
	public URI getSegment() {
		return (URI) bs.getValue(VAR_SEGMENT);
	}

	public Value getLength() {
		return bs.getValue(VAR_LENGTH);
	}

	@Override
	public URI[] toArray() {
		return new URI[] { getSegment() };
	}

}
