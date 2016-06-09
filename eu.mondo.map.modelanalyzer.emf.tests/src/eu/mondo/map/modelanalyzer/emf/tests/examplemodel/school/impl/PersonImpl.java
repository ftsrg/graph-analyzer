/**
 */
package eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.impl;

import eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.Person;
import eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.SchoolPackage;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Person</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class PersonImpl extends MinimalEObjectImpl.Container implements Person {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PersonImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SchoolPackage.Literals.PERSON;
	}

} //PersonImpl
