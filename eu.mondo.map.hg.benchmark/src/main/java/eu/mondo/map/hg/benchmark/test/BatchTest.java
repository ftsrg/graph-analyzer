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

package eu.mondo.map.hg.benchmark.test;

import static eu.mondo.map.hg.constants.Query.CONNECTEDSEGMENTS;
import static eu.mondo.map.hg.constants.Query.POSLENGTH;
import static eu.mondo.map.hg.constants.Query.ROUTESENSOR;
import static eu.mondo.map.hg.constants.Query.SEMAPHORENEIGHBOR;
import static eu.mondo.map.hg.constants.Query.SWITCHSENSOR;
import static eu.mondo.map.hg.constants.Query.SWITCHSET;
import static eu.mondo.map.hg.constants.ScenarioConstants.BATCH;

import java.io.IOException;

import org.apache.commons.cli.ParseException;
import org.junit.Test;

public abstract class BatchTest extends TrainBenchmarkTest {

	@Test
	public void connectedSegments() throws ParseException, IOException {
		testQuery(CONNECTEDSEGMENTS, BATCH, 4);
	}

	@Test
	public void posLength() throws ParseException, IOException {
		testQuery(POSLENGTH, BATCH, 92);
	}

	@Test
	public void routeSensor() throws ParseException, IOException {
		testQuery(ROUTESENSOR, BATCH, 7);
	}

	@Test
	public void semaphoreNeighbor() throws ParseException, IOException {
		testQuery(SEMAPHORENEIGHBOR, BATCH, 1);
	}

	@Test
	public void switchSensor() throws ParseException, IOException {
		testQuery(SWITCHSENSOR, BATCH, 3);
	}

	@Test
	public void switchSet() throws ParseException, IOException {
		testQuery(SWITCHSET, BATCH, 2);
	}

}