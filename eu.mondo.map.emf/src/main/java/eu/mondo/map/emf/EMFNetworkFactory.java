package eu.mondo.map.emf;

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import eu.mondo.map.core.graph.Network;

public class EMFNetworkFactory {

	public static Network<EObject> createNetwork(final Iterator<EObject> objects, final boolean containmentOnly) {
		if (containmentOnly) {
			return createNetworkWithContainments(objects);
		} else {
			return createNetworkWithAllDimensions(objects);
		}
	}

	protected static Network<EObject> createNetworkWithAllDimensions(final Iterator<EObject> objects) {
		Network<EObject> network = new Network<>();

		while (objects.hasNext()) {
			final EObject object = objects.next();
			network.addNode(object);
			for (final EReference reference : object.eClass().getEAllReferences()) {
				if (!reference.isDerived()) {
					final String referenceWithContainingClass = reference.getEContainingClass().getName() + "." + reference.getName();
					if (reference.isMany()) {
						for (final EObject neighbor : (EList<EObject>) object.eGet(reference, true)) {
							network.addEdge(referenceWithContainingClass, object, neighbor);
						}
					} else {
						network.addEdge(referenceWithContainingClass, object, (EObject) object.eGet(reference, true));
					}
				}
			}
		}
		return network;
	}

	protected static Network<EObject> createNetworkWithContainments(final Iterator<EObject> objects) {
		Network<EObject> network = new Network<>();

		String containment = "containment";
		String nonContainment = "nonContainment";

		while (objects.hasNext()) {
			final EObject object = objects.next();
			network.addNode(object);
			for (final EReference reference : object.eClass().getEAllReferences()) {
				if (!reference.isDerived()) {
					if (reference.isMany()) {
						for (final EObject neighbor : (EList<EObject>) object.eGet(reference, true)) {
							if (reference.isContainment()) {
								network.addEdge(containment, object, neighbor);
							} else {
								network.addEdge(nonContainment, object, neighbor);
							}

						}
					} else {
						if (reference.isContainment()) {
							network.addEdge(containment, object, (EObject) object.eGet(reference, true));
						} else {
							network.addEdge(nonContainment, object, (EObject) object.eGet(reference, true));
						}
					}
				}
			}
		}
		return network;
	}

}
