package eu.mondo.map.emf.metrics;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import eu.mondo.map.emf.EMFAnalyzerUtility;
import eu.mondo.map.modelmetrics.composite.typed.TypedDegreeList;

public class EMFTypedDegreeList extends TypedDegreeList {

//	public void calculate(final TreeIterator<EObject> contents) {
//		EObject object;
//		EList<EReference> references;
//
//		while (contents.hasNext()) {
//			object = contents.next();
//			references = object.eClass().getEAllReferences();
//			values.add(EMFAnalyzerUtility.getDegree(object, references));
//		}
//	}

	public void calculate(final TreeIterator<EObject> contents) {
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
