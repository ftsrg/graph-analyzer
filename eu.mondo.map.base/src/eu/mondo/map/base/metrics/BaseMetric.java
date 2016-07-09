package eu.mondo.map.base.metrics;

import eu.mondo.map.base.data.BaseData;

public abstract class BaseMetric<D extends BaseData> implements Metric {

	protected D data;
	protected String name;
	protected String defaultName;

	public BaseMetric(final String defaultName) {
		this.defaultName = defaultName;
		name = defaultName;
	}

	@Override
	public String getDefaultName() {
		return defaultName;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void clear() {
		data.clear();
	}

	public D getData() {
		return data;
	}

}
