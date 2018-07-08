/**
 */
package hu.bme.mit.ga.tests.graph.emf.network.impl;

import hu.bme.mit.ga.tests.graph.emf.network.NetworkPackage;
import hu.bme.mit.ga.tests.graph.emf.network.Node;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link NodeImpl#getDim1 <em>Dim1</em>}</li>
 *   <li>{@link NodeImpl#getName <em>Name</em>}</li>
 *   <li>{@link NodeImpl#getDim2 <em>Dim2</em>}</li>
 *   <li>{@link NodeImpl#getDim3 <em>Dim3</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NodeImpl extends MinimalEObjectImpl.Container implements Node {
        /**
         * The cached value of the '{@link #getDim1() <em>Dim1</em>}' reference list.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see #getDim1()
         * @generated
         * @ordered
         */
        protected EList<Node> dim1;

        /**
         * The default value of the '{@link #getName() <em>Name</em>}' attribute.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see #getName()
         * @generated
         * @ordered
         */
        protected static final String NAME_EDEFAULT = null;

        /**
         * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see #getName()
         * @generated
         * @ordered
         */
        protected String name = NAME_EDEFAULT;

        /**
         * The cached value of the '{@link #getDim2() <em>Dim2</em>}' reference list.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see #getDim2()
         * @generated
         * @ordered
         */
        protected EList<Node> dim2;

        /**
         * The cached value of the '{@link #getDim3() <em>Dim3</em>}' reference list.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see #getDim3()
         * @generated
         * @ordered
         */
        protected EList<Node> dim3;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected NodeImpl() {
                super();
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @Override
        protected EClass eStaticClass() {
                return NetworkPackage.Literals.NODE;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public EList<Node> getDim1() {
                if (dim1 == null) {
                        dim1 = new EObjectResolvingEList<Node>(Node.class, this, NetworkPackage.NODE__DIM1);
                }
                return dim1;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public String getName() {
                return name;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public void setName(String newName) {
                String oldName = name;
                name = newName;
                if (eNotificationRequired())
                        eNotify(new ENotificationImpl(this, Notification.SET, NetworkPackage.NODE__NAME, oldName, name));
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public EList<Node> getDim2() {
                if (dim2 == null) {
                        dim2 = new EObjectResolvingEList<Node>(Node.class, this, NetworkPackage.NODE__DIM2);
                }
                return dim2;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public EList<Node> getDim3() {
                if (dim3 == null) {
                        dim3 = new EObjectResolvingEList<Node>(Node.class, this, NetworkPackage.NODE__DIM3);
                }
                return dim3;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @Override
        public Object eGet(int featureID, boolean resolve, boolean coreType) {
                switch (featureID) {
                        case NetworkPackage.NODE__DIM1:
                                return getDim1();
                        case NetworkPackage.NODE__NAME:
                                return getName();
                        case NetworkPackage.NODE__DIM2:
                                return getDim2();
                        case NetworkPackage.NODE__DIM3:
                                return getDim3();
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
                        case NetworkPackage.NODE__DIM1:
                                getDim1().clear();
                                getDim1().addAll((Collection<? extends Node>)newValue);
                                return;
                        case NetworkPackage.NODE__NAME:
                                setName((String)newValue);
                                return;
                        case NetworkPackage.NODE__DIM2:
                                getDim2().clear();
                                getDim2().addAll((Collection<? extends Node>)newValue);
                                return;
                        case NetworkPackage.NODE__DIM3:
                                getDim3().clear();
                                getDim3().addAll((Collection<? extends Node>)newValue);
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
                        case NetworkPackage.NODE__DIM1:
                                getDim1().clear();
                                return;
                        case NetworkPackage.NODE__NAME:
                                setName(NAME_EDEFAULT);
                                return;
                        case NetworkPackage.NODE__DIM2:
                                getDim2().clear();
                                return;
                        case NetworkPackage.NODE__DIM3:
                                getDim3().clear();
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
                        case NetworkPackage.NODE__DIM1:
                                return dim1 != null && !dim1.isEmpty();
                        case NetworkPackage.NODE__NAME:
                                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
                        case NetworkPackage.NODE__DIM2:
                                return dim2 != null && !dim2.isEmpty();
                        case NetworkPackage.NODE__DIM3:
                                return dim3 != null && !dim3.isEmpty();
                }
                return super.eIsSet(featureID);
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @Override
        public String toString() {
                if (eIsProxy()) return super.toString();

                StringBuffer result = new StringBuffer(super.toString());
                result.append(" (name: ");
                result.append(name);
                result.append(')');
                return result.toString();
        }

} //NodeImpl
