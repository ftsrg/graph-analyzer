package hu.bme.mit.ga.tests.graph.neo4j;

import hu.bme.mit.ga.tests.graph.TestGraph;
import hu.bme.mit.ga.tests.graph.TestGraphToConcreteFormatConverter;
import hu.bme.mit.ga.tests.graph.TestGraphConstants;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.test.TestGraphDatabaseFactory;

import java.util.HashMap;
import java.util.Map;

public class TestGraphToNeo4jConverter extends TestGraphToConcreteFormatConverter<Node, GraphDatabaseService> {

    public GraphDatabaseService convert(final TestGraph testGraph) {
        nodeMapping = new HashMap<>();
        GraphDatabaseService graph = new TestGraphDatabaseFactory().newImpermanentDatabase();

        try (Transaction tx = graph.beginTx()) {
            for (final String nodeName : testGraph.getAdjacency().rowKeySet()) {
                addNode(nodeMapping, graph, nodeName);

                for (final String neighborName : testGraph.getAdjacency().row(nodeName).keySet()) {
                    addNode(nodeMapping, graph, neighborName);
                }
            }

            for (final String nodeName : testGraph.getAdjacency().rowKeySet()) {
                for (final String neighborName : testGraph.getAdjacency().row(nodeName).keySet()) {
                    for (final String dimensionName : testGraph.getAdjacency().get(nodeName, neighborName)) {
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
            node.setProperty(TestGraphConstants.name, nodeName);
            nodeMapping.put(nodeName, node);
        }
    }

}
