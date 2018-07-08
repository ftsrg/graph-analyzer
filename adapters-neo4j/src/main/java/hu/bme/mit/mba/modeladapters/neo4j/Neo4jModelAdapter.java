package hu.bme.mit.mba.modeladapters.neo4j;

import hu.bme.mit.mba.modeladapters.ModelAdapter;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.ResourceIterable;
import org.neo4j.graphdb.Transaction;

import java.util.Iterator;

public class Neo4jModelAdapter extends ModelAdapter<Node, String> {

	private GraphDatabaseService graph;

    @Override
	public Iterator<Node> getModelIterator() {
        try (Transaction tx = graph.beginTx()) {
            return graph.getAllNodes().iterator();
        }
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

	protected void addEdge(final Node node, final RelationshipType relationshipType, final Node neighbor) {
		if (neighbor != null && relationshipType != null) {
			indexer.addEdge(relationshipType.name(), node, neighbor);
		}
	}

}
