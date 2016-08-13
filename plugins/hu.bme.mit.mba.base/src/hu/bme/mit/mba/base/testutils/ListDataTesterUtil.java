package hu.bme.mit.mba.base.testutils;

import org.junit.Assert;

import hu.bme.mit.mba.base.data.ListData;

public class ListDataTesterUtil {

	public static <V extends Number> void checkSize(int expected, final ListData<V> data) {
		Assert.assertEquals(expected, data.size());
	}

	public static <V extends Number> void checkAppearance(int expecpectedAppearance, final V value,
			final ListData<V> data) {
		int appearance = 0;
		for (int i = 0; i < data.size(); i++) {
			if (value.equals(data.get(i))) {
				appearance++;
			}
		}
		Assert.assertEquals(expecpectedAppearance, appearance);
	}

	public static <V extends Number> void checkMinAppearance(int expecpectedMinAppearance, final V value,
			final ListData<V> data) {
		int appearance = 0;
		for (int i = 0; i < data.size(); i++) {
			if (value.equals(data.get(i))) {
				appearance++;
			}
		}
		Assert.assertTrue(appearance >= expecpectedMinAppearance);
	}

	public static <V extends Number> void checkMaxAppearance(int expecpectedMaxAppearance, final V value,
			final ListData<V> data) {
		int appearance = 0;
		for (int i = 0; i < data.size(); i++) {
			if (value.equals(data.get(i))) {
				appearance++;
			}
		}
		Assert.assertTrue(appearance <= expecpectedMaxAppearance);
	}

}
