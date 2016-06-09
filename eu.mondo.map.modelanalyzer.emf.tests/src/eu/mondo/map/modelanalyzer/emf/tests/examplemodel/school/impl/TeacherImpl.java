/**
 */
package eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.impl;

import eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.SchoolPackage;
import eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.Student;
import eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.Teacher;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Teacher</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.impl.TeacherImpl#getTeach <em>Teach</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TeacherImpl extends PersonImpl implements Teacher {
	/**
	 * The cached value of the '{@link #getTeach() <em>Teach</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTeach()
	 * @generated
	 * @ordered
	 */
	protected EList<Student> teach;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TeacherImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SchoolPackage.Literals.TEACHER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Student> getTeach() {
		if (teach == null) {
			teach = new EObjectResolvingEList<Student>(Student.class, this, SchoolPackage.TEACHER__TEACH);
		}
		return teach;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SchoolPackage.TEACHER__TEACH:
				return getTeach();
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
			case SchoolPackage.TEACHER__TEACH:
				getTeach().clear();
				getTeach().addAll((Collection<? extends Student>)newValue);
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
			case SchoolPackage.TEACHER__TEACH:
				getTeach().clear();
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
			case SchoolPackage.TEACHER__TEACH:
				return teach != null && !teach.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TeacherImpl
