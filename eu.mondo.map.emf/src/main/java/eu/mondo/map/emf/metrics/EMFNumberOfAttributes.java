package eu.mondo.map.emf.metrics;

import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;

import eu.mondo.map.modelmetrics.composite.typed.NumberOfTypedAttributes;

public class EMFNumberOfAttributes extends NumberOfTypedAttributes {

	public void calculate(final TreeIterator<EObject> contents) {
		EObject object;
		List<EAttribute> attributes;
		Object currentAttribute;
		String attributeName;
		String attributeValue;
		int appearance;

		while (contents.hasNext()) {
			object = contents.next();
			attributes = object.eClass().getEAllAttributes();
			for (EAttribute attribute : attributes) {
				attributeName = attribute.getName();
				currentAttribute = object.eGet(attribute);
				if (currentAttribute == null) {
					continue;
				}
				attributeValue = object.eGet(attribute).toString();
				if (multitypedValues.contains(attributeName, attributeValue)) {
					appearance = multitypedValues.get(attributeName, attributeValue) + 1;
					multitypedValues.put(attributeName, attributeValue, appearance);
				} else {
					multitypedValues.put(attributeName, attributeValue, 1);
				}

			}
		}
	}
}
