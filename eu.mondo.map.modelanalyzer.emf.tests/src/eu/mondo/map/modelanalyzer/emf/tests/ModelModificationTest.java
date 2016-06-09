package eu.mondo.map.modelanalyzer.emf.tests;

import org.junit.Test;

import eu.mondo.map.modelanalyzer.emf.ModelModificationObserver;
import eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.Container;
import eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.SchoolFactory;
import eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.Student;
import eu.mondo.map.modelanalyzer.emf.tests.examplemodel.school.impl.SchoolPackageImpl;

public class ModelModificationTest {

	@Test
	public void testNewObjectTest() {
		SchoolPackageImpl.init();
		Container container = SchoolFactory.eINSTANCE.createContainer();

		container.eAdapters().add(new ModelModificationObserver());

		Student student = SchoolFactory.eINSTANCE.createStudent();
		container.getPeople().add(student);
	}
}
