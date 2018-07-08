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
 *   <li>{@link Node#getType1 <em>Type1</em>}</li>
 *   <li>{@link Node#getName <em>Name</em>}</li>
 *   <li>{@link Node#getType2 <em>Type2</em>}</li>
 *   <li>{@link Node#getType3 <em>Type3</em>}</li>
 * </ul>
 *
 * @see NetworkPackage#getNode()
 * @model
 * @generated
 */
public interface Node extends EObject {
        /**
         * Returns the value of the '<em><b>Type1</b></em>' reference list.
         * The list contents are of type {@link Node}.
         * <!-- begin-user-doc -->
         * <p>
         * If the meaning of the '<em>Type1</em>' reference list isn't clear,
         * there really should be more of a description here...
         * </p>
         * <!-- end-user-doc -->
         * @return the value of the '<em>Type1</em>' reference list.
         * @see NetworkPackage#getNode_Type1()
         * @model
         * @generated
         */
        EList<Node> getType1();

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
         * Returns the value of the '<em><b>Type2</b></em>' reference list.
         * The list contents are of type {@link Node}.
         * <!-- begin-user-doc -->
         * <p>
         * If the meaning of the '<em>Type2</em>' reference list isn't clear,
         * there really should be more of a description here...
         * </p>
         * <!-- end-user-doc -->
         * @return the value of the '<em>Type2</em>' reference list.
         * @see NetworkPackage#getNode_Type2()
         * @model
         * @generated
         */
        EList<Node> getType2();

        /**
         * Returns the value of the '<em><b>Type3</b></em>' reference list.
         * The list contents are of type {@link Node}.
         * <!-- begin-user-doc -->
         * <p>
         * If the meaning of the '<em>Type3</em>' reference list isn't clear,
         * there really should be more of a description here...
         * </p>
         * <!-- end-user-doc -->
         * @return the value of the '<em>Type3</em>' reference list.
         * @see NetworkPackage#getNode_Type3()
         * @model
         * @generated
         */
        EList<Node> getType3();

} // Node
