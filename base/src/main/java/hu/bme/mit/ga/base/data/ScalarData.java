package hu.bme.mit.ga.base.data;

public class ScalarData<V extends Number> implements BaseData {

    protected V value;

    public V getValue() {
        return value;
    }

    public void setValue(final V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ScalarData [value=" + value + "]";
    }

}
