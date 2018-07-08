/**
 */
package hu.bme.mit.ga.tests.graph.emf.network;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link NodeContainer#getNodes <em>Nodes</em>}</li>
 * </ul>
 *
 * @see NetworkPackage#getNodeContainer()
 * @model
 * @generated
 */
public interface NodeContainer extends EObject {
        /**
         * Returns the value of the '<em><b>Nodes</b></em>' containment reference list.
         * The list contents are of type {@link Node}.
         * <!-- begin-user-doc -->
         * <p>
         * If the meaning of the '<em>Nodes</em>' containment reference list isn't clear,
         * there really should be more of a description here...
         * </p>
         * <!-- end-user-doc -->
         * @return the value of the '<em>Nodes</em>' containment reference list.
         * @see NetworkPackage#getNodeContainer_Nodes()
         * @model containment="true"
         * @generated
         */
        EList<Node> getNodes();

} // NodeContainer
