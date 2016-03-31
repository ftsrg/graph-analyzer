package eu.mondo.map.emf;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class ListContainmentEdgesMain {

	public static void main(final String[] args) {
		listContainments(args[0]);
	}

	protected static void listContainments(final String ecorePath) {
		EcorePackage.eINSTANCE.eClass();
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new XMIResourceFactoryImpl());
		
		final ResourceSetImpl resourceSet = new ResourceSetImpl();
		final Resource resource = resourceSet.getResource(URI.createFileURI(ecorePath), true);
		
		final EPackage ePackage = (EPackage) resource.getContents().get(0);
		final TreeIterator<EObject> contents = ePackage.eAllContents();

		while (contents.hasNext()) {
			EObject next = contents.next();
			if (next instanceof EReference) {
				EReference reference = (EReference) next;
				System.out.println(reference.getName() + "," + reference.isContainment());
			}	
		}
	}
	
}
