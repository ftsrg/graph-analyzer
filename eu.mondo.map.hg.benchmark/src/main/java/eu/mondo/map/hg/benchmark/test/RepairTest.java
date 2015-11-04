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
import static eu.mondo.map.hg.constants.ScenarioConstants.REPAIR;

import java.io.IOException;

import org.apache.commons.cli.ParseException;
import org.junit.Test;

public abstract class RepairTest extends TrainBenchmarkTest {

	@Test
	public void connectedSegmentsRepair() throws ParseException, IOException {
		testQuery(CONNECTEDSEGMENTS, REPAIR, 3);
	}

	@Test
	public void posLengthRepair() throws ParseException, IOException {
		testQuery(POSLENGTH, REPAIR, 91);
	}

	@Test
	public void routeSensorRepair() throws ParseException, IOException {
		testQuery(ROUTESENSOR, REPAIR, 6);
	}

	@Test
	public void semaphoreNeighborRepair() throws ParseException, IOException {
		testQuery(SEMAPHORENEIGHBOR, REPAIR, 0);
	}

	@Test
	public void switchSensorRepair() throws ParseException, IOException {
		testQuery(SWITCHSENSOR, REPAIR, 2);
	}

	@Test
	public void switchSetRepair() throws ParseException, IOException {
		testQuery(SWITCHSET, REPAIR, 1);
	}

}
