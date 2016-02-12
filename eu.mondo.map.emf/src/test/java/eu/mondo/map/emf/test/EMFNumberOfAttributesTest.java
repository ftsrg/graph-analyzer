package eu.mondo.map.emf.test;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class EMFNumberOfAttributesTest extends EMFMetricTester {

	protected static Table<String, String, Integer> expectedAttributes = HashBasedTable.create();

	public EMFNumberOfAttributesTest(String modelPath, String key) {
		super(modelPath, key);
	}

	public static void initFields() {
	}

}
