package eu.mondo.map.emf;

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import eu.mondo.map.core.graph.Network;

public class EMFNetworkFactory {

	public static Network<EObject> createNetwork(Iterator<EObject> objects) {
		Network<EObject> network = new Network<EObject>();
		EObject obj;

		while (objects.hasNext()) {
			obj = objects.next();
			network.addNode(obj);
			for (EReference ref : obj.eClass().getEAllReferences()) {
				if (!ref.isDerived()) {
					if (ref.isMany()) {
						for (EObject neighbor : (EList<EObject>) obj.eGet(ref, true)) {
							network.addEdge(ref.getName(), obj, neighbor);
						}
					} else {
						network.addEdge(ref.getName(), obj, (EObject) obj.eGet(ref, true));
					}
				}
			}
		}
		return network;
	}
}
