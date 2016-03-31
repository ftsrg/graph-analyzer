package eu.mondo.map.rdf;

import java.util.ArrayList;
import java.util.List;

import org.openrdf.model.Resource;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.RepositoryResult;

import eu.mondo.map.core.graph.Network;

public class RDFNetworkFactory {

	public static Network<Resource> createNetwork(final RepositoryConnection connection) throws RepositoryException {
		final Network<Resource> network = new Network<>();
		final List<Resource> resources = new ArrayList<>();
		final RepositoryResult<Statement> statements = connection.getStatements(null, null, null, true);

		while (statements.hasNext()) {
			final Statement statement = statements.next();

			final Resource subject = statement.getSubject();
			resources.add(subject);

			final Value object = statement.getObject();
			if (object instanceof Resource) {
				resources.add((Resource) object);
			}
		}
		
		for (Resource resource : resources) {
			network.addNode(resource); 

			RepositoryResult<Statement> outgoingEdges = connection.getStatements(resource, null, null, true);
			while (outgoingEdges.hasNext()) {
				final Statement edge = outgoingEdges.next();
				final URI predicate = edge.getPredicate();
				final Value object = edge.getObject();
				
				if (!(object instanceof Resource)) {
					continue;
				}
				
				network.addEdge(predicate.toString(), resource, (Resource) object);
			}
		}
		
		return network;
	}
}
