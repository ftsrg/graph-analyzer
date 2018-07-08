package hu.bme.mit.mba.base.data;

import java.util.HashMap;
import java.util.Map;

public class MapData<K, V extends Number> implements BaseData {

    protected Map<K, V> values;

    public MapData() {
        values = new HashMap<K, V>();
    }

    public void setTypedValues(Map<K, V> typedValues) {
        this.values = typedValues;
    }

    public Map<K, V> getValues() {
        return values;
    }

    public boolean containsKey(Object arg0) {
        return values.containsKey(arg0);
    }

    public boolean containsValue(Object arg0) {
        return values.containsValue(arg0);
    }

    public V get(Object arg0) {
        return values.get(arg0);
    }

    public V put(K arg0, V arg1) {
        return values.put(arg0, arg1);
    }

    public V remove(Object arg0) {
        return values.remove(arg0);
    }

    public int size() {
        return values.size();
    }

    @Override
    public String toString() {
        return "MapData [values=" + values + "]";
    }

}
