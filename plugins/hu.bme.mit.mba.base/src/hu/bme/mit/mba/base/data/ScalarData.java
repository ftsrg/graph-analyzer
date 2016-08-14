package hu.bme.mit.mba.base.data;

public class ScalarData<V extends Number> implements BaseData {

    protected V value;

    public V getValue() {
        return value;
    }

    public void setValue(final V value) {
        this.value = value;
    }

    @Override
    public void clear() {
        value = null;
    }

    @Override
    public String toString() {
        return "ScalarData [value=" + value + "]";
    }

}
