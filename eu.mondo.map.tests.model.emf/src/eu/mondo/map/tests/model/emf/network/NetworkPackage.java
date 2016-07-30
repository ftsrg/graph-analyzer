/**
 */
package eu.mondo.map.tests.model.emf.network;

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
 * @see eu.mondo.map.tests.model.emf.network.NetworkFactory
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
        String eNS_URI = "eu.mondo.map.tests.model.emf.network";

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
        NetworkPackage eINSTANCE = eu.mondo.map.tests.model.emf.network.impl.NetworkPackageImpl.init();

        /**
         * The meta object id for the '{@link eu.mondo.map.tests.model.emf.network.impl.NodeContainerImpl <em>Node Container</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see eu.mondo.map.tests.model.emf.network.impl.NodeContainerImpl
         * @see eu.mondo.map.tests.model.emf.network.impl.NetworkPackageImpl#getNodeContainer()
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
         * The meta object id for the '{@link eu.mondo.map.tests.model.emf.network.impl.NodeImpl <em>Node</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see eu.mondo.map.tests.model.emf.network.impl.NodeImpl
         * @see eu.mondo.map.tests.model.emf.network.impl.NetworkPackageImpl#getNode()
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
         * Returns the meta object for class '{@link eu.mondo.map.tests.model.emf.network.NodeContainer <em>Node Container</em>}'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return the meta object for class '<em>Node Container</em>'.
         * @see eu.mondo.map.tests.model.emf.network.NodeContainer
         * @generated
         */
        EClass getNodeContainer();

        /**
         * Returns the meta object for the containment reference list '{@link eu.mondo.map.tests.model.emf.network.NodeContainer#getNodes <em>Nodes</em>}'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return the meta object for the containment reference list '<em>Nodes</em>'.
         * @see eu.mondo.map.tests.model.emf.network.NodeContainer#getNodes()
         * @see #getNodeContainer()
         * @generated
         */
        EReference getNodeContainer_Nodes();

        /**
         * Returns the meta object for class '{@link eu.mondo.map.tests.model.emf.network.Node <em>Node</em>}'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return the meta object for class '<em>Node</em>'.
         * @see eu.mondo.map.tests.model.emf.network.Node
         * @generated
         */
        EClass getNode();

        /**
         * Returns the meta object for the reference list '{@link eu.mondo.map.tests.model.emf.network.Node#getDim1 <em>Dim1</em>}'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return the meta object for the reference list '<em>Dim1</em>'.
         * @see eu.mondo.map.tests.model.emf.network.Node#getDim1()
         * @see #getNode()
         * @generated
         */
        EReference getNode_Dim1();

        /**
         * Returns the meta object for the attribute '{@link eu.mondo.map.tests.model.emf.network.Node#getName <em>Name</em>}'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return the meta object for the attribute '<em>Name</em>'.
         * @see eu.mondo.map.tests.model.emf.network.Node#getName()
         * @see #getNode()
         * @generated
         */
        EAttribute getNode_Name();

        /**
         * Returns the meta object for the reference list '{@link eu.mondo.map.tests.model.emf.network.Node#getDim2 <em>Dim2</em>}'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return the meta object for the reference list '<em>Dim2</em>'.
         * @see eu.mondo.map.tests.model.emf.network.Node#getDim2()
         * @see #getNode()
         * @generated
         */
        EReference getNode_Dim2();

        /**
         * Returns the meta object for the reference list '{@link eu.mondo.map.tests.model.emf.network.Node#getDim3 <em>Dim3</em>}'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return the meta object for the reference list '<em>Dim3</em>'.
         * @see eu.mondo.map.tests.model.emf.network.Node#getDim3()
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
                 * The meta object literal for the '{@link eu.mondo.map.tests.model.emf.network.impl.NodeContainerImpl <em>Node Container</em>}' class.
                 * <!-- begin-user-doc -->
                 * <!-- end-user-doc -->
                 * @see eu.mondo.map.tests.model.emf.network.impl.NodeContainerImpl
                 * @see eu.mondo.map.tests.model.emf.network.impl.NetworkPackageImpl#getNodeContainer()
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
                 * The meta object literal for the '{@link eu.mondo.map.tests.model.emf.network.impl.NodeImpl <em>Node</em>}' class.
                 * <!-- begin-user-doc -->
                 * <!-- end-user-doc -->
                 * @see eu.mondo.map.tests.model.emf.network.impl.NodeImpl
                 * @see eu.mondo.map.tests.model.emf.network.impl.NetworkPackageImpl#getNode()
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
