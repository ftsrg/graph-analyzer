package eu.mondo.map.emf;

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;

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
				// skip derived and unset references
				if (skippable(object, reference)) {
					continue;
				}
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
				// skip some references, see the implementation of the skippable method
				if (skippable(object, reference)) {
					continue;
				}
				if (reference.isMany()) { // many
					for (final EObject neighbor : (EList<EObject>) object.eGet(reference, true)) {
						if (reference.isContainment()) {
							network.addEdge(containment, object, neighbor);
							k++;
						} else {
							network.addEdge(nonContainment, object, neighbor);
						}

					}
				} else { // single
					final EObject neighbor = (EObject) object.eGet(reference, true);
					if (reference.isContainment()) {
						network.addEdge(containment, object, neighbor);
						k++;
					} else {
						network.addEdge(nonContainment, object, neighbor);
					}
				}

			}
		}
		return network;
	}

	private static boolean skippable(final EObject object, final EReference reference) {
		return !reference.isContainment() || reference.isDerived() || !object.eIsSet(reference) || hasStrongerOpposite(reference);
	}

	private static boolean hasStrongerOpposite(EReference reference) {
		// a reference is considered to have a stronger opposite,
		// if it has an opposite that
		// - is a containment opposite
		// - or the name of the opposite follows the name of the reference w.r.t. a lexicographical ordering
		final EReference opposite = reference.getEOpposite();

		if (opposite == null) {
			return false;
		}

		if (opposite.isContainment()) {
			return true;
		}

		final String referenceString = EcoreUtil.getURI(reference).toString();
		final String oppositeString = EcoreUtil.getURI(opposite).toString();

		if (referenceString.equals(oppositeString)) {
			throw new RuntimeException("The reference and its opposite have the same name.");
		}
		if (referenceString.compareTo(oppositeString) < 0) {
			return false;
		}

		return true;
	}

}
