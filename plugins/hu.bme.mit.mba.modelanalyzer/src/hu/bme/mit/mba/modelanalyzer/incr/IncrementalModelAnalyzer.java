package hu.bme.mit.mba.modelanalyzer.incr;

import com.google.common.base.Preconditions;

import hu.bme.mit.mba.modeladapters.ModelAdapter;
import hu.bme.mit.mba.modelanalyzer.ModelAnalyzer;
import hu.bme.mit.mba.modelmetrics.ModelMetric;
import hu.bme.mit.mba.modelmetrics.impl.ModelMetrics;
import hu.bme.mit.mba.modelmetrics.incr.IncrementalModelEvaluator;

public class IncrementalModelAnalyzer extends ModelAnalyzer {

  @Override
  public IncrementalModelAnalyzer clear() {
    super.clear();
    return this;
  }

  @Override
  public IncrementalModelAnalyzer use(ModelMetrics metric) {
    super.use(metric);
    return this;
  }

  @Override
  public IncrementalModelAnalyzer use(ModelMetrics metric, String name) {
    super.use(metric, name);
    return this;
  }

  @Override
  protected void useMetric(ModelMetrics metric, String name) {
    checkNewMetric(metric);
    ModelMetric metricObj = metric.instantiate();
    Preconditions.checkState(metricObj instanceof IncrementalModelEvaluator,
        "Metric " + metric.toString() + " must implement IncrementalModelEvaluator.");

    addMetric(metric, metricObj, name);
  }

  @Override
  public IncrementalModelAnalyzer useAll() {
    super.useAll();
    return this;
  }

  @Override
  public IncrementalModelAnalyzer omit(ModelMetrics metric) {
    super.omit(metric);
    return this;
  }

  @Override
  public <N, T> IncrementalModelAnalyzer evaluate(ModelAdapter<N, T> adapter) {
    super.evaluate(adapter);
    return this;
  }

  @Override
  public <N, T> IncrementalModelAnalyzer evaluate(ModelAdapter<N, T> adapter, ModelMetrics metric) {
    super.evaluate(adapter, metric);
    return this;
  }

}
