package eu.mondo.map.core.metrics;

public class PublishedMetric {

	protected String name;
	protected String value;

	public PublishedMetric(final String value, final String name) {
		this.value = value;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setValue(final String value) {
		this.value = value;
	}

}
