package hu.bme.mit.ga.adapters.neo4j;

import com.google.common.collect.ImmutableList;
import hu.bme.mit.ga.adapters.GraphAdapter;
import hu.bme.mit.ga.adapters.GraphIndexer;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.ResourceIterable;
import org.neo4j.graphdb.Transaction;

public class Neo4jGraphAdapter extends GraphAdapter<Node, String> {

    public void init(GraphDatabaseService graph) {
        try (Transaction tx = graph.beginTx()) {
            init(graph.getAllNodes());
        }
    }

    protected void init(ResourceIterable<Node> nodes) {
        final ImmutableList<Node> nodesList = ImmutableList.copyOf(nodes);
        indexer = new GraphIndexer<>(nodesList.size());
        for (Node node : nodesList) {
            indexer.addNode(node);
        }
        for (Node node : nodesList) {
            for (Relationship relationship : node.getRelationships(Direction.OUTGOING)) {
                Node neighbor = relationship.getOtherNode(node);
                addEdge(node, relationship.getType(), neighbor);
            }
        }
    }

    protected void addEdge(final Node node, final RelationshipType relationshipType, final Node neighbor) {
        if (neighbor != null && relationshipType != null) {
            indexer.addEdge(relationshipType.name(), node, neighbor);
        }
    }

}
