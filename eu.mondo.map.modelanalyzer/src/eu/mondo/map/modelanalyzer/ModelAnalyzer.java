package eu.mondo.map.modelanalyzer;

import java.io.FileWriter;
import java.io.IOException;

import eu.mondo.map.core.Analyzer;
import eu.mondo.map.core.graph.Network;
import eu.mondo.map.core.metrics.PublishedMetric;
import eu.mondo.map.modelmetrics.composite.ClusteringCoefficientList;
import eu.mondo.map.modelmetrics.composite.DegreeList;
import eu.mondo.map.modelmetrics.composite.DimensionalClusteringCoefficient;
import eu.mondo.map.modelmetrics.composite.MultiplexParticipationCoefficient;
import eu.mondo.map.modelmetrics.composite.NodeInterdependenceList;
import eu.mondo.map.modelmetrics.composite.ShortestPathList;
import eu.mondo.map.modelmetrics.composite.typed.DimensionalTypedClusteringCoefficientList;
import eu.mondo.map.modelmetrics.composite.typed.NodeActivityList;
import eu.mondo.map.modelmetrics.scalar.Density;
import eu.mondo.map.modelmetrics.scalar.NumberOfEdges;
import eu.mondo.map.modelmetrics.scalar.NumberOfNodes;
import eu.mondo.map.modelmetrics.scalar.typed.DimensionActivity;
import eu.mondo.map.modelmetrics.scalar.typed.EdgeDimensionConnectivity;
import eu.mondo.map.modelmetrics.scalar.typed.NodeDimensionConnectivity;
import eu.mondo.map.modelmetrics.scalar.typed.NumberOfTypedEdges;
import eu.mondo.map.modelmetrics.scalar.typed.NumberOfTypedNodes;
import eu.mondo.map.modelmetrics.scalar.typed.PairwiseMultiplexity;

public abstract class ModelAnalyzer<N> extends Analyzer {

	protected String modelName;
	protected Network<N> network;

	// scalar metrics
	protected NumberOfEdges numberOfEdges = new NumberOfEdges();
	protected NumberOfNodes numberOfNodes = new NumberOfNodes();
	protected Density density = new Density();

	// typed scalar metrics
	protected DimensionActivity dimensionActivity = new DimensionActivity();
	protected EdgeDimensionConnectivity edgeDimensionConnectivity = new EdgeDimensionConnectivity();
	protected NodeDimensionConnectivity nodeDimensionConnectivity = new NodeDimensionConnectivity();
	protected NumberOfTypedNodes numberOfTypedNodes = new NumberOfTypedNodes();
	protected NumberOfTypedEdges numberOfTypedEdges = new NumberOfTypedEdges();
	protected PairwiseMultiplexity pairwiseMultiplexity = new PairwiseMultiplexity();

	// composite metrics
	protected ClusteringCoefficientList<N> clusteringCoefficientList = new ClusteringCoefficientList<>();
	protected DegreeList degreeList = new DegreeList();
	protected DimensionalClusteringCoefficient dimensionalClusteringCoefficient = new DimensionalClusteringCoefficient();
	protected MultiplexParticipationCoefficient multiplexParticipationCoefficient = new MultiplexParticipationCoefficient();
	protected NodeInterdependenceList<N> nodeInterdependenceList = new NodeInterdependenceList<>();
	protected ShortestPathList<N> shortestPathList = new ShortestPathList<>();

	// typed composite metrics
	protected DimensionalTypedClusteringCoefficientList<N> dimensionalTypedClusteringCoefficientList = new DimensionalTypedClusteringCoefficientList<>();
	protected NodeActivityList<N> nodeActivityList = new NodeActivityList<>();
	protected int SAMPLE_COUNT = 100;

	// init methods
	protected abstract void initNetwork() throws IOException;

	protected abstract void initModel() throws IOException;

	public void run() throws IOException {
		System.out.println("Calculating metrics for: " + modelName);
		initModel();
		initNetwork();
		initFields();
		calculateMetrics();
		saveMetrics();
	}

	protected void initFields() {
		// scalar metrics
		addMetric(density);
		addMetric(numberOfEdges);
		addMetric(numberOfNodes);

		// typed scalar metrics
		addMetric(dimensionActivity);
		addMetric(edgeDimensionConnectivity);
		addMetric(nodeDimensionConnectivity);
		addMetric(numberOfTypedNodes);
		addMetric(numberOfTypedEdges);
		addMetric(pairwiseMultiplexity);

		// composite metrics
		addMetric(clusteringCoefficientList);
		addMetric(degreeList);
		addMetric(dimensionalClusteringCoefficient);
		addMetric(multiplexParticipationCoefficient);
		addMetric(nodeInterdependenceList);
		addMetric(shortestPathList);

		// typed composite metrics
		addMetric(dimensionalTypedClusteringCoefficientList);
		addMetric(nodeActivityList);

		System.out.println("Initialized fields");
	}

	protected void calculateMetrics() {
		// TODO print if finished
		// scalar metrics
		System.out.println(numberOfNodes.getClass());
		numberOfNodes.calculate(network);

		System.out.println(numberOfEdges.getClass());
		numberOfEdges.calculate(network);

		System.out.println(density.getClass());
		density.calculate(numberOfNodes, numberOfEdges);

		// typed scalar metrics

		System.out.println(dimensionActivity.getClass());
		dimensionActivity.calculate(network);

		System.out.println(edgeDimensionConnectivity.getClass());
		edgeDimensionConnectivity.calculate(network);

		System.out.println(nodeDimensionConnectivity.getClass());
		nodeDimensionConnectivity.calculate(network);

		System.out.println(numberOfTypedEdges.getClass());
		numberOfTypedEdges.calculate(network);

		System.out.println(pairwiseMultiplexity.getClass());
		pairwiseMultiplexity.calculateAllPairs(network, true);

		System.out.println(pairwiseMultiplexity.getClass());
		pairwiseMultiplexity.calculateAllPairsExclusive(network, true);

		// composite metrics
		System.out.println(clusteringCoefficientList.getClass());
		clusteringCoefficientList.calculate(network);

		System.out.println(degreeList.getClass());
		degreeList.calculate(network);

		System.out.println(dimensionalClusteringCoefficient.getClass());
		dimensionalClusteringCoefficient.setName("DimensionalClusteringCoefficientDef1");
		dimensionalClusteringCoefficient.calculateFirstDefinition(network);

		System.out.println(dimensionalClusteringCoefficient.getClass());
		dimensionalClusteringCoefficient.setName("DimensionalClusteringCoefficientDef2");
		dimensionalClusteringCoefficient.calculateSecondDefinition(network);

		System.out.println(multiplexParticipationCoefficient.getClass());
		multiplexParticipationCoefficient.calculate(network);

		// typed composite metrics
		if (SAMPLE_COUNT == 0) {
			System.out.println(shortestPathList.getClass());
			shortestPathList.calculate(network);

			System.out.println(dimensionalTypedClusteringCoefficientList.getClass());
			dimensionalTypedClusteringCoefficientList.calculate(network);

			System.out.println(nodeInterdependenceList.getClass());
			nodeInterdependenceList.calculate(network);
		} else {
			System.out.println(shortestPathList.getClass());
			shortestPathList.calculate(network, SAMPLE_COUNT);

			System.out.println(dimensionalTypedClusteringCoefficientList.getClass());
			dimensionalTypedClusteringCoefficientList.calculate(network, SAMPLE_COUNT);

			System.out.println(nodeInterdependenceList.getClass());
			nodeInterdependenceList.calculate(network, SAMPLE_COUNT);
		}

		System.out.println(nodeActivityList.getClass());
		nodeActivityList.calculate(network);

		System.out.println("Calculated metrics");
	}

	protected static final String NEWLINE = "\n";
	protected static final String DELIMITER = "\t";
	protected static final String HEADER = "Category" + DELIMITER + "Instance" + DELIMITER + "Index" + DELIMITER + "Value";

	protected void saveMetrics() throws IOException {

		Object samplePostfix = SAMPLE_COUNT == 0 ? "" : "-" + SAMPLE_COUNT;
		try (FileWriter fileWriter = new FileWriter(modelName + samplePostfix + ".tsv");) {
			fileWriter.append(HEADER);
			fileWriter.append(NEWLINE);
			for (PublishedMetric metric : resolve()) {
				fileWriter.append(metric.getCategory());
				fileWriter.append(DELIMITER);
				fileWriter.append(metric.getInstanceString());
				fileWriter.append(DELIMITER);
				fileWriter.append(metric.getIndexString());
				fileWriter.append(DELIMITER);
				fileWriter.append(metric.getValue().toString());
				fileWriter.append(NEWLINE);
			}
			fileWriter.flush();
		}
		System.out.println("Saved results");
	}
}
