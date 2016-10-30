package hu.bme.mit.mba.base.testutils;

import org.junit.Assert;

import hu.bme.mit.mba.base.data.ListData;

public class ListDataTesterUtil {

    public static <V extends Number> void checkSize(int expected, final ListData<V> data) {
        Assert.assertEquals(expected, data.size());
    }

    public static <V extends Number> void checkAppearance(int expectedAppearance, final V value,
            final ListData<V> data) {
        int appearance = 0;
        for (int i = 0; i < data.size(); i++) {
            if (value.equals(data.get(i))) {
                appearance++;
            }
        }
        Assert.assertEquals(expectedAppearance, appearance);
    }

    public static <V extends Number> void checkMinAppearance(int expectedMinAppearance, final V value,
            final ListData<V> data) {
        int appearance = 0;
        for (int i = 0; i < data.size(); i++) {
            if (value.equals(data.get(i))) {
                appearance++;
            }
        }
        Assert.assertTrue(appearance >= expectedMinAppearance);
    }

    public static <V extends Number> void checkMaxAppearance(int expectedMaxAppearance, final V value,
            final ListData<V> data) {
        int appearance = 0;
        for (int i = 0; i < data.size(); i++) {
            if (value.equals(data.get(i))) {
                appearance++;
            }
        }
        Assert.assertTrue(appearance <= expectedMaxAppearance);
    }

}
