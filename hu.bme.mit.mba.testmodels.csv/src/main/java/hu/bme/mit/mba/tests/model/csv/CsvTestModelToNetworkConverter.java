package hu.bme.mit.mba.tests.model.csv;

import java.util.HashMap;
import java.util.Map;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.test.TestGraphDatabaseFactory;

import hu.bme.mit.mba.tests.model.ModelContext;
import hu.bme.mit.mba.tests.model.TestModel;

public class CsvTestModelToNetworkConverter {

	private Map<String, Node> nodeMapping;

	public GraphDatabaseService convert(final TestModel testModel) {
		nodeMapping = new HashMap<>();
		GraphDatabaseService graph = new TestGraphDatabaseFactory().newImpermanentDatabase();

		try (Transaction tx = graph.beginTx()) {
			for (final String nodeName : testModel.getAdjacency().rowKeySet()) {
				addNode(nodeMapping, graph, nodeName);

				for (final String neighborName : testModel.getAdjacency().row(nodeName).keySet()) {
					addNode(nodeMapping, graph, neighborName);
				}
			}

			for (final String nodeName : testModel.getAdjacency().rowKeySet()) {
				for (final String neighborName : testModel.getAdjacency().row(nodeName).keySet()) {
					for (final String dimensionName : testModel.getAdjacency().get(nodeName, neighborName)) {
						Node node = nodeMapping.get(nodeName);
						Node neighbor = nodeMapping.get(neighborName);
						node.createRelationshipTo(neighbor, relationship(dimensionName));
					}
				}
			}

			tx.success();
		}
		return graph;
	}

	protected RelationshipType relationship(final String label) {
		return RelationshipType.withName(label);
	}

	protected void addNode(final Map<String, Node> nodeMapping, final GraphDatabaseService graph,
			final String nodeName) {
		if (!nodeMapping.containsKey(nodeName)) {
			final Node node = graph.createNode();
			node.setProperty(ModelContext.name, nodeName);
			nodeMapping.put(nodeName, node);
		}
	}

	public Map<String, Node> getNodeMapping() {
		return nodeMapping;
	}

}
