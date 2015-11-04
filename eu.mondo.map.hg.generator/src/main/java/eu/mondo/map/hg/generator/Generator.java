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

package eu.mondo.map.hg.generator;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

import eu.mondo.map.hg.constants.TrainBenchmarkConstants;
import eu.mondo.map.hg.generator.config.GeneratorConfig;

public abstract class Generator {

	protected int id = 1;

	protected static final Map<String, Object> emptyMap = Collections.emptyMap();

	protected static final List<Integer> emptyList = Collections.emptyList();

	protected GeneratorConfig generatorConfig;

	protected Random random = new Random(TrainBenchmarkConstants.RANDOM_SEED);

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public GeneratorConfig getGeneratorConfig() {
		return generatorConfig;
	}

	public void setGeneratorConfig(GeneratorConfig generatorConfig) {
		this.generatorConfig = generatorConfig;
	}

	public Random getRandom() {
		return random;
	}

	public void setRandom(Random random) {
		this.random = random;
	}

}
