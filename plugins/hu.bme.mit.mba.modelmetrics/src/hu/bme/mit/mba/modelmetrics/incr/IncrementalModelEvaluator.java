package hu.bme.mit.mba.modelmetrics.incr;

import hu.bme.mit.mba.modeladapters.ModelAdapter;
import hu.bme.mit.mba.modelmetrics.ModelMetric;

public interface IncrementalModelEvaluator extends ModelMetric {

    public <N, T> void reevaluateNewEdge(final ModelAdapter<N, T> adapter, final T type, final N sourceNode,
	    final N targetNode);

}
