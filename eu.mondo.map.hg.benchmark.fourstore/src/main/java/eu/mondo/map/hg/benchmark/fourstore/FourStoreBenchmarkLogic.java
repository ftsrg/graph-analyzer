/*******************************************************************************
 * Copyright (c) 2010-2015, Gabor Szarnyas, Benedek Izso, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package eu.mondo.map.hg.benchmark.fourstore;

import org.apache.commons.cli.ParseException;

import eu.mondo.map.hg.benchmark.BenchmarkLogic;
import eu.mondo.map.hg.benchmark.fourstore.config.FourStoreBenchmarkConfig;

public class FourStoreBenchmarkLogic extends BenchmarkLogic {

	protected FourStoreBenchmarkConfig fsbc;

	public FourStoreBenchmarkLogic(final String[] args) throws ParseException {
		bc = fsbc = new FourStoreBenchmarkConfig(args, "FourStore");
	}

	public FourStoreBenchmarkLogic(final FourStoreBenchmarkConfig fsbc) {
		super(fsbc);
		this.fsbc = fsbc;
	}

}
