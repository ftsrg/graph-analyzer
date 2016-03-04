package eu.mondo.map.core.util;

import java.util.List;

public class MathUtils {

	public static int sumInt(List<? extends Number> list) {
		int sum = 0;
		for (int i = 0; i < list.size(); i++) {
			sum += list.get(i).intValue();
		}
		return sum;
	}

	public static double sumDouble(List<? extends Number> list) {
		double sum = 0;
		for (int i = 0; i < list.size(); i++) {
			sum += list.get(i).doubleValue();
		}
		return sum;
	}
}
