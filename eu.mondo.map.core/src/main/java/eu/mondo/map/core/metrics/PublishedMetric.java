package eu.mondo.map.core.metrics;

import com.google.common.base.Optional;

public class PublishedMetric {

	protected String category;
	protected Optional<String> instance = Optional.absent();
	protected Optional<Integer> index = Optional.absent();
	protected Optional<Number> value = Optional.absent();

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
		this.value = Optional.fromNullable(value);
		return this;
	}

	public String getCategory() {
		return category;
	}

	public Optional<String> getInstance() {
		return instance;
	}

	public Optional<Integer> getIndex() {
		return index;
	}
	
	public String getIndexString() {
		return index.or(supplier);
	}

	public Optional<Number> getValue() {
		return value;
	}

}
