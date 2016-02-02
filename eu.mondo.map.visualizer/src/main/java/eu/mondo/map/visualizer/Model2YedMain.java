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

import org.eclipse.emf.ecore.EObject;

public class Model2YedMain {
	public static void main(String[] args) throws IOException {	
		final Model2Yed model2Yed = new Model2Yed();		
		
		final List<EObject> model = new ArrayList<>();
		final CharSequence gml = model2Yed.transform(model);
		System.out.println(gml);
	}
}
