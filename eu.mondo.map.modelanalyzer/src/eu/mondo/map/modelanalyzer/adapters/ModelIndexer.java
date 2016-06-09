package eu.mondo.map.modelanalyzer.adapters;

import java.util.Set;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class ModelIndexer {

	protected Table<Object, Object, Set<Object>> adjacency;

	public ModelIndexer() {
		adjacency = HashBasedTable.create();
	}

	public Table<Object, Object, Set<Object>> getAdjacency() {
		return adjacency;
	}

	public boolean isAdjacent(final Object source, final Object target) {
		return adjacency.contains(source, target);
	}

	public boolean isAdjacentBidirectional(final Object source, final Object target) {
		return adjacency.contains(source, target) || adjacency.contains(target, source);
	}

}
