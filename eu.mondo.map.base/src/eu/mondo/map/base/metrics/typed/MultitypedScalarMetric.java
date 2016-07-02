package eu.mondo.map.base.metrics.typed;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import eu.mondo.map.base.metrics.BaseMetric;

public abstract class MultitypedScalarMetric<Type, Key, Value> extends BaseMetric {

	public MultitypedScalarMetric(String defaultName) {
		super(defaultName);
		multitypedValues = HashBasedTable.create();
	}

	protected Table<Type, Key, Value> multitypedValues;

	@Override
	public void clear() {
		multitypedValues.clear();
	}

	public Table<Type, Key, Value> getMultitypedValues() {
		return multitypedValues;
	}

	public void setMultitypedValues(Table<Type, Key, Value> multitypedValues) {
		this.multitypedValues = multitypedValues;
	}

	public Value get(Object rowKey, Object columnKey) {
		return multitypedValues.get(rowKey, columnKey);
	}

	public Value put(Type rowKey, Key columnKey, Value value) {
		return multitypedValues.put(rowKey, columnKey, value);
	}

	public void putAll(Table<? extends Type, ? extends Key, ? extends Value> table) {
		multitypedValues.putAll(table);
	}

	@Override
	public String toString() {
		return "MultitypedAggregatedMetric [multitypedValues=" + multitypedValues + "]";
	}

}
