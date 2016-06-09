/**
 */
package eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school;

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
 * @see eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.SchoolFactory
 * @model kind="package"
 * @generated
 */
public interface SchoolPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "school";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "eu.mondo.map.modelanalyzer.emf.tests.schoolmodel";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "school";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SchoolPackage eINSTANCE = eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.impl.SchoolPackageImpl.init();

	/**
	 * The meta object id for the '{@link eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.impl.PersonImpl <em>Person</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.impl.PersonImpl
	 * @see eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.impl.SchoolPackageImpl#getPerson()
	 * @generated
	 */
	int PERSON = 0;

	/**
	 * The number of structural features of the '<em>Person</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Person</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.impl.StudentImpl <em>Student</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.impl.StudentImpl
	 * @see eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.impl.SchoolPackageImpl#getStudent()
	 * @generated
	 */
	int STUDENT = 1;

	/**
	 * The number of structural features of the '<em>Student</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT_FEATURE_COUNT = PERSON_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Student</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT_OPERATION_COUNT = PERSON_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.impl.TeacherImpl <em>Teacher</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.impl.TeacherImpl
	 * @see eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.impl.SchoolPackageImpl#getTeacher()
	 * @generated
	 */
	int TEACHER = 2;

	/**
	 * The feature id for the '<em><b>Teach</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEACHER__TEACH = PERSON_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Teacher</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEACHER_FEATURE_COUNT = PERSON_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Teacher</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEACHER_OPERATION_COUNT = PERSON_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.impl.ContainerImpl <em>Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.impl.ContainerImpl
	 * @see eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.impl.SchoolPackageImpl#getContainer()
	 * @generated
	 */
	int CONTAINER = 3;

	/**
	 * The feature id for the '<em><b>People</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__PEOPLE = 0;

	/**
	 * The number of structural features of the '<em>Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.Person <em>Person</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Person</em>'.
	 * @see eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.Person
	 * @generated
	 */
	EClass getPerson();

	/**
	 * Returns the meta object for class '{@link eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.Student <em>Student</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Student</em>'.
	 * @see eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.Student
	 * @generated
	 */
	EClass getStudent();

	/**
	 * Returns the meta object for class '{@link eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.Teacher <em>Teacher</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Teacher</em>'.
	 * @see eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.Teacher
	 * @generated
	 */
	EClass getTeacher();

	/**
	 * Returns the meta object for the reference list '{@link eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.Teacher#getTeach <em>Teach</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Teach</em>'.
	 * @see eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.Teacher#getTeach()
	 * @see #getTeacher()
	 * @generated
	 */
	EReference getTeacher_Teach();

	/**
	 * Returns the meta object for class '{@link eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.Container <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container</em>'.
	 * @see eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.Container
	 * @generated
	 */
	EClass getContainer();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.Container#getPeople <em>People</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>People</em>'.
	 * @see eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.Container#getPeople()
	 * @see #getContainer()
	 * @generated
	 */
	EReference getContainer_People();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SchoolFactory getSchoolFactory();

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
		 * The meta object literal for the '{@link eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.impl.PersonImpl <em>Person</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.impl.PersonImpl
		 * @see eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.impl.SchoolPackageImpl#getPerson()
		 * @generated
		 */
		EClass PERSON = eINSTANCE.getPerson();

		/**
		 * The meta object literal for the '{@link eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.impl.StudentImpl <em>Student</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.impl.StudentImpl
		 * @see eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.impl.SchoolPackageImpl#getStudent()
		 * @generated
		 */
		EClass STUDENT = eINSTANCE.getStudent();

		/**
		 * The meta object literal for the '{@link eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.impl.TeacherImpl <em>Teacher</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.impl.TeacherImpl
		 * @see eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.impl.SchoolPackageImpl#getTeacher()
		 * @generated
		 */
		EClass TEACHER = eINSTANCE.getTeacher();

		/**
		 * The meta object literal for the '<em><b>Teach</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEACHER__TEACH = eINSTANCE.getTeacher_Teach();

		/**
		 * The meta object literal for the '{@link eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.impl.ContainerImpl <em>Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.impl.ContainerImpl
		 * @see eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.impl.SchoolPackageImpl#getContainer()
		 * @generated
		 */
		EClass CONTAINER = eINSTANCE.getContainer();

		/**
		 * The meta object literal for the '<em><b>People</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER__PEOPLE = eINSTANCE.getContainer_People();

	}

} //SchoolPackage
