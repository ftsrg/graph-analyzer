package hu.bme.mit.mba.base.data;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class MatrixData<R, C, V> implements BaseData {

    protected Table<R, C, V> values;

    public MatrixData() {
	values = HashBasedTable.create();
    }

    @Override
    public void clear() {
	values.clear();
    }

    public V get(Object rowKey, Object columnKey) {
	return values.get(rowKey, columnKey);
    }

    public V put(R rowKey, C columnKey, V value) {
	return values.put(rowKey, columnKey, value);
    }

    public void putAll(Table<? extends R, ? extends C, ? extends V> table) {
	values.putAll(table);
    }

    public Table<R, C, V> getValues() {
	return values;
    }

    @Override
    public String toString() {
	return "MatrixData [values=" + values + "]";
    }

}
