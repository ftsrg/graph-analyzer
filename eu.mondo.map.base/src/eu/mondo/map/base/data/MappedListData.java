package eu.mondo.map.base.data;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimap;

public class MappedListData<Type, Value extends Number> implements BaseData {

	protected ListMultimap<Type, Value> typedValues;

	public MappedListData() {
		typedValues = ArrayListMultimap.create();
	}

	public ListMultimap<Type, Value> getValues() {
		return typedValues;
	}

	public void setTypedValues(ListMultimap<Type, Value> typedValues) {
		this.typedValues = typedValues;
	}

	@Override
	public void clear() {
		typedValues.clear();
	}

	public boolean put(Type key, Value value) {
		return typedValues.put(key, value);
	}

	public boolean putAll(Multimap<? extends Type, ? extends Value> multimap) {
		return typedValues.putAll(multimap);
	}

}
