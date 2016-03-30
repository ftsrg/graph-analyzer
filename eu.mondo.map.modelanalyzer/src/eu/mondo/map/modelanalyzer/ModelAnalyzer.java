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
import eu.mondo.map.modelmetrics.composite.typed.DimensionalDegreeList;
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
	protected NodeDimensionConnectivity nodeExclusiveDimensionConnectivity = new NodeDimensionConnectivity();
	protected NumberOfTypedNodes numberOfTypedNodes = new NumberOfTypedNodes();
	protected NumberOfTypedEdges numberOfTypedEdges = new NumberOfTypedEdges();
	protected PairwiseMultiplexity pairwiseMultiplexity = new PairwiseMultiplexity();

	// composite metrics
	protected ClusteringCoefficientList<N> clusteringCoefficientList = new ClusteringCoefficientList<>();
	protected DegreeList<N> degreeList = new DegreeList<>();
	protected DimensionalClusteringCoefficient dimensionalClusteringCoefficientDef1 = new DimensionalClusteringCoefficient();
	protected DimensionalClusteringCoefficient dimensionalClusteringCoefficientDef2 = new DimensionalClusteringCoefficient();
	protected MultiplexParticipationCoefficient multiplexParticipationCoefficient = new MultiplexParticipationCoefficient();
	protected NodeInterdependenceList<N> nodeInterdependenceList = new NodeInterdependenceList<>();
	protected ShortestPathList<N> shortestPathList = new ShortestPathList<>();

	// typed composite metrics
	protected DimensionalTypedClusteringCoefficientList<N> dimensionalTypedClusteringCoefficientList = new DimensionalTypedClusteringCoefficientList<>();
	protected NodeActivityList<N> nodeActivityList = new NodeActivityList<>();
	protected DimensionalDegreeList<N> dimensionalDegreeList = new DimensionalDegreeList<>();
	protected final int sampleSize;

	// init methods
	protected abstract void initNetwork() throws IOException;

	protected abstract void initModel() throws IOException;

	public ModelAnalyzer(final int sampleSize) {
		this.sampleSize = sampleSize;
	}

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
		addMetric(nodeExclusiveDimensionConnectivity);
		addMetric(numberOfTypedNodes);
		addMetric(numberOfTypedEdges);
		addMetric(pairwiseMultiplexity);

		// composite metrics
		addMetric(clusteringCoefficientList);
		addMetric(degreeList);
		addMetric(dimensionalClusteringCoefficientDef1);
		addMetric(dimensionalClusteringCoefficientDef2);
		addMetric(multiplexParticipationCoefficient);
		addMetric(nodeInterdependenceList);
		addMetric(shortestPathList);

		// typed composite metrics
		addMetric(dimensionalDegreeList);
		addMetric(dimensionalTypedClusteringCoefficientList);
		addMetric(nodeActivityList);

		System.out.println("Initialized fields");
	}

	protected void calculateMetrics() {
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

		nodeExclusiveDimensionConnectivity.setName("NodeExclusiveDimensionConnectivity");
		System.out.println(nodeExclusiveDimensionConnectivity.getClass());
		nodeExclusiveDimensionConnectivity.calculate(network);

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

		System.out.println(dimensionalClusteringCoefficientDef1.getClass());
		dimensionalClusteringCoefficientDef1.setName("DimensionalClusteringCoefficientDef1");
		dimensionalClusteringCoefficientDef1.calculateFirstDefinition(network);

		System.out.println(dimensionalClusteringCoefficientDef2.getClass());
		dimensionalClusteringCoefficientDef2.setName("DimensionalClusteringCoefficientDef2");
		dimensionalClusteringCoefficientDef2.calculateSecondDefinition(network);

		System.out.println(multiplexParticipationCoefficient.getClass());
		multiplexParticipationCoefficient.calculate(network);

		if (sampleSize == 0) {
//			System.out.println(shortestPathList.getClass());
//			shortestPathList.calculate(network);

			System.out.println(nodeInterdependenceList.getClass());
			nodeInterdependenceList.calculate(network);
		} else {
//			System.out.println(shortestPathList.getClass());
//			shortestPathList.calculate(network, sampleSize);

			System.out.println(nodeInterdependenceList.getClass());
			nodeInterdependenceList.calculate(network, sampleSize);
		}

		// typed composite metrics
		System.out.println(dimensionalDegreeList.getClass());
		dimensionalDegreeList.calculate(network);

		if (sampleSize == 0) {
			System.out.println(dimensionalTypedClusteringCoefficientList.getClass());
			dimensionalTypedClusteringCoefficientList.calculate(network);
		} else {
			System.out.println(dimensionalTypedClusteringCoefficientList.getClass());
			dimensionalTypedClusteringCoefficientList.calculate(network, sampleSize);
		}

		System.out.println(nodeActivityList.getClass());
		nodeActivityList.calculate(network);

		System.out.println("Calculated metrics");
	}

	protected static final String NEWLINE = "\n";
	protected static final String DELIMITER = "\t";
	protected static final String HEADER = "Category" + DELIMITER + "Instance" + DELIMITER + "Index" + DELIMITER + "Value";

	protected void saveMetrics() throws IOException {

		Object samplePostfix = sampleSize == 0 ? "" : "-" + sampleSize;
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
