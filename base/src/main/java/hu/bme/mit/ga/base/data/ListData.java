package hu.bme.mit.ga.base.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.google.common.base.Preconditions.checkState;

public class ListData<V extends Number> implements BaseData {

    protected List<V> values;

    public ListData() {
        this.values = new ArrayList<>();
    }

    public boolean add(final V e) {
        return values.add(e);
    }

    public boolean addAll(final Collection<? extends V> c) {
        return values.addAll(c);
    }

    public V get(final int index) {
        return values.get(index);
    }

    public List<V> getValues() {
        return values;
    }

    public void setMetricValues(final List<V> metricValues) {
        this.values = metricValues;
    }

    public int size() {
        return values.size();
    }

    public V last() {
        checkState(!values.isEmpty());
        return values.get(values.size() - 1);
    }

    @Override
    public String toString() {
        return "ListData [values=" + values + "]";
    }

}
