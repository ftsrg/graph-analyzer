/*******************************************************************************
 * Copyright (c) 2010-2015, Zoltan Ujhelyi, Gabor Szarnyas
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Zoltan Ujhelyi - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package eu.mondo.map.visualizer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import hu.bme.mit.trainbenchmark.railway.RailwayPackage;

public class Model2YedMain {
	
	public static void main(String[] args) throws IOException {	
		final Model2Yed model2Yed = new Model2Yed();		
		
		RailwayPackage.eINSTANCE.eClass();
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());

		final String modelPath = args[0];
		final URI resourceURI = URI.createFileURI(modelPath);
		final ResourceSet resourceSet = new ResourceSetImpl();  
		final Resource resource = resourceSet.getResource(resourceURI, true);

		final List<EObject> model = new ArrayList<>();
		for (EObject root : resource.getContents()) {
			model.add(root);
			TreeIterator<EObject> iterator = root.eAllContents();
			while(iterator.hasNext()) model.add(iterator.next());
		}
		
		final CharSequence gml = model2Yed.transform(model);
		System.out.println(gml);
	}
	
}
