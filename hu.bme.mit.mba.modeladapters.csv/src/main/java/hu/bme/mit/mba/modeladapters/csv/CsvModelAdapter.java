package hu.bme.mit.mba.modeladapters.csv;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.ResourceIterable;
import org.neo4j.graphdb.Transaction;

import hu.bme.mit.mba.modeladapters.ModelIndexer;
import hu.bme.mit.mba.modeladapters.TypedModelAdapter;

public class CsvModelAdapter extends TypedModelAdapter<Long, String> {

	@Override
	public Iterator<Long> getModelIterator() {
        try (Transaction tx = graph.beginTx()) {
            return graph.getAllNodes().iterator();
        }
	}

	public void init(String nodesCsv, String relsCsv) {
		try (Transaction tx = graph.beginTx()) {
			init(graph.getAllNodes());
		}
	}

	protected void init(ResourceIterable<Node> nodes) {
		indexer = new ModelIndexer<Node, String>();

		for (Node node : nodes) {
			for (Relationship relationship : node.getRelationships(Direction.OUTGOING)) {
				Node neighbor = relationship.getOtherNode(node);
				addEdge(node, relationship.getType(), neighbor);
			}
		}
	}

	protected List<Node> getNeighbors(final Node node, final RelationshipType relationshipType) {
		List<Node> neighbors = new ArrayList<>();
		for (Relationship relationship: node.getRelationships(Direction.OUTGOING, relationshipType)) {
			Node otherNode = relationship.getOtherNode(node);
			neighbors.add(otherNode);
		}
		return neighbors;
	}

	protected void addEdge(final Node node, final RelationshipType relationshipType, final Node neighbor) {
		if (neighbor != null && relationshipType != null) {
			indexer.addEdge(relationshipType.name(), node, neighbor);
		}
	}

}
