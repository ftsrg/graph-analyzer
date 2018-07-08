/**
 */
package hu.bme.mit.ga.tests.graph.emf.network;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Node#getDim1 <em>Dim1</em>}</li>
 *   <li>{@link Node#getName <em>Name</em>}</li>
 *   <li>{@link Node#getDim2 <em>Dim2</em>}</li>
 *   <li>{@link Node#getDim3 <em>Dim3</em>}</li>
 * </ul>
 *
 * @see NetworkPackage#getNode()
 * @model
 * @generated
 */
public interface Node extends EObject {
        /**
         * Returns the value of the '<em><b>Dim1</b></em>' reference list.
         * The list contents are of type {@link Node}.
         * <!-- begin-user-doc -->
         * <p>
         * If the meaning of the '<em>Dim1</em>' reference list isn't clear,
         * there really should be more of a description here...
         * </p>
         * <!-- end-user-doc -->
         * @return the value of the '<em>Dim1</em>' reference list.
         * @see NetworkPackage#getNode_Dim1()
         * @model
         * @generated
         */
        EList<Node> getDim1();

        /**
         * Returns the value of the '<em><b>Name</b></em>' attribute.
         * <!-- begin-user-doc -->
         * <p>
         * If the meaning of the '<em>Name</em>' attribute isn't clear,
         * there really should be more of a description here...
         * </p>
         * <!-- end-user-doc -->
         * @return the value of the '<em>Name</em>' attribute.
         * @see #setName(String)
         * @see NetworkPackage#getNode_Name()
         * @model
         * @generated
         */
        String getName();

        /**
         * Sets the value of the '{@link Node#getName <em>Name</em>}' attribute.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @param value the new value of the '<em>Name</em>' attribute.
         * @see #getName()
         * @generated
         */
        void setName(String value);

        /**
         * Returns the value of the '<em><b>Dim2</b></em>' reference list.
         * The list contents are of type {@link Node}.
         * <!-- begin-user-doc -->
         * <p>
         * If the meaning of the '<em>Dim2</em>' reference list isn't clear,
         * there really should be more of a description here...
         * </p>
         * <!-- end-user-doc -->
         * @return the value of the '<em>Dim2</em>' reference list.
         * @see NetworkPackage#getNode_Dim2()
         * @model
         * @generated
         */
        EList<Node> getDim2();

        /**
         * Returns the value of the '<em><b>Dim3</b></em>' reference list.
         * The list contents are of type {@link Node}.
         * <!-- begin-user-doc -->
         * <p>
         * If the meaning of the '<em>Dim3</em>' reference list isn't clear,
         * there really should be more of a description here...
         * </p>
         * <!-- end-user-doc -->
         * @return the value of the '<em>Dim3</em>' reference list.
         * @see NetworkPackage#getNode_Dim3()
         * @model
         * @generated
         */
        EList<Node> getDim3();

} // Node
