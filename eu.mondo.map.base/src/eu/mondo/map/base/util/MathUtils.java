package eu.mondo.map.base.util;

import java.util.List;

public class MathUtils {

	public static double sumDouble(final List<? extends Number> list) {
		double sum = 0;
		for (int i = 0; i < list.size(); i++) {
			sum += list.get(i).doubleValue();
		}
		return sum;
	}

	public static int sumInt(final List<? extends Number> list) {
		int sum = 0;
		for (int i = 0; i < list.size(); i++) {
			sum += list.get(i).intValue();
		}
		return sum;
	}
}
