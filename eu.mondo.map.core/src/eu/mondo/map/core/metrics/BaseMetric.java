package eu.mondo.map.core.metrics;

public abstract class BaseMetric implements Metric {

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

}
