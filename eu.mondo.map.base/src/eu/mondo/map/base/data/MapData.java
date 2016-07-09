package eu.mondo.map.base.data;

import java.util.HashMap;
import java.util.Map;

public class MapData<K, V extends Number> implements BaseData {

	public MapData() {
		typedValues = new HashMap<K, V>();
	}

	protected Map<K, V> typedValues;

	public Map<K, V> getValues() {
		return typedValues;
	}

	public void setTypedValues(Map<K, V> typedValues) {
		this.typedValues = typedValues;
	}

	@Override
	public void clear() {
		typedValues.clear();
	}

	public boolean containsKey(Object arg0) {
		return typedValues.containsKey(arg0);
	}

	public boolean containsValue(Object arg0) {
		return typedValues.containsValue(arg0);
	}

	public V get(Object arg0) {
		return typedValues.get(arg0);
	}

	public V put(K arg0, V arg1) {
		return typedValues.put(arg0, arg1);
	}

	public V remove(Object arg0) {
		return typedValues.remove(arg0);
	}

	public int size() {
		return typedValues.size();
	}

}
