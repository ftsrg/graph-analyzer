/**
 */
package eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.impl;

import eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.SchoolPackage;
import eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.Student;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Student</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class StudentImpl extends PersonImpl implements Student {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StudentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SchoolPackage.Literals.STUDENT;
	}

} //StudentImpl
