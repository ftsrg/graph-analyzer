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

package eu.mondo.map.hg.benchmark.allegro;

import org.apache.commons.cli.ParseException;

import eu.mondo.map.hg.benchmark.BenchmarkLogic;
import eu.mondo.map.hg.benchmark.rdf.RDFBenchmarkConfig;

public class AllegroBenchmarkLogic extends BenchmarkLogic{

	protected RDFBenchmarkConfig rbc;
	
	public AllegroBenchmarkLogic(final String[] args) throws ParseException {
		bc = rbc = new RDFBenchmarkConfig(args, "Allegro");
	}

	public AllegroBenchmarkLogic(final RDFBenchmarkConfig rbc) {
		super(rbc);
		this.rbc = rbc;
	}

}
