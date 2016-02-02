package eu.mondo.map.core.util;

import java.util.List;

// TODO look for a 3rd party implementation
public class ListUtil {

	public static Integer summarize(final List<Integer> elements) {
		int sum = 0;
		for (Integer el : elements) {
			sum += el;
		}
		return sum;
	}
}
