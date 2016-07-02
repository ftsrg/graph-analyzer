package eu.mondo.map.base.metrics;

public interface Metric {

	/**
	 * Clears the value of metric.
	 */
	public void clear();

	public String getDefaultName();

	public String getName();

	public void setName(final String name);

}
