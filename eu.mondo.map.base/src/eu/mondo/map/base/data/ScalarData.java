package eu.mondo.map.base.data;

public class ScalarData<T extends Number> implements BaseData {

	protected T value;

	public T getValue() {
		return value;
	}

	public void setValue(final T value) {
		this.value = value;
	}

	@Override
	public void clear() {
		value = null;
	}

}
