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
				if (!included(object, reference)) {
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
				System.out.print(reference.getName() + " ");
				if (!included(object, reference)) {
					System.out.println(" OMITTED");
					continue;
				}
				System.out.println(" OK");
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
		// System.out.println(k);
		return network;
	}

	private static boolean included(final EObject object, final EReference reference) {
		// an edge is INCLUDED, if
		// - it is set, and
		// - it is not derived, and
		// - it is "stronger" than its opposite edge

		return object.eIsSet(reference) && !reference.isDerived() && isStrongerThanItsOpposite(reference);
	}

	private static boolean isStrongerThanItsOpposite(EReference reference) {
		// a reference is considered to be "stronger" than its opposite, if it
		// (1) is a containment reference, or
		// (2) it does not have an opposite, or
		// (3)
		// - a) the opposite is not a containment and
		// - b) the name of the opposite follows the name of the reference w.r.t. a lexicographical ordering

		// (1)
		if (reference.isContainment()) {
			return true;
		}

		// (2)
		final EReference opposite = reference.getEOpposite();
		if (opposite == null) {
			return true;
		}

		// (3)
		if (!opposite.isContainment()) {
			final String referenceString = EcoreUtil.getURI(reference).toString();
			final String oppositeString = EcoreUtil.getURI(opposite).toString();

			if (referenceString.equals(oppositeString)) {
				throw new RuntimeException("The reference and its opposite have the same name.");
			}
			if (referenceString.compareTo(oppositeString) < 0) {
				return true;
			}
		}

		return false;
	}

}
