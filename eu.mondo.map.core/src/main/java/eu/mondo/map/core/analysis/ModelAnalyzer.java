package eu.mondo.map.core.analysis;

import eu.mondo.map.core.analysis.metrics.models.ModelMetric;
import eu.mondo.sam.core.metrics.BenchmarkMetric;

public abstract class ModelAnalyzer extends Analyzer {

//	protected DensityMetric densityMetric;
//	protected MaximumDegreeMetric maximumDegreeMetric;
//	protected NumberOfNodesMetric nodesMetric;
//	protected NumberOfEdgesMetric edgesMetric;
//	protected ClusteringCoefficientMetric clusteringCoefficientMetric;
//	protected DegreesMetric averageDegreeMetric;
//	protected ShortestPathMetric shortestPathMetric;
//	protected CentralityMetric betweennessMetric;

	@Override
	public void initializeMetrics() {
//		if (metrics == null) {
//			metrics = new ArrayList<BenchmarkMetric>();
//		}
//		nodesMetric = new NumberOfNodesMetric();
//		metrics.add(nodesMetric);
//		edgesMetric = new NumberOfEdgesMetric();
//		metrics.add(edgesMetric);
//		averageDegreeMetric = new AverageDegreeMetric(BOTH);
//		metrics.add(averageDegreeMetric);
//		maximumDegreeMetric = new MaximumDegreeMetric(BOTH);
//		metrics.add(maximumDegreeMetric);
//		averageDegreeMetric = new AverageDegreeMetric(OUTGOING);
//		metrics.add(averageDegreeMetric);
//		densityMetric = new DensityMetric(BOTH);
//		metrics.add(densityMetric);
//		clusteringCoefficientMetric = new AverageClusteringCoefficientMetric();
//		metrics.add(clusteringCoefficientMetric);
//		shortestPathMetric = new AverageShortestPathMetric();
//		metrics.add(shortestPathMetric);
//		betweennessMetric = new BetweennessMetric();
//		metrics.add(betweennessMetric);
//		for (BenchmarkMetric m : metrics) {
//			((ModelMetric) m).initName();
//		}
	}

	@Override
	public void resetMetrics() {
		for (BenchmarkMetric m : metrics) {
			((ModelMetric) m).clear();
		}

	}
}
