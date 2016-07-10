package eu.mondo.map.base.tests;

import org.junit.Assert;

import eu.mondo.map.base.data.MapData;

public class MapDataTesterUtil {

	public static <K, V extends Number> void checkKeysSize(int expected, final MapData<K, V> data) {
		Assert.assertEquals(expected, data.getValues().keySet().size());
	}

}
