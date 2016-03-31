package eu.mondo.map.rdf;

import org.openrdf.model.Resource;
import org.openrdf.model.Statement;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.RepositoryResult;

import eu.mondo.map.core.graph.Network;

public class RDFNetworkFactory {

	public static Network<Resource> createNetwork(final RepositoryConnection connection) throws RepositoryException {
		Network<Resource> network = new Network<>();

		RepositoryResult<Statement> statements = connection.getStatements(null, null, null, true);
		
		while (statements.hasNext()) {
			System.out.println(statements.next());
		}
		
//		while (objects.hasNext()) {
//			obj = objects.next();
//			network.addNode(obj);
//			for (EReference ref : obj.eClass().getEAllReferences()) {
//				if (!ref.isDerived()) {
//					String referenceWithContainingClass = ref.getEContainingClass().getName() + "." + ref.getName();
//					if (ref.isMany()) {
//						for (EObject neighbor : (EList<EObject>) obj.eGet(ref, true)) {
//							network.addEdge(referenceWithContainingClass, obj, neighbor);
//						}
//					} else {
//						network.addEdge(referenceWithContainingClass, obj, (EObject) obj.eGet(ref, true));
//					}
//				}
//			}
//		}
		return network;
	}
}
