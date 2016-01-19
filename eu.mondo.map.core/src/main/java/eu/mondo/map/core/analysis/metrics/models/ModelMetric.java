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

package eu.mondo.map.core.analysis.metrics.models;

import eu.mondo.map.core.constants.EdgeDirection;
import eu.mondo.sam.core.metrics.BenchmarkMetric;

public abstract class ModelMetric extends BenchmarkMetric {

	protected double metricValue;

	protected EdgeDirection direction;

	protected boolean withOutgoingDegree;

	public ModelMetric(EdgeDirection direction) {
		this.direction = direction;
		withOutgoingDegree = (direction == EdgeDirection.OUTGOING);
	}

	public void calculate() {

	};

//	public void loadValue(final JsonNode root) {
//		List<JsonNode> nodes = root.get("PhaseResults").findValues("Metrics");
//		for (JsonNode metrics : nodes) {
//			for (JsonNode metric : metrics) {
//				if (metric.get("MetricName").asText().equals(getIdentifier())) {
//					metricValue = metric.get("MetricValue").asDouble();
//				}
//
//			}
//		}
//
//	}

	public void clear() {
		metricValue = 0;
	}

	protected abstract String getIdentifier();

	@Override
	public String getValue() {
		return Double.toString(metricValue);
	}

}
