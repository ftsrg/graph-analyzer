package eu.mondo.map.core.metrics;

public abstract class Metric {

	protected String name;
	protected String defaultName;

	public Metric(final String defaultName) {
		this.defaultName = defaultName;
		this.name = defaultName;
	}

	public abstract void clear();

	public String getDefaultName() {
		return defaultName;
	}

	public void setName(final String name) {
		this.name = name;
	}

}
