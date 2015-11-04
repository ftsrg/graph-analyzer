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

package eu.mondo.map.hg.generator.concretes.schedule.scales;

import static eu.mondo.map.hg.constants.schedule.ScheduleModelConstants.DESTINATIONS;
import static eu.mondo.map.hg.constants.schedule.ScheduleModelConstants.NEIGHBORS;
import static eu.mondo.map.hg.constants.schedule.ScheduleModelConstants.SCHEDULE;
import static eu.mondo.map.hg.constants.schedule.ScheduleModelConstants.STATION;

import java.io.IOException;
import java.util.ArrayList;

import eu.mondo.map.hg.generator.FormatGenerator;
import eu.mondo.map.hg.generator.concretes.schedule.HeterogeneousScheduleGenerator;
import eu.mondo.map.hg.generator.config.GeneratorConfig;
import eu.mondo.map.hg.generator.util.Node;

public class HeterogeneousScaleFreeGenerator extends HeterogeneousScheduleGenerator implements ScaleFreeModel {

	protected ScaleFreeGenerator sfg;

	public HeterogeneousScaleFreeGenerator(FormatGenerator formatGenerator,
			GeneratorConfig generatorConfig) {
		super(formatGenerator, generatorConfig);
	}

	@Override
	protected void initializeConstants() {
		super.initializeConstants();
		sfg = new ScaleFreeGenerator(this);
		sfg.initializeConstants();
	}

	@Override
	protected void printMessage() {
		System.out.print("Generate heterogeneous scale-free model " + submodel + ", size: "
				+ generatorConfig.getSize() + "...");

	}

	@Override
	protected void initializationStep() throws IOException {
		sfg.initializationStep();
	}

	@Override
	protected void generateStations() throws IOException {
		if (stations.size() < maxNumberOfStations) {
			addStation();
			sfg.newStationConnections(NEIGHBORS, lastSt(), getNeighborsNumber());
		}
	}

	@Override
	protected void generateSchedules() throws IOException {
		addSchedule();
		int destinations = getDestinationsNumber();
		if (currentNodes < maxNumberOfSchedules - maxNumberOfRepetitiveSchedules) {
			if (!schedulesOfDestinations.containsKey(destinations)) {
				schedulesOfDestinations.put(destinations, new ArrayList<Integer>());
			}
			schedulesOfDestinations.get(destinations).add(lastSch());
			sfg.newStationConnections(DESTINATIONS, lastSch(), destinations);
		} else {
			addRepetitiveScheduleConnections(lastSch(), destinations);
		}

	}

	@Override
	protected boolean addDestination(final int sourceIndex, final int targetIndex) {
		if (super.addDestination(sourceIndex, targetIndex)) {
			sfg.increaseDegree(SCHEDULE, sourceIndex);
			sfg.increaseDegree(STATION, targetIndex);
			return true;
		}
		return false;
	}

	@Override
	protected int getNeighborsNumber() {
		return 0;
	}

	@Override
	public ArrayList<Node> getStations() {
		return stations;
	}

	@Override
	public ArrayList<Node> getSchedules() {
		return schedules;
	}

	@Override
	public void addNewStation() throws IOException {
		addStation();
	}

	@Override
	public boolean addNewNeighbor(final int sourceIndex, final int targetIndex) {
		return addNeighbor(sourceIndex, targetIndex);
	}

	@Override
	public boolean addNewDestination(int sourceIndex, int targetIndex) {
		return addDestination(sourceIndex, targetIndex);
	}

	@Override
	public int getMaxNewNeighbors() {
		return 0;
	}

}
