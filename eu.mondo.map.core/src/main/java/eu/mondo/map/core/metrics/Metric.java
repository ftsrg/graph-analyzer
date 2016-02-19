package eu.mondo.map.core.metrics;

public abstract class Metric {

	protected String name;
	protected String defaultName;

	public Metric(String defaultName) {
		this.defaultName = defaultName;
		this.name = defaultName;
	}

	public abstract void clear();

//	public String getName() {
//		return name;
//	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDefaultName() {
		return defaultName;
	}

}
