package eu.mondo.map.core.metrics;

import com.google.common.base.Optional;

public class PublishedMetric {

	protected String category;
	protected Optional<String> instance = Optional.absent();
	protected Optional<Integer> index = Optional.absent();
	protected Number value;

	public PublishedMetric(String category) {
		this.category = category;
	}

	public PublishedMetric setInstance(String instance) {
		this.instance = Optional.fromNullable(instance);
		return this;
	}

	public PublishedMetric setIndex(Integer index) {
		this.index = Optional.fromNullable(index);
		return this;
	}

	public PublishedMetric setValue(Number value) {
		this.value = value;
		return this;
	}

	public String getCategory() {
		return category;
	}

	public String getInstanceString() {
		return instance.isPresent() ? instance.get().toString() : "";
	}

	public String getIndexString() {
		return index.isPresent() ? index.get().toString() : "";
	}

	public Number getValue() {
		return value;
	}

}
