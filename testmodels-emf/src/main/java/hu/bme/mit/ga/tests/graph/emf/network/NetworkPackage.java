/**
 */
package hu.bme.mit.ga.tests.graph.emf.network;

import hu.bme.mit.ga.tests.graph.emf.network.impl.NetworkPackageImpl;
import hu.bme.mit.ga.tests.graph.emf.network.impl.NodeContainerImpl;
import hu.bme.mit.ga.tests.graph.emf.network.impl.NodeImpl;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see NetworkFactory
 * @model kind="package"
 * @generated
 */
public interface NetworkPackage extends EPackage {
        /**
         * The package name.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        String eNAME = "network";

        /**
         * The package namespace URI.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        String eNS_URI = "hu.bme.mit.mba.tests.model.emf.network";

        /**
         * The package namespace name.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        String eNS_PREFIX = "network";

        /**
         * The singleton instance of the package.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        NetworkPackage eINSTANCE = NetworkPackageImpl.init();

        /**
         * The meta object id for the '{@link NodeContainerImpl <em>Node Container</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see NodeContainerImpl
         * @see NetworkPackageImpl#getNodeContainer()
         * @generated
         */
        int NODE_CONTAINER = 0;

        /**
         * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @ordered
         */
        int NODE_CONTAINER__NODES = 0;

        /**
         * The number of structural features of the '<em>Node Container</em>' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @ordered
         */
        int NODE_CONTAINER_FEATURE_COUNT = 1;

        /**
         * The number of operations of the '<em>Node Container</em>' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @ordered
         */
        int NODE_CONTAINER_OPERATION_COUNT = 0;

        /**
         * The meta object id for the '{@link NodeImpl <em>Node</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see NodeImpl
         * @see NetworkPackageImpl#getNode()
         * @generated
         */
        int NODE = 1;

        /**
         * The feature id for the '<em><b>Dim1</b></em>' reference list.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @ordered
         */
        int NODE__DIM1 = 0;

        /**
         * The feature id for the '<em><b>Name</b></em>' attribute.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @ordered
         */
        int NODE__NAME = 1;

        /**
         * The feature id for the '<em><b>Dim2</b></em>' reference list.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @ordered
         */
        int NODE__DIM2 = 2;

        /**
         * The feature id for the '<em><b>Dim3</b></em>' reference list.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @ordered
         */
        int NODE__DIM3 = 3;

        /**
         * The number of structural features of the '<em>Node</em>' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @ordered
         */
        int NODE_FEATURE_COUNT = 4;

        /**
         * The number of operations of the '<em>Node</em>' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @ordered
         */
        int NODE_OPERATION_COUNT = 0;


        /**
         * Returns the meta object for class '{@link NodeContainer <em>Node Container</em>}'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return the meta object for class '<em>Node Container</em>'.
         * @see NodeContainer
         * @generated
         */
        EClass getNodeContainer();

        /**
         * Returns the meta object for the containment reference list '{@link NodeContainer#getNodes <em>Nodes</em>}'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return the meta object for the containment reference list '<em>Nodes</em>'.
         * @see NodeContainer#getNodes()
         * @see #getNodeContainer()
         * @generated
         */
        EReference getNodeContainer_Nodes();

        /**
         * Returns the meta object for class '{@link Node <em>Node</em>}'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return the meta object for class '<em>Node</em>'.
         * @see Node
         * @generated
         */
        EClass getNode();

        /**
         * Returns the meta object for the reference list '{@link Node#getDim1 <em>Dim1</em>}'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return the meta object for the reference list '<em>Dim1</em>'.
         * @see Node#getDim1()
         * @see #getNode()
         * @generated
         */
        EReference getNode_Dim1();

        /**
         * Returns the meta object for the attribute '{@link Node#getName <em>Name</em>}'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return the meta object for the attribute '<em>Name</em>'.
         * @see Node#getName()
         * @see #getNode()
         * @generated
         */
        EAttribute getNode_Name();

        /**
         * Returns the meta object for the reference list '{@link Node#getDim2 <em>Dim2</em>}'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return the meta object for the reference list '<em>Dim2</em>'.
         * @see Node#getDim2()
         * @see #getNode()
         * @generated
         */
        EReference getNode_Dim2();

        /**
         * Returns the meta object for the reference list '{@link Node#getDim3 <em>Dim3</em>}'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return the meta object for the reference list '<em>Dim3</em>'.
         * @see Node#getDim3()
         * @see #getNode()
         * @generated
         */
        EReference getNode_Dim3();

        /**
         * Returns the factory that creates the instances of the model.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return the factory that creates the instances of the model.
         * @generated
         */
        NetworkFactory getNetworkFactory();

        /**
         * <!-- begin-user-doc -->
         * Defines literals for the meta objects that represent
         * <ul>
         *   <li>each class,</li>
         *   <li>each feature of each class,</li>
         *   <li>each operation of each class,</li>
         *   <li>each enum,</li>
         *   <li>and each data type</li>
         * </ul>
         * <!-- end-user-doc -->
         * @generated
         */
        interface Literals {
                /**
                 * The meta object literal for the '{@link NodeContainerImpl <em>Node Container</em>}' class.
                 * <!-- begin-user-doc -->
                 * <!-- end-user-doc -->
                 * @see NodeContainerImpl
                 * @see NetworkPackageImpl#getNodeContainer()
                 * @generated
                 */
                EClass NODE_CONTAINER = eINSTANCE.getNodeContainer();

                /**
                 * The meta object literal for the '<em><b>Nodes</b></em>' containment reference list feature.
                 * <!-- begin-user-doc -->
                 * <!-- end-user-doc -->
                 * @generated
                 */
                EReference NODE_CONTAINER__NODES = eINSTANCE.getNodeContainer_Nodes();

                /**
                 * The meta object literal for the '{@link NodeImpl <em>Node</em>}' class.
                 * <!-- begin-user-doc -->
                 * <!-- end-user-doc -->
                 * @see NodeImpl
                 * @see NetworkPackageImpl#getNode()
                 * @generated
                 */
                EClass NODE = eINSTANCE.getNode();

                /**
                 * The meta object literal for the '<em><b>Dim1</b></em>' reference list feature.
                 * <!-- begin-user-doc -->
                 * <!-- end-user-doc -->
                 * @generated
                 */
                EReference NODE__DIM1 = eINSTANCE.getNode_Dim1();

                /**
                 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
                 * <!-- begin-user-doc -->
                 * <!-- end-user-doc -->
                 * @generated
                 */
                EAttribute NODE__NAME = eINSTANCE.getNode_Name();

                /**
                 * The meta object literal for the '<em><b>Dim2</b></em>' reference list feature.
                 * <!-- begin-user-doc -->
                 * <!-- end-user-doc -->
                 * @generated
                 */
                EReference NODE__DIM2 = eINSTANCE.getNode_Dim2();

                /**
                 * The meta object literal for the '<em><b>Dim3</b></em>' reference list feature.
                 * <!-- begin-user-doc -->
                 * <!-- end-user-doc -->
                 * @generated
                 */
                EReference NODE__DIM3 = eINSTANCE.getNode_Dim3();

        }

} //NetworkPackage
