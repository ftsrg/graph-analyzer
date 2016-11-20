package hu.bme.mit.mba.modeladapters.neo4j;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.ResourceIterable;
import org.neo4j.graphdb.Transaction;

import hu.bme.mit.mba.modeladapters.EdgeOperation;
import hu.bme.mit.mba.modeladapters.ModelIndexBuilder;
import hu.bme.mit.mba.modeladapters.ModelProvider;

public class Neo4jModelProvider implements ModelProvider<Node, String> {

    private GraphDatabaseService graph;
    private ModelIndexBuilder builder;

    public Neo4jModelProvider(ModelIndexBuilder builder) {
        this.builder = builder;
    }

    public void init(GraphDatabaseService graph) {
        this.graph = graph;
        try (Transaction tx = graph.beginTx()) {
            init(graph.getAllNodes());
        }
    }

    protected void init(ResourceIterable<Node> nodes) {
        for (Node node : nodes) {
            for (Relationship relationship : node.getRelationships(Direction.OUTGOING)) {
                Node neighbor = relationship.getOtherNode(node);
                addEdge(node, relationship.getType(), neighbor);
            }
        }
    }

    protected List<Node> getNeighbors(final Node node, final RelationshipType relationshipType) {
        List<Node> neighbors = new ArrayList<>();
        for (Relationship relationship : node.getRelationships(Direction.OUTGOING, relationshipType)) {
            Node otherNode = relationship.getOtherNode(node);
            neighbors.add(otherNode);
        }
        return neighbors;
    }

    protected void addEdge(final Node node, final RelationshipType relationshipType, final Node neighbor) {
        if (neighbor != null && relationshipType != null) {
            builder.build(new EdgeOperation<>(node, neighbor, relationshipType.name()));
        }
    }

    @Override
    public List<EdgeOperation<Node, String>> getOperations() {
        // TODO Auto-generated method stub
        return null;
    }

}