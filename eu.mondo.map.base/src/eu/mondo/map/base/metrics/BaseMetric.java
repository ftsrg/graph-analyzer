package eu.mondo.map.base.metrics;

import org.apache.log4j.Logger;

public abstract class BaseMetric implements Metric {

	protected String name;
	protected String defaultName;

	protected final Logger logger = Logger.getLogger(this.getClass());

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
