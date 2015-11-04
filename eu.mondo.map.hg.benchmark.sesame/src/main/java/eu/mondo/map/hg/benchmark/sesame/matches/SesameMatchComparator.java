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

import org.openrdf.model.URI;

import eu.mondo.map.hg.benchmark.matches.MatchComparator;
import eu.mondo.map.hg.benchmark.sesame.driver.URIComparator;

public class SesameMatchComparator extends MatchComparator<SesameMatch, URI> {

	protected URIComparator uc = new URIComparator();

	@Override
	public int compare(final SesameMatch o1, final SesameMatch o2) {
		final URI[] m1 = o1.toArray();
		final URI[] m2 = o2.toArray();
		return compareArrays(m1, m2, uc);
	}

}
