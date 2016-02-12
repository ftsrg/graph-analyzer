package eu.mondo.map.emf.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Table;
import com.google.common.primitives.Ints;

import eu.mondo.map.emf.metrics.EMFDegreeList;
import groovy.util.GroovyCollections;

public class EMFDegreeTester extends EMFMetricTester {

	protected static ListMultimap<String, Integer> expectedDegrees = ArrayListMultimap.create();
	protected static Table<String, String, Integer> expectedTypedDegrees = HashBasedTable.create();

	public EMFDegreeTester(String modelPath, String key) {
		super(modelPath, key);
	}

	@BeforeClass
	public static void initFields() {
		expectedDegrees.clear();
		expectedDegrees.putAll(CONNECTEDSEGMENTS, Ints.asList(7, 2, 2, 2, 2, 2, 1, 6));
		expectedDegrees.putAll(ROUTESENSOR, Ints.asList(1, 2, 2, 2, 1));
		expectedDegrees.putAll(SEMAPHORENEIGHBOR, Ints.asList(2, 1, 4, 3, 0, 1, 1, 1));
		expectedDegrees.putAll(SWITCHSENSOR, Ints.asList(1, 0));
		expectedDegrees.putAll(SWITCHSET, Ints.asList(2, 2, 2, 1, 0, 1));

		expectedTypedDegrees.clear();
		expectedTypedDegrees.put(CONNECTEDSEGMENTS, REGION, 7);
		expectedTypedDegrees.put(CONNECTEDSEGMENTS, SEGMENT, 11);
		expectedTypedDegrees.put(CONNECTEDSEGMENTS, SENSOR, 6);

		expectedTypedDegrees.put(ROUTESENSOR, ROUTE, 1);
		expectedTypedDegrees.put(ROUTESENSOR, REGION, 2);
		expectedTypedDegrees.put(ROUTESENSOR, SWITCHPOSITION, 2);
		expectedTypedDegrees.put(ROUTESENSOR, SWITCH, 2);
		expectedTypedDegrees.put(ROUTESENSOR, SENSOR, 1);

		expectedTypedDegrees.put(SEMAPHORENEIGHBOR, ROUTE, 3);
		expectedTypedDegrees.put(SEMAPHORENEIGHBOR, REGION, 4);
		expectedTypedDegrees.put(SEMAPHORENEIGHBOR, SEMAPHORE, 0);
		expectedTypedDegrees.put(SEMAPHORENEIGHBOR, SEGMENT, 4);
		expectedTypedDegrees.put(SEMAPHORENEIGHBOR, SENSOR, 2);

		expectedTypedDegrees.put(SWITCHSENSOR, REGION, 1);
		expectedTypedDegrees.put(SWITCHSENSOR, SWITCH, 0);

		expectedTypedDegrees.put(SWITCHSET, ROUTE, 2);
		expectedTypedDegrees.put(SWITCHSET, REGION, 2);
		expectedTypedDegrees.put(SWITCHSET, SEMAPHORE, 0);
		expectedTypedDegrees.put(SWITCHSET, SWITCHPOSITION, 2);
		expectedTypedDegrees.put(SWITCHSET, SEGMENT, 1);
		expectedTypedDegrees.put(SWITCHSET, SWITCH, 1);

	}

	@Before
	public void init() {
		readModel();
	}

	@Test
	public void testDegreeList() {
		EMFDegreeList degreeList = new EMFDegreeList();
		degreeList.calculate(container.eAllContents());
		Assert.assertArrayEquals(degreeList.getValues().toArray(), expectedDegrees.get(currentKey)
				.toArray());
	}

	@Test
	public void testTypedDegreeList() {
		EMFDegreeList degreeList = new EMFDegreeList();
		degreeList.calculateOfTypes(container.eAllContents());
		for (String keyType : expectedTypedDegrees.rowMap().get(currentKey).keySet()) {
			Assert.assertTrue(degreeList.getTypedValues().containsKey(keyType));
			Assert.assertEquals(
					keyType,
					expectedTypedDegrees.get(currentKey, keyType),
					(Integer) GroovyCollections.sum(degreeList.getTypedValues()
							.get(keyType).toArray()));
		}

	}
}
