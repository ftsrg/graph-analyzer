package eu.mondo.map.emf.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import eu.mondo.map.core.metrics.models.scalar.NumberOfNodes;
import eu.mondo.map.emf.metrics.EMFDegreeList;

public class EMFNumberOfNodesTester extends EMFMetricTester {

	protected static Map<String, Integer> expectedNodes;
	protected static Table<String, String, Integer> expectedTypedNodes = HashBasedTable.create();

	public EMFNumberOfNodesTester(String modelPath, String key) {
		super(modelPath, key);
	}

	@BeforeClass
	public static void initFields() {
		expectedNodes = new HashMap<String, Integer>();
		expectedNodes.put(CONNECTEDSEGMENTS, 8);
		expectedNodes.put(ROUTESENSOR, 5);
		expectedNodes.put(SEMAPHORENEIGHBOR, 8);
		expectedNodes.put(SWITCHSENSOR, 2);
		expectedNodes.put(SWITCHSET, 6);

		expectedTypedNodes.clear();
		expectedTypedNodes.put(CONNECTEDSEGMENTS, REGION, 1);
		expectedTypedNodes.put(CONNECTEDSEGMENTS, SEGMENT, 6);
		expectedTypedNodes.put(CONNECTEDSEGMENTS, SENSOR, 1);

		expectedTypedNodes.put(ROUTESENSOR, ROUTE, 1);
		expectedTypedNodes.put(ROUTESENSOR, SWITCHPOSITION, 1);
		expectedTypedNodes.put(ROUTESENSOR, REGION, 1);
		expectedTypedNodes.put(ROUTESENSOR, SWITCH, 1);
		expectedTypedNodes.put(ROUTESENSOR, SENSOR, 1);

		expectedTypedNodes.put(SEMAPHORENEIGHBOR, ROUTE, 2);
		expectedTypedNodes.put(SEMAPHORENEIGHBOR, SEGMENT, 2);
		expectedTypedNodes.put(SEMAPHORENEIGHBOR, REGION, 1);
		expectedTypedNodes.put(SEMAPHORENEIGHBOR, SEMAPHORE, 1);
		expectedTypedNodes.put(SEMAPHORENEIGHBOR, SENSOR, 2);

		expectedTypedNodes.put(SWITCHSENSOR, REGION, 1);
		expectedTypedNodes.put(SWITCHSENSOR, SWITCH, 1);

		expectedTypedNodes.put(SWITCHSET, ROUTE, 1);
		expectedTypedNodes.put(SWITCHSET, REGION, 1);
		expectedTypedNodes.put(SWITCHSET, SWITCHPOSITION, 1);
		expectedTypedNodes.put(SWITCHSET, SEGMENT, 1);
		expectedTypedNodes.put(SWITCHSET, SEMAPHORE, 1);
		expectedTypedNodes.put(SWITCHSET, SWITCH, 1);
	}

	@Before
	public void init() {
		readModel();
	}

	@Test
	public void testNumberOfNodesFromDegrees() {
		NumberOfNodes nodes = new NumberOfNodes();
		EMFDegreeList degreeList = new EMFDegreeList();
		degreeList.calculate(container.eAllContents());
		nodes.calculate(degreeList);
		Assert.assertEquals(expectedNodes.get(currentKey), nodes.getValue());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNumberOfTypedNodesFromDegreesException() {
		NumberOfNodes nodes = new NumberOfNodes();
		EMFDegreeList degreeList = new EMFDegreeList();
		degreeList.calculate(container.eAllContents());
		nodes.calculateOfTypes(degreeList);
	}

	@Test
	public void testNumberOfTypedNodesFromDegrees() {
		NumberOfNodes nodes = new NumberOfNodes();
		EMFDegreeList degreeList = new EMFDegreeList();
		degreeList.calculateOfTypes(container.eAllContents());
		nodes.calculateOfTypes(degreeList);
		for (String keyType : expectedTypedNodes.rowMap().get(currentKey).keySet()) {
			Assert.assertTrue(keyType, degreeList.getTypedValues().containsKey(keyType));
			Assert.assertTrue(keyType, nodes.getTypedValues().containsKey(keyType));
			Assert.assertEquals(keyType, expectedTypedNodes.get(currentKey, keyType), nodes
					.getTypedValues().get(keyType));
		}
	}
}
