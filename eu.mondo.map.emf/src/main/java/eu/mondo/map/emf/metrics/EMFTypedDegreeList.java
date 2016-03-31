//package eu.mondo.map.emf.metrics;
//
//import eu.mondo.map.modelmetrics.composite.typed.TypedDegreeList;
//
//public class EMFTypedDegreeList extends TypedDegreeList {
//
//	public void calculate(final TreeIterator<EObject> contents) {
//		EObject object;
//		EList<EReference> references;
//		while (contents.hasNext()) {
//			object = contents.next();
//			references = object.eClass().getEAllReferences();
//			typedValues.put(object.eClass().getName(),
//					EMFAnalyzerUtility.getDegree(object, references));
//		}
//	}
//
//}
