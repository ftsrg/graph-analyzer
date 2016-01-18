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

package eu.mondo.map.analysis;

import java.util.ArrayList;

import eu.mondo.map.analysis.metrics.models.ModelMetric;
import eu.mondo.sam.core.metrics.BenchmarkMetric;

public abstract class Analyzer {

	protected ArrayList<BenchmarkMetric> metrics;

	public void calculateAll() {
		calculateMetrics();
		for (BenchmarkMetric m : metrics) {
			((ModelMetric) m).calculate();
		}
	}

	protected abstract void calculateMetrics();

	public abstract void initializeMetrics();

	public abstract void resetMetrics();

	public ArrayList<BenchmarkMetric> getMetrics() {
		return metrics;
	}

	public void setMetrics(ArrayList<BenchmarkMetric> metrics) {
		this.metrics = metrics;
	}

}
