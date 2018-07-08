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
 *   <li>{@link NodeImpl#getType1 <em>Type1</em>}</li>
 *   <li>{@link NodeImpl#getName <em>Name</em>}</li>
 *   <li>{@link NodeImpl#getType2 <em>Type2</em>}</li>
 *   <li>{@link NodeImpl#getType3 <em>Type3</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NodeImpl extends MinimalEObjectImpl.Container implements Node {
        /**
         * The cached value of the '{@link #getType1() <em>Type1</em>}' reference list.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see #getType1()
         * @generated
         * @ordered
         */
        protected EList<Node> type1;

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
         * The cached value of the '{@link #getType2() <em>Type2</em>}' reference list.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see #getType2()
         * @generated
         * @ordered
         */
        protected EList<Node> type2;

        /**
         * The cached value of the '{@link #getType3() <em>Type3</em>}' reference list.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see #getType3()
         * @generated
         * @ordered
         */
        protected EList<Node> type3;

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
        public EList<Node> getType1() {
                if (type1 == null) {
                        type1 = new EObjectResolvingEList<Node>(Node.class, this, NetworkPackage.NODE__TYPE1);
                }
                return type1;
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
        public EList<Node> getType2() {
                if (type2 == null) {
                        type2 = new EObjectResolvingEList<Node>(Node.class, this, NetworkPackage.NODE__TYPE2);
                }
                return type2;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public EList<Node> getType3() {
                if (type3 == null) {
                        type3 = new EObjectResolvingEList<Node>(Node.class, this, NetworkPackage.NODE__TYPE3);
                }
                return type3;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @Override
        public Object eGet(int featureID, boolean resolve, boolean coreType) {
                switch (featureID) {
                        case NetworkPackage.NODE__TYPE1:
                                return getType1();
                        case NetworkPackage.NODE__NAME:
                                return getName();
                        case NetworkPackage.NODE__TYPE2:
                                return getType2();
                        case NetworkPackage.NODE__TYPE3:
                                return getType3();
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
                        case NetworkPackage.NODE__TYPE1:
                                getType1().clear();
                                getType1().addAll((Collection<? extends Node>)newValue);
                                return;
                        case NetworkPackage.NODE__NAME:
                                setName((String)newValue);
                                return;
                        case NetworkPackage.NODE__TYPE2:
                                getType2().clear();
                                getType2().addAll((Collection<? extends Node>)newValue);
                                return;
                        case NetworkPackage.NODE__TYPE3:
                                getType3().clear();
                                getType3().addAll((Collection<? extends Node>)newValue);
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
                        case NetworkPackage.NODE__TYPE1:
                                getType1().clear();
                                return;
                        case NetworkPackage.NODE__NAME:
                                setName(NAME_EDEFAULT);
                                return;
                        case NetworkPackage.NODE__TYPE2:
                                getType2().clear();
                                return;
                        case NetworkPackage.NODE__TYPE3:
                                getType3().clear();
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
                        case NetworkPackage.NODE__TYPE1:
                                return type1 != null && !type1.isEmpty();
                        case NetworkPackage.NODE__NAME:
                                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
                        case NetworkPackage.NODE__TYPE2:
                                return type2 != null && !type2.isEmpty();
                        case NetworkPackage.NODE__TYPE3:
                                return type3 != null && !type3.isEmpty();
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
