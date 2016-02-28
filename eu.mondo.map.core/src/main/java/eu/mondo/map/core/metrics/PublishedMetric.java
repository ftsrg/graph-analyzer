package eu.mondo.map.core.metrics;


public class PublishedMetric{

	protected String name;
	protected String value;
	
	public PublishedMetric(final String value, final String name) {
		this.value = value;
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
