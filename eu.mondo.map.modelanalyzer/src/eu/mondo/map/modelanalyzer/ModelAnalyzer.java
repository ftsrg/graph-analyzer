package eu.mondo.map.modelanalyzer;

import static com.google.common.base.Preconditions.checkState;

import org.apache.log4j.Logger;

import eu.mondo.map.core.Analyzer;
import eu.mondo.map.core.metrics.Metric;
import eu.mondo.map.core.metrics.SummaryMetric;
import eu.mondo.map.modelmetrics.ModelMetrics;

public class ModelAnalyzer extends Analyzer<String, Metric> {

	// public ModelAnalyzer(final String modelName, final int sampleSize) {
	// this.modelName = modelName;
	// this.sampleSize = sampleSize;
	// }

	private final Logger logger = Logger.getLogger(this.getClass());

	/**
	 * <p>
	 * Adds the parameter metric (see {@link ModelMetrics}) to the metrics
	 * collection which will be used in the analysis.
	 * </p>
	 * 
	 * @param metric
	 *            represents the metric
	 * 
	 * @return this
	 */
	public ModelAnalyzer use(ModelMetrics metric) {
		useMetric(metric, null);
		return this;
	}

	/**
	 * <p>
	 * Adds the parameter metric (see {@link ModelMetrics}) to the
	 * <em>metrics</em> collection which will be used in the analysis. The
	 * metric is identified by the given name parameter.
	 * </p>
	 * 
	 * @param metric
	 *            {@link ModelMetrics}
	 * 
	 * @param name
	 *            String, represents the name of the metric
	 * 
	 * @return this
	 */
	public ModelAnalyzer use(ModelMetrics metric, String name) {
		useMetric(metric, name);
		return this;
	}

	/**
	 * <p>
	 * Adds the parameter metric (see {@link Metric}) to the <em>metrics</em>
	 * collection which will be used in the analysis.
	 * </p>
	 * 
	 * @param metric
	 *            {@link Metric} object
	 * @return this
	 */
	public ModelAnalyzer use(Metric metric) {
		metrics.put(metric.getName(), metric);
		return this;
	}

	/**
	 * <p>
	 * Adds the parameter metric (see {@link Metric}) to the <em>metrics</em>
	 * collection which will be used in the analysis. The metric is identified
	 * by the given name parameter.
	 * </p>
	 * 
	 * @param metric
	 *            {@link Metric} object
	 * @param name
	 *            String, represents the name of the metric
	 * @return this
	 */
	public ModelAnalyzer use(Metric metric, String name) {
		metric.setName(name);
		metrics.put(metric.getName(), metric);
		return this;
	}

	protected void useMetric(ModelMetrics metric, String name) {
		Metric newMetric = metric.instantiate();
		if (name != null) {
			newMetric.setName(name);
		}
		metrics.put(metric.toString(), newMetric);
		logger.info("Use metric for the analysis: " + metric.toString());
	}

	/**
	 * <p>
	 * Adds all of the metrics that are in {@link ModelMetrics}. It must be
	 * guaranteed that other metric was not added before and the
	 * <em>metrics</em> collection is empty.
	 * </p>
	 * 
	 * @throws IllegalStateException
	 *             if at least one metric was added before
	 * 
	 * @return
	 */
	public ModelAnalyzer useAll() {
		checkState(!metrics.isEmpty(),
				"The ModelAnalyzer already contains metrics, so the useAll function cannot be used.");
		for (ModelMetrics m : ModelMetrics.values()) {
			useMetric(m, null);
		}
		return this;
	}

	public ModelAnalyzer useSummary(SummaryMetric<?, ?> summary) {
		// checkArgument(metrics.containsKey(metric.toString()),
		// "The " + metric.toString() + " should be contained by the
		// ModelAnalyzer");
		// TODO
		return this;
	}

	/**
	 * <p>
	 * Removes the metric parameter (see {@link ModelMetrics}) from the
	 * <em>metrics</em> collection. As a result, the metric won't be calculated
	 * during the analysis.
	 * </p>
	 * 
	 * @param metric
	 *            {@link ModelMetrics} value
	 * 
	 * @throws IllegalStateException
	 *             if the <em>metrics</em> collection is empty
	 * @return this
	 */
	public ModelAnalyzer omit(ModelMetrics metric) {
		checkState(metrics.isEmpty(), "The ModelAnalyzer should contain metrics to omit " + metric.toString()
				+ " from the analysis, but it is empty.");
		if (!metrics.containsKey(metric.toString())) {
			logger.warn("The ModelAnalyzer does not contain " + metric.toString() + " which is planned to be omitted.");
		} else {
			metrics.remove(metric.toString());
			logger.info("Omit the metric from the analysis: " + metric.toString());
		}
		return this;
	}

	/**
	 * Returns a {@link Metric} object which is mapped to the
	 * {@link ModelMetrics} parameter and contained by the ModelAnalyzer. If
	 * there is not any {@link Metric} object, returns <strong>null</strong>.
	 * 
	 * @param metric
	 *            {@link ModelMetrics} value
	 * 
	 * @return {@link Metric} object or <strong>null</strong>
	 */
	public Metric getMetric(ModelMetrics metric) {
		return metrics.get(metric.toString());
	}

	// protected final int sampleSize;
	// protected final String modelName;
	// protected Network<N> network;
	//
	// // scalar metrics
	// protected NumberOfEdges numberOfEdges = new NumberOfEdges();
	// protected NumberOfNodes numberOfNodes = new NumberOfNodes();
	// protected Density density = new Density();
	//
	// // typed scalar metrics
	// protected DimensionActivity dimensionActivity = new DimensionActivity();
	// protected EdgeDimensionConnectivity edgeDimensionConnectivity = new
	// EdgeDimensionConnectivity();
	// protected NodeDimensionConnectivity nodeDimensionConnectivity = new
	// NodeDimensionConnectivity();
	// protected NodeDimensionConnectivity nodeExclusiveDimensionConnectivity =
	// new NodeDimensionConnectivity();
	// protected NumberOfTypedNodes numberOfTypedNodes = new
	// NumberOfTypedNodes();
	// protected NumberOfTypedEdges numberOfTypedEdges = new
	// NumberOfTypedEdges();
	// protected PairwiseMultiplexity pairwiseMultiplexity = new
	// PairwiseMultiplexity();
	//
	// // composite metrics
	// protected ClusteringCoefficientList<N> clusteringCoefficientList = new
	// ClusteringCoefficientList<>();
	// protected DegreeList<N> degreeList = new DegreeList<>();
	// protected DimensionalClusteringCoefficient
	// dimensionalClusteringCoefficientDef1 = new
	// DimensionalClusteringCoefficient();
	// protected DimensionalClusteringCoefficient
	// dimensionalClusteringCoefficientDef2 = new
	// DimensionalClusteringCoefficient();
	// protected DimensionalClusteringCoefficient
	// dimensionalClusteringCoefficientDef3 = new
	// DimensionalClusteringCoefficient();
	// protected MultiplexParticipationCoefficient
	// multiplexParticipationCoefficient = new
	// MultiplexParticipationCoefficient();
	// protected NodeInterdependenceList<N> nodeInterdependenceList = new
	// NodeInterdependenceList<>();
	// protected ShortestPathList<N> shortestPathList = new
	// ShortestPathList<>();
	// protected NeighborhoodList<N> neighborhoods = new NeighborhoodList<>();
	//
	// // typed composite metrics
	// protected DimensionalTypedClusteringCoefficientList<N>
	// dimensionalTypedClusteringCoefficientList = new
	// DimensionalTypedClusteringCoefficientList<>();
	// protected NodeActivityList<N> nodeActivityList = new
	// NodeActivityList<>();
	// protected DimensionalDegreeList<N> dimensionalDegreeList = new
	// DimensionalDegreeList<>();
	//
	// // init methods
	// protected abstract void initNetwork() throws Exception;
	//
	// protected abstract void initModel() throws Exception;
	//
	// public void run() throws Exception {
	// System.out.println("Calculating metrics for: " + modelName);
	// initModel();
	// initNetwork();
	// initFields();
	// calculateMetrics();
	// saveMetrics();
	// }
	//
	// protected void initFields() {
	// // scalar metrics
	// addMetric(density);
	// addMetric(numberOfEdges);
	// addMetric(numberOfNodes);
	//
	// // typed scalar metrics
	// addMetric(dimensionActivity);
	// addMetric(edgeDimensionConnectivity);
	// addMetric(nodeDimensionConnectivity);
	// addMetric(nodeExclusiveDimensionConnectivity);
	// addMetric(numberOfTypedNodes);
	// addMetric(numberOfTypedEdges);
	// addMetric(pairwiseMultiplexity);
	//
	// // composite metrics
	// addMetric(clusteringCoefficientList);
	// addMetric(degreeList);
	// addMetric(dimensionalClusteringCoefficientDef1);
	// addMetric(dimensionalClusteringCoefficientDef2);
	// addMetric(dimensionalClusteringCoefficientDef3);
	// addMetric(multiplexParticipationCoefficient);
	// addMetric(nodeInterdependenceList);
	// addMetric(shortestPathList);
	// addMetric(neighborhoods);
	//
	// // typed composite metrics
	// addMetric(dimensionalDegreeList);
	// addMetric(dimensionalTypedClusteringCoefficientList);
	// addMetric(nodeActivityList);
	//
	// System.out.println("Initialized fields");
	// }
	//
	// protected void calculateMetrics() {
	// // scalar metrics
	// System.out.println(numberOfNodes.getClass());
	// numberOfNodes.calculate(network);
	//
	// System.out.println(numberOfEdges.getClass());
	// numberOfEdges.calculate(network);
	//
	// System.out.println(density.getClass());
	// density.calculate(numberOfNodes, numberOfEdges);
	//
	// // typed scalar metrics
	//
	// System.out.println(dimensionActivity.getClass());
	// dimensionActivity.calculate(network);
	//
	// System.out.println(edgeDimensionConnectivity.getClass());
	// edgeDimensionConnectivity.calculate(network);
	//
	// System.out.println(nodeDimensionConnectivity.getClass());
	// nodeDimensionConnectivity.calculate(network);
	//
	// nodeExclusiveDimensionConnectivity.setName("NodeExclusiveDimensionConnectivity");
	// System.out.println(nodeExclusiveDimensionConnectivity.getClass());
	// nodeExclusiveDimensionConnectivity.calculate(network);
	//
	// System.out.println(numberOfTypedEdges.getClass());
	// numberOfTypedEdges.calculate(network);
	//
	// System.out.println(pairwiseMultiplexity.getClass());
	// pairwiseMultiplexity.calculateAllPairs(network, true);
	//
	// System.out.println(pairwiseMultiplexity.getClass());
	// pairwiseMultiplexity.calculateAllPairsExclusive(network, true);
	//
	// // composite metrics
	// System.out.println(clusteringCoefficientList.getClass());
	// clusteringCoefficientList.setUseHeuristic(true);
	// clusteringCoefficientList.calculate(network);
	//
	// System.out.println(degreeList.getClass());
	// degreeList.calculate(network);
	//
	// System.out.println(dimensionalClusteringCoefficientDef1.getClass());
	// dimensionalClusteringCoefficientDef1.setName("DimensionalClusteringCoefficientDef1");
	// dimensionalClusteringCoefficientDef1.setUseHeuristic(true);
	// dimensionalClusteringCoefficientDef1.calculateFirstDefinition(network);
	//
	// System.out.println(dimensionalClusteringCoefficientDef2.getClass());
	// dimensionalClusteringCoefficientDef2.setName("DimensionalClusteringCoefficientDef2");
	// dimensionalClusteringCoefficientDef2.setUseHeuristic(true);
	// dimensionalClusteringCoefficientDef2.calculateSecondDefinition(network);
	//
	// System.out.println(dimensionalClusteringCoefficientDef3.getClass());
	// dimensionalClusteringCoefficientDef3.setName("DimensionalClusteringCoefficientDef3");
	// dimensionalClusteringCoefficientDef3.setUseHeuristic(true);
	// dimensionalClusteringCoefficientDef3.calculateSecondDefinition(network);
	//
	// System.out.println(multiplexParticipationCoefficient.getClass());
	// multiplexParticipationCoefficient.calculate(network);
	//
	// System.out.println(neighborhoods.getClass());
	// neighborhoods.calculate(network);
	//
	// // if (sampleSize == 0) {
	// // System.out.println(shortestPathList.getClass());
	// // shortestPathList.calculate(network);
	// //
	// // System.out.println(nodeInterdependenceList.getClass());
	// // nodeInterdependenceList.calculate(network);
	// // } else {
	// // System.out.println(shortestPathList.getClass());
	// // shortestPathList.calculate(network, sampleSize);
	// //
	// // System.out.println(nodeInterdependenceList.getClass());
	// // nodeInterdependenceList.calculate(network, sampleSize);
	// // }
	//
	// // typed composite metrics
	// System.out.println(dimensionalDegreeList.getClass());
	// dimensionalDegreeList.calculate(network);
	//
	// if (sampleSize == 0) {
	// System.out.println(dimensionalTypedClusteringCoefficientList.getClass());
	// dimensionalTypedClusteringCoefficientList.calculate(network);
	// } else {
	// System.out.println(dimensionalTypedClusteringCoefficientList.getClass());
	// dimensionalTypedClusteringCoefficientList.calculate(network, sampleSize);
	// }
	//
	// System.out.println(nodeActivityList.getClass());
	// nodeActivityList.calculate(network);
	//
	// System.out.println("Calculated metrics");
	// }
	//
	// protected static final String NEWLINE = "\n";
	// protected static final String DELIMITER = "\t";
	// protected static final String HEADER = "Category" + DELIMITER +
	// "Instance" + DELIMITER + "Index" + DELIMITER
	// + "Value";
	//
	// protected void saveMetrics() throws IOException {
	// final Object samplePostfix = sampleSize == 0 ? "" : "-" + sampleSize;
	// try (final FileWriter fileWriter = new FileWriter(modelName +
	// samplePostfix + getPostfix() + ".tsv");) {
	// fileWriter.append(HEADER);
	// fileWriter.append(NEWLINE);
	// for (final PublishedMetric metric : resolve()) {
	// fileWriter.append(metric.getCategory());
	// fileWriter.append(DELIMITER);
	// fileWriter.append(metric.getInstanceString());
	// fileWriter.append(DELIMITER);
	// fileWriter.append(metric.getIndexString());
	// fileWriter.append(DELIMITER);
	// fileWriter.append(metric.getValue().toString());
	// fileWriter.append(NEWLINE);
	// }
	// fileWriter.flush();
	// }
	// System.out.println("Saved results");
	// }
	//
	// protected String getPostfix() {
	// return "";
	// }
}
