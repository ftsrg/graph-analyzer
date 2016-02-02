package eu.mondo.map.emf.metrics;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import eu.mondo.map.core.metrics.models.DegreeList;
import eu.mondo.map.emf.EMFAnalyzerUtility;

public class EMFDegreeList extends DegreeList {

	public void calculate(final TreeIterator<EObject> contents) {
		EObject object;
		EList<EReference> references;

		while (contents.hasNext()) {
			object = contents.next();
			references = object.eClass().getEAllReferences();
			values.add(EMFAnalyzerUtility.getDegree(object, references));
		}
	}

	public void calculateOfTypes(final TreeIterator<EObject> contents) {
		EObject object;
		EList<EReference> references;
		while (contents.hasNext()) {
			object = contents.next();
			references = object.eClass().getEAllReferences();
			typedValues.put(object.eClass().getName(),
					EMFAnalyzerUtility.getDegree(object, references));
		}

	}

}
