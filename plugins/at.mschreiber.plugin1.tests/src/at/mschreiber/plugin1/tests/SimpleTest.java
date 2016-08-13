package at.mschreiber.plugin1.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import at.mschreiber.plugin1.MyService;

public class SimpleTest {

	private MyService myService;

	@BeforeClass
	public void init() {
		myService = new MyService();
	}
	
	@Test
	public void testAdditionWithPositivValues() {
		assert myService.sum(4, 5) == 9 : "Sum 4+5 must be 9";
	}
	
	
}
