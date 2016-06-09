/**
 */
package eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.Container#getPeople <em>People</em>}</li>
 * </ul>
 *
 * @see eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.SchoolPackage#getContainer()
 * @model
 * @generated
 */
public interface Container extends EObject {
	/**
	 * Returns the value of the '<em><b>People</b></em>' containment reference list.
	 * The list contents are of type {@link eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.Person}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>People</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>People</em>' containment reference list.
	 * @see eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.SchoolPackage#getContainer_People()
	 * @model containment="true"
	 * @generated
	 */
	EList<Person> getPeople();

} // Container
