package hu.bme.mit.ga.base.testutils;

import hu.bme.mit.ga.base.data.ListData;
import org.junit.Assert;

public class ListDataTesterUtil {

    /**
     * Check if the size of {@code data} is equal to {@code expected}.
     *
     * @param expected
     * @param data
     */
    public static <V extends Number> void checkSize(int expected, final ListData<V> data) {
        Assert.assertEquals(expected, data.size());
    }

    /**
     * Check if the number of appearances of {@code value} in {@code data} is equal to {@code expectedAppearance}.
     *
     * @param expectedAppearance
     * @param value
     * @param data
     */
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
