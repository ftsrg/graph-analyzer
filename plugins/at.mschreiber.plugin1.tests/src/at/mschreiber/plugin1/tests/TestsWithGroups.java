package at.mschreiber.plugin1.tests;

import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import at.mschreiber.plugin1.MyService;

public class TestsWithGroups {
	
	private MyService myService;

	@BeforeGroups(groups = {"negativeValues", "positiveValues"})
	public void init() {
		myService = new MyService();
	}
	
	@Test(groups= "negativeValues")
	public void testWithBothNegative() {
		assert myService.sum(-1, -4) == -5;
	}

	@Test(groups= "positiveValues")
	public void testWithBothPositiv() {
		assert myService.sum(1, 4) == 5;
	}

	@Test(groups = {"negativeValues", "positiveValues"})
	public void testWithNegativeAndPositiv() {
		assert myService.sum(1, -4) == -3;
	}


}
