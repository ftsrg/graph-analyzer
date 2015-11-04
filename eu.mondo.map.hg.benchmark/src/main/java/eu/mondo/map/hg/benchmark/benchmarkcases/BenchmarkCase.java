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

package eu.mondo.map.hg.benchmark.benchmarkcases;

import java.util.Collection;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import eu.mondo.map.analysis.Analyzer;
import eu.mondo.map.hg.benchmark.benchmarkcases.layers.AnalyzedBenchmarkCase;
import eu.mondo.map.hg.benchmark.benchmarkcases.layers.DescribedBenchmarkCase;
import eu.mondo.map.hg.benchmark.benchmarkcases.layers.VersatileBenchmarkCase;
import eu.mondo.map.hg.benchmark.checker.Checker;
import eu.mondo.map.hg.benchmark.config.BenchmarkConfig;
import eu.mondo.map.hg.benchmark.driver.Driver;
import eu.mondo.map.hg.benchmark.publisher.TrainBenchmarkCaseDescriptor;
import eu.mondo.map.hg.benchmark.queries.QueryInitializer;
import eu.mondo.map.hg.benchmark.task.EvaluationTask;
import eu.mondo.map.hg.benchmark.util.UniqueRandom;
import eu.mondo.map.hg.constants.Query;
import eu.mondo.map.hg.constants.TrainBenchmarkConstants;
import eu.mondo.sam.core.metrics.BenchmarkMetric;
import eu.mondo.sam.core.metrics.ScalarMetric;
import eu.mondo.sam.core.metrics.TimeMetric;
import eu.mondo.sam.core.results.PhaseResult;

public abstract class BenchmarkCase<M, T, D extends Driver<T>> {

	protected Random random = new UniqueRandom(TrainBenchmarkConstants.RANDOM_SEED);
	protected BenchmarkConfig benchmarkConfig;
	protected Driver<T> driver;
	protected Checker<M> checker;
	protected Collection<M> matches;
//	protected TransformationLogic<M, T, ?> transformationLogic;
//	protected Transformation<?> transformation;
	protected Analyzer modelAnalyzer;
	protected Analyzer queryAnalyzer;
	protected QueryInitializer queryInitializer;

	public Collection<M> getMatches() {
		return matches;
	}

	// shorthands
	public Query getQuery() {
		return benchmarkConfig.getQuery();
	}

	// these should be implemented for each tool

	protected abstract void init() throws Exception;

	protected void destroy() throws Exception {
		if (checker != null) {
			checker.destroy();
		}
		if (driver != null) {
			driver.destroy();
		}
	}

	public void benchmarkInit(final BenchmarkConfig bc) throws Exception {
		this.benchmarkConfig = bc;
		init();
	}

	public void benchmarkInitAnalyzer() {
		if (this instanceof AnalyzedBenchmarkCase) {
			((AnalyzedBenchmarkCase) this).initAnalyzer();
		}
	}

	public void benchmarkInitDescription() {
		if (this instanceof DescribedBenchmarkCase) {
			((DescribedBenchmarkCase) this).initDescription();
		}
	}

	public void benchmarkInitMetrics() {
		modelAnalyzer.initializeMetrics();
		if (queryAnalyzer != null) {
			queryAnalyzer.initializeMetrics();
		}
	};

	public void benchmarkInitQuery() throws Exception {
		if (this instanceof VersatileBenchmarkCase) {
			if (queryInitializer == null) {
				queryInitializer = new QueryInitializer(benchmarkConfig.getQuery(),
						benchmarkConfig.getSize());
			}
			((VersatileBenchmarkCase) this).modify();
		}
	}

	public void calculateModelMetrics(final PhaseResult phaseResult,
			final TrainBenchmarkCaseDescriptor descriptor) {
		if (this instanceof AnalyzedBenchmarkCase || this instanceof DescribedBenchmarkCase) {
			TimeMetric timer = new TimeMetric("CalculationTime");
			modelAnalyzer.resetMetrics();

			timer.startMeasure();
			modelAnalyzer.calculateAll();
			timer.stopMeasure();

			phaseResult.addMetrics(timer);
			for (BenchmarkMetric m : modelAnalyzer.getMetrics()) {
				phaseResult.addMetrics(m);
			}
		}
	}

	public void calculateQueryMetrics(final PhaseResult phaseResult,
			final TrainBenchmarkCaseDescriptor descriptor) {
		if (this instanceof AnalyzedBenchmarkCase) {
			queryAnalyzer.resetMetrics();
			queryAnalyzer.calculateAll();
			for (BenchmarkMetric m : queryAnalyzer.getMetrics()) {
				phaseResult.addMetrics(m);
			}
		}
	}

//	public void benchmarkInitTransformation() {
//		transformationLogic = TransformationLogic.newInstance(benchmarkConfig.getScenario(),
//				getComparator());
//		if (transformationLogic != null) {
//			transformationLogic.initialize(benchmarkConfig, driver, random);
//		}
//		transformationLogic.setTransformation(transformation);
//	}

	// benchmark methods

	public void benchmarkRead(final PhaseResult phaseResult) throws Exception {
		final TimeMetric timer = new TimeMetric("Time");
		timer.startMeasure();
		driver.read(benchmarkConfig.getModelPathNameWithoutExtension());
		timer.stopMeasure();
		phaseResult.addMetrics(timer);
	}

	public void benchmarkCheck(final PhaseResult phaseResult) throws Exception {
		final TimeMetric timer = new TimeMetric("Time");
		final ScalarMetric results = new ScalarMetric("Matches");

		ExecutorService executor = Executors.newSingleThreadExecutor();
		EvaluationTask<M> task = new EvaluationTask<M>(checker);
		Future<Collection<M>> future = executor.submit(task);
		timer.startMeasure();
		try {
			matches = future.get(300, TimeUnit.SECONDS);
		} catch (InterruptedException | TimeoutException e) {
			future.cancel(true);
			System.out.println("Timeout");
		} finally {
			executor.shutdown();
		}
		timer.stopMeasure();
		if (matches != null) {
			results.setValue(matches.size());
		}
		phaseResult.addMetrics(timer, results);
	}

	public void benchmarkProcessMatches(final PhaseResult phaseResult) {
		for (Entry<String, Object> entry : checker.processMatches(matches).entrySet()) {
			ScalarMetric matchResult = new ScalarMetric(entry.getKey());
			matchResult.setValue((long) entry.getValue());
			phaseResult.addMetrics(matchResult);
		}
	}

	public void benchmarkDestroy() throws Exception {
		destroy();
	}

//	public void benchmarkModify(final PhaseResult phaseResult) throws Exception {
//		transformationLogic.performTransformation(phaseResult, matches);
//	}

//	protected final Comparator<?> getComparator() {
//		switch (benchmarkConfig.getScenario()) {
//		case BATCH:
//		case INJECT:
//			return driver.getElementComparator();
//		case REPAIR:
//			return getMatchComparator();
//		default:
//			throw new UnsupportedOperationException();
//		}
//	}

//	protected abstract Comparator<?> getMatchComparator();

	public QueryInitializer getQueryInitializer() {
		if (queryInitializer == null) {
			queryInitializer = new QueryInitializer(benchmarkConfig.getQuery(),
					benchmarkConfig.getSize());
		}
		return queryInitializer;
	}

	public void setQueryInitializer(QueryInitializer queryInitializer) {
		this.queryInitializer = queryInitializer;
	}

	public BenchmarkConfig getBenchmarkConfig() {
		return benchmarkConfig;
	}

	public void setBenchmarkConfig(BenchmarkConfig benchmarkConfig) {
		this.benchmarkConfig = benchmarkConfig;
	}

}
