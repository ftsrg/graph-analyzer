package eu.mondo.map.analysis;

import static eu.mondo.map.constants.EdgeDirection.BOTH;
import static eu.mondo.map.constants.EdgeDirection.OUTGOING;

import java.util.ArrayList;

import eu.mondo.map.analysis.metrics.models.AverageClusteringCoefficientMetric;
import eu.mondo.map.analysis.metrics.models.AverageDegreeMetric;
import eu.mondo.map.analysis.metrics.models.AverageShortestPathMetric;
import eu.mondo.map.analysis.metrics.models.BetweennessMetric;
import eu.mondo.map.analysis.metrics.models.DensityMetric;
import eu.mondo.map.analysis.metrics.models.MaximumDegreeMetric;
import eu.mondo.map.analysis.metrics.models.ModelMetric;
import eu.mondo.map.analysis.metrics.models.NumberOfEdgesMetric;
import eu.mondo.map.analysis.metrics.models.NumberOfNodesMetric;
import eu.mondo.sam.core.metrics.BenchmarkMetric;

public abstract class ModelAnalyzer extends Analyzer {

	protected DensityMetric densityMetric;
	protected MaximumDegreeMetric maximumDegreeMetric;
	protected NumberOfNodesMetric nodesMetric;
	protected NumberOfEdgesMetric edgesMetric;
	protected AverageClusteringCoefficientMetric clusteringCoefficientMetric;
	protected AverageDegreeMetric averageDegreeMetric;
	protected AverageShortestPathMetric shortestPathMetric;
	protected BetweennessMetric betweennessMetric;

	@Override
	public void initializeMetrics() {
		if (metrics == null) {
			metrics = new ArrayList<BenchmarkMetric>();
		}
		nodesMetric = new NumberOfNodesMetric();
		metrics.add(nodesMetric);
		edgesMetric = new NumberOfEdgesMetric();
		metrics.add(edgesMetric);
		averageDegreeMetric = new AverageDegreeMetric(BOTH);
		metrics.add(averageDegreeMetric);
		maximumDegreeMetric = new MaximumDegreeMetric(BOTH);
		metrics.add(maximumDegreeMetric);
		averageDegreeMetric = new AverageDegreeMetric(OUTGOING);
		metrics.add(averageDegreeMetric);
		densityMetric = new DensityMetric(BOTH);
		metrics.add(densityMetric);
		clusteringCoefficientMetric = new AverageClusteringCoefficientMetric();
		metrics.add(clusteringCoefficientMetric);
		shortestPathMetric = new AverageShortestPathMetric();
		metrics.add(shortestPathMetric);
		betweennessMetric = new BetweennessMetric();
		metrics.add(betweennessMetric);
		for (BenchmarkMetric m : metrics) {
			((ModelMetric) m).initName();
		}
	}

	@Override
	public void resetMetrics() {
		for (BenchmarkMetric m : metrics) {
			((ModelMetric) m).clear();
		}

	}
}
