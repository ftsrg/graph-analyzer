package eu.mondo.map.core.metrics;

import java.util.Iterator;

public abstract class Metric {

	protected String name;
	protected String defaultName;

	public Metric(final String defaultName) {
		this.defaultName = defaultName;
		name = defaultName;
	}

	public abstract void clear();

	public abstract void calculate(final Iterator<Object> iterator);

	public abstract void calculate(final Object node);

	public String getDefaultName() {
		return defaultName;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

}
