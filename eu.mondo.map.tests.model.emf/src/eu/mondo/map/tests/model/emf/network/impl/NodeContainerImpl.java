/**
 */
package eu.mondo.map.tests.model.emf.network.impl;

import eu.mondo.map.tests.model.emf.network.NetworkPackage;
import eu.mondo.map.tests.model.emf.network.Node;
import eu.mondo.map.tests.model.emf.network.NodeContainer;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Node Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.mondo.map.tests.model.emf.network.impl.NodeContainerImpl#getNodes <em>Nodes</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NodeContainerImpl extends MinimalEObjectImpl.Container implements NodeContainer {
        /**
         * The cached value of the '{@link #getNodes() <em>Nodes</em>}' containment reference list.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see #getNodes()
         * @generated
         * @ordered
         */
        protected EList<Node> nodes;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected NodeContainerImpl() {
                super();
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @Override
        protected EClass eStaticClass() {
                return NetworkPackage.Literals.NODE_CONTAINER;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public EList<Node> getNodes() {
                if (nodes == null) {
                        nodes = new EObjectContainmentEList<Node>(Node.class, this, NetworkPackage.NODE_CONTAINER__NODES);
                }
                return nodes;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @Override
        public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
                switch (featureID) {
                        case NetworkPackage.NODE_CONTAINER__NODES:
                                return ((InternalEList<?>)getNodes()).basicRemove(otherEnd, msgs);
                }
                return super.eInverseRemove(otherEnd, featureID, msgs);
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @Override
        public Object eGet(int featureID, boolean resolve, boolean coreType) {
                switch (featureID) {
                        case NetworkPackage.NODE_CONTAINER__NODES:
                                return getNodes();
                }
                return super.eGet(featureID, resolve, coreType);
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @SuppressWarnings("unchecked")
        @Override
        public void eSet(int featureID, Object newValue) {
                switch (featureID) {
                        case NetworkPackage.NODE_CONTAINER__NODES:
                                getNodes().clear();
                                getNodes().addAll((Collection<? extends Node>)newValue);
                                return;
                }
                super.eSet(featureID, newValue);
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @Override
        public void eUnset(int featureID) {
                switch (featureID) {
                        case NetworkPackage.NODE_CONTAINER__NODES:
                                getNodes().clear();
                                return;
                }
                super.eUnset(featureID);
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @Override
        public boolean eIsSet(int featureID) {
                switch (featureID) {
                        case NetworkPackage.NODE_CONTAINER__NODES:
                                return nodes != null && !nodes.isEmpty();
                }
                return super.eIsSet(featureID);
        }

} //NodeContainerImpl
