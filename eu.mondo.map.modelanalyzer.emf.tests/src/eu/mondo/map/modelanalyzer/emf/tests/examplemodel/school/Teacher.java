/**
 */
package eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Teacher</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.Teacher#getTeach <em>Teach</em>}</li>
 * </ul>
 *
 * @see eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.SchoolPackage#getTeacher()
 * @model
 * @generated
 */
public interface Teacher extends Person {
	/**
	 * Returns the value of the '<em><b>Teach</b></em>' reference list.
	 * The list contents are of type {@link eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.Student}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Teach</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Teach</em>' reference list.
	 * @see eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.SchoolPackage#getTeacher_Teach()
	 * @model
	 * @generated
	 */
	EList<Student> getTeach();

} // Teacher
