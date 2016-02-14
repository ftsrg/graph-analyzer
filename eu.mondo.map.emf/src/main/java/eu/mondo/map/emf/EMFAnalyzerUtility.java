package eu.mondo.map.emf;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

public class EMFAnalyzerUtility {

	public static int getDegree(final EObject object, final EList<EReference> references) {
		int degree = 0;
		for (EReference ref : references) {
			degree += getDegree(object, ref);
		}
		return degree;
	}

	@SuppressWarnings("unchecked")
	public static int getDegree(final EObject object, final EReference reference) {
		int degree = 0;
		if (reference.isMany()) {
			degree += ((EList<EObject>) object.eGet(reference, true)).size();
		} else {
			if (object.eGet(reference, true) != null) {
				degree++;
			}
		}
		return degree;
	}
}
