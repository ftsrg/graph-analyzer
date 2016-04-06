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

		int k = 0;
		while (objects.hasNext()) {
			final EObject object = objects.next();
			network.addNode(object);

			for (final EReference reference : object.eClass().getEAllReferences()) {
				if (!reference.isDerived() && object.eIsSet(reference)) {
					final String referenceWithContainingClass = reference.getEContainingClass().getName() + "." + reference.getName();

					if (reference.isMany()) { // many
						for (final EObject neighbor : (EList<EObject>) object.eGet(reference, true)) {
							network.addEdge(referenceWithContainingClass, object, neighbor);

							if (reference.isContainment()) {
								k++;
							}
						}
					} else { // single
						network.addEdge(referenceWithContainingClass, object, (EObject) object.eGet(reference, true));
						if (reference.isContainment()) {
							k++;
						}
					}
				}
			}
		}
		// System.out.println(k);
		return network;
	}

	protected static Network<EObject> createNetworkWithContainments(final Iterator<EObject> objects) {
		Network<EObject> network = new Network<>();

		final String containment = "containment";
		final String nonContainment = "nonContainment";

		int k = 0;
		while (objects.hasNext()) {
			final EObject object = objects.next();
			network.addNode(object);
			for (final EReference reference : object.eClass().getEAllReferences()) {
				if (!reference.isDerived() && object.eIsSet(reference)) {
					if (reference.isMany()) { // many
						for (final EObject neighbor : (EList<EObject>) object.eGet(reference, true)) {
							if (reference.isContainment()) {
								network.addEdge(containment, object, neighbor);
								// System.out.println(object.eClass().getName() + "-[" + reference.getName() + "]->"+
								// neighbor.eClass().getName());
								k++;
							} else {
								network.addEdge(nonContainment, object, neighbor);
							}

						}
					} else { // single
						final EObject neighbor = (EObject) object.eGet(reference, true);
						if (reference.isContainment()) {
							network.addEdge(containment, object, neighbor);
							// System.out.println(object);
							// System.out.println(neighbor);
							// System.out.println(object.eClass().getName() + "-[" + reference.getName() + "]->"+
							// neighbor.eClass().getName());
							k++;
						} else {
							network.addEdge(nonContainment, object, neighbor);
						}
					}
				}
			}
		}
		// System.out.println(k);
		return network;
	}

}
