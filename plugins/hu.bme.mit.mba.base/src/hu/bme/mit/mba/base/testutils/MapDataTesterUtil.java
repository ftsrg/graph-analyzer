package hu.bme.mit.mba.base.testutils;

import org.junit.Assert;

import hu.bme.mit.mba.base.data.MapData;

public class MapDataTesterUtil {

    public static <K, V extends Number> void checkKeysSize(int expected, final MapData<K, V> data) {
	Assert.assertEquals(expected, data.getValues().keySet().size());
    }

    public static <K, V extends Number> void checkValue(final MapData<K, V> data, K key, V expected) {
	Assert.assertEquals(expected, data.getValues().get(key));
    }

}
