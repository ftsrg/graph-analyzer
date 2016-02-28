package eu.mondo.map.emf

import java.util.Iterator
import org.eclipse.emf.ecore.EObject
import eu.mondo.map.core.graph.Network
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.common.util.EList

class EMFNetworkFactory {

	def static Network<EObject> createNetwork(Iterator<EObject> objects) {
		val network = new Network<EObject>
		var EObject obj

		while (objects.hasNext) {
			obj = objects.next()
			network.addNode(obj)
			for (EReference ref : obj.eClass.EAllReferences) {
//				if (!ref.derived){
				// TODO do something with derived features
					if (ref.many) {
						for (EObject neighbor : obj.eGet(ref, true) as EList<EObject>) {
							network.addEdge(ref.name, obj, neighbor)
						}
					} else {
						network.addEdge(ref.name, obj, obj.eGet(ref, true) as EObject)
					}
//				}
			}
		}
		return network
	}
}