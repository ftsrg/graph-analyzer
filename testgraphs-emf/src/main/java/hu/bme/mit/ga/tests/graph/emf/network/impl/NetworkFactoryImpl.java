/**
 */
package hu.bme.mit.ga.tests.graph.emf.network.impl;

import hu.bme.mit.ga.tests.graph.emf.network.NetworkFactory;
import hu.bme.mit.ga.tests.graph.emf.network.NetworkPackage;
import hu.bme.mit.ga.tests.graph.emf.network.Node;
import hu.bme.mit.ga.tests.graph.emf.network.NodeContainer;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class NetworkFactoryImpl extends EFactoryImpl implements NetworkFactory {
        /**
         * Creates the default factory implementation.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public static NetworkFactory init() {
                try {
                        NetworkFactory theNetworkFactory = (NetworkFactory)EPackage.Registry.INSTANCE.getEFactory(NetworkPackage.eNS_URI);
                        if (theNetworkFactory != null) {
                                return theNetworkFactory;
                        }
                }
                catch (Exception exception) {
                        EcorePlugin.INSTANCE.log(exception);
                }
                return new NetworkFactoryImpl();
        }

        /**
         * Creates an instance of the factory.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public NetworkFactoryImpl() {
                super();
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @Override
        public EObject create(EClass eClass) {
                switch (eClass.getClassifierID()) {
                        case NetworkPackage.NODE_CONTAINER: return createNodeContainer();
                        case NetworkPackage.NODE: return createNode();
                        default:
                                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
                }
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public NodeContainer createNodeContainer() {
                NodeContainerImpl nodeContainer = new NodeContainerImpl();
                return nodeContainer;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public Node createNode() {
                NodeImpl node = new NodeImpl();
                return node;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public NetworkPackage getNetworkPackage() {
                return (NetworkPackage)getEPackage();
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @deprecated
         * @generated
         */
        @Deprecated
        public static NetworkPackage getPackage() {
                return NetworkPackage.eINSTANCE;
        }

} //NetworkFactoryImpl
