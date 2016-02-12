package eu.mondo.map.core.metrics;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public abstract class MultitypedScalarMetric<T, K, V> extends TypedScalarMetric<K, V> {

	protected Table<T, K, V> multitypedValues;

	public MultitypedScalarMetric() {
		multitypedValues = HashBasedTable.create();
	}

	public Table<T, K, V> getMultitypedValues() {
		return multitypedValues;
	}

	public void setMultitypedValues(Table<T, K, V> multitypedValues) {
		this.multitypedValues = multitypedValues;
	}

	public V get(Object rowKey, Object columnKey) {
		return multitypedValues.get(rowKey, columnKey);
	}

	public V put(T rowKey, K columnKey, V value) {
		return multitypedValues.put(rowKey, columnKey, value);
	}

	public void putAll(Table<? extends T, ? extends K, ? extends V> table) {
		multitypedValues.putAll(table);
	}

	@Override
	public String toString() {
		return "MultitypedAggregatedMetric [multitypedValues=" + multitypedValues + "]";
	}

}
