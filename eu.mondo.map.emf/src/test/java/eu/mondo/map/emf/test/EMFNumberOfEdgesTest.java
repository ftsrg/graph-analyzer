package eu.mondo.map.emf.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import eu.mondo.map.emf.metrics.EMFNumberOfTypedEdges;
import eu.mondo.map.emf.metrics.EMFTypedDegreeList;
import eu.mondo.map.modelmetrics.scalar.NumberOfEdges;

public class EMFNumberOfEdgesTest extends EMFMetricTester {

	protected static Map<String, Integer> expectedEdges;
	protected static Table<String, String, Integer> expectedTypedEdges = HashBasedTable.create();

	public EMFNumberOfEdgesTest(String modelPath, String key) {
		super(modelPath, key);
	}

	@BeforeClass
	public static void initFields() {
		expectedEdges = new HashMap<String, Integer>();
		expectedEdges.put(POSLENGTH, 1);
		expectedEdges.put(CONNECTEDSEGMENTS, 24);
		expectedEdges.put(ROUTESENSOR, 8);
		expectedEdges.put(SEMAPHORENEIGHBOR, 13);
		expectedEdges.put(SWITCHSENSOR, 1);
		expectedEdges.put(SWITCHSET, 8);

		expectedTypedEdges.clear();
		expectedTypedEdges.put(POSLENGTH, ELEMENTS, 1);

		expectedTypedEdges.put(CONNECTEDSEGMENTS, ELEMENTS, 6);
		expectedTypedEdges.put(CONNECTEDSEGMENTS, MONITOREDBY, 6);
		expectedTypedEdges.put(CONNECTEDSEGMENTS, CONNECTSTO, 5);
		expectedTypedEdges.put(CONNECTEDSEGMENTS, MONITORS, 6);
		expectedTypedEdges.put(CONNECTEDSEGMENTS, SENSORS, 1);

		expectedTypedEdges.put(ROUTESENSOR, FOLLOWS, 1);
		expectedTypedEdges.put(ROUTESENSOR, TARGET, 1);
		expectedTypedEdges.put(ROUTESENSOR, MONITOREDBY, 1);
		expectedTypedEdges.put(ROUTESENSOR, ELEMENTS, 1);
		expectedTypedEdges.put(ROUTESENSOR, POSITIONS, 1);
		expectedTypedEdges.put(ROUTESENSOR, MONITORS, 1);
		expectedTypedEdges.put(ROUTESENSOR, ROUTE_REFERENCE, 1);

		expectedTypedEdges.put(SEMAPHORENEIGHBOR, EXIT, 1);
		expectedTypedEdges.put(SEMAPHORENEIGHBOR, GATHERS, 2);
		expectedTypedEdges.put(SEMAPHORENEIGHBOR, ELEMENTS, 2);
		expectedTypedEdges.put(SEMAPHORENEIGHBOR, MONITOREDBY, 2);
		expectedTypedEdges.put(SEMAPHORENEIGHBOR, CONNECTSTO, 1);
		expectedTypedEdges.put(SEMAPHORENEIGHBOR, MONITORS, 2);
		expectedTypedEdges.put(SEMAPHORENEIGHBOR, SENSORS, 2);

		expectedTypedEdges.put(SWITCHSENSOR, ELEMENTS, 1);

		expectedTypedEdges.put(SWITCHSET, ENTRY, 1);
		expectedTypedEdges.put(SWITCHSET, TARGET, 1);
		expectedTypedEdges.put(SWITCHSET, FOLLOWS, 1);
		expectedTypedEdges.put(SWITCHSET, ROUTE_REFERENCE, 1);
		expectedTypedEdges.put(SWITCHSET, ELEMENTS, 2);
		expectedTypedEdges.put(SWITCHSET, SEMAPHORES, 1);
		expectedTypedEdges.put(SWITCHSET, POSITIONS, 1);

	}

	@Before
	public void init() {
		readModel();
	}

	@Test
	public void testNumberOfEdgesFromDegrees() {
		NumberOfEdges edges = new NumberOfEdges();
		EMFTypedDegreeList degreeList = new EMFTypedDegreeList();
		degreeList.calculate(container.eAllContents());
		edges.calculate(degreeList);
		Assert.assertEquals(expectedEdges.get(currentKey), edges.getValue());
	}

//	@Test
//	public void testNumberOfEdgesFromModel() {
//		EMFNumberOfTypedEdges typedEdges = new EMFNumberOfTypedEdges();
//		typedEdges.calculate(container.eAllContents());
//		Assert.assertEquals(expectedEdges.get(currentKey), typedEdges.getValue());
//	}

	@Test
	public void testNumberOfTypedEdgesFromModel() {
		EMFNumberOfTypedEdges edges = new EMFNumberOfTypedEdges();
		edges.calculate(container.eAllContents());
		for (String keyType : expectedTypedEdges.rowMap().get(currentKey).keySet()) {
			Assert.assertTrue(keyType, edges.getValues().containsKey(keyType));
			Assert.assertEquals(keyType, expectedTypedEdges.get(currentKey, keyType), edges
					.getValues().get(keyType));
		}
	}

}
