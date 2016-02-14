package eu.mondo.map.emf.metrics;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import eu.mondo.map.emf.EMFAnalyzerUtility;
import eu.mondo.map.modelmetrics.scalar.typed.NumberOfTypedEdges;

public class EMFNumberOfTypedEdges extends NumberOfTypedEdges {

//	public void calculate(final TreeIterator<EObject> contents) {
//		EObject object;
//		EList<EReference> references;
//		value = 0;
//
//		while (contents.hasNext()) {
//			object = contents.next();
//			references = object.eClass().getEAllReferences();
//			value += EMFAnalyzerUtility.getDegree(object, references);
//		}
//	}

	public void calculate(final TreeIterator<EObject> contents) {
		EObject object;
		EList<EReference> references;
		typedValues.clear();
		int degree = 0;
		int oldValue;
		int newValue;
		String name;

		while (contents.hasNext()) {
			object = contents.next();
			references = object.eClass().getEAllReferences();

			for (EReference ref : references) {
				degree = EMFAnalyzerUtility.getDegree(object, ref);
				name = ref.getName();
				if (typedValues.containsKey(name)) {
					oldValue = typedValues.get(name);
					newValue = oldValue + degree;
					typedValues.put(name, newValue);
				} else {
					typedValues.put(name, degree);
				}

			}
		}
	}
}
