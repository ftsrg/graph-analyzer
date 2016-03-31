package eu.mondo.map.rdf;

import java.util.Collection;

import org.openrdf.model.Resource;
import org.openrdf.model.URI;

public class RDFAnalyzerUtility {

	public static int getDegree(final Resource object, final Collection<URI> references) {
		int degree = 0;
//		for (EReference ref : references) {
//			degree += getDegree(object, ref);
//		}
		return degree;
	}

	@SuppressWarnings("unchecked")
	public static int getDegree(final Resource object, final URI reference) {
		int degree = 0;
//		if (reference.isMany()) {
//			degree += ((EList<EObject>) object.eGet(reference, true)).size();
//		} else {
//			if (object.eGet(reference, true) != null) {
//				degree++;
//			}
//		}
		return degree;
	}
}
