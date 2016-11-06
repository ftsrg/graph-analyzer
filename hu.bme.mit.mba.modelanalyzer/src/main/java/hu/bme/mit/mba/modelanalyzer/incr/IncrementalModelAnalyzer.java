package hu.bme.mit.mba.modelanalyzer.incr;

import com.google.common.base.Preconditions;

import hu.bme.mit.mba.modeladapters.ModelAdapter;
import hu.bme.mit.mba.modelanalyzer.ModelAnalyzer;
import hu.bme.mit.mba.modelmetrics.ModelMetric;
import hu.bme.mit.mba.modelmetrics.impl.ModelMetricsEnum;
import hu.bme.mit.mba.modelmetrics.incr.IncrementalModelEvaluator;

public class IncrementalModelAnalyzer extends ModelAnalyzer {

  @Override
  public IncrementalModelAnalyzer clear() {
    super.clear();
    return this;
  }

  @Override
  public IncrementalModelAnalyzer use(ModelMetricsEnum metric) {
    super.use(metric);
    return this;
  }

  @Override
  public IncrementalModelAnalyzer use(ModelMetricsEnum metric, String name) {
    super.use(metric, name);
    return this;
  }

  @Override
  protected void useMetric(ModelMetricsEnum metric, String name) {
    checkNewMetric(metric);
    ModelMetric metricObj;
    try {
      metricObj = metric.instantiate();
    } catch (InstantiationException | IllegalAccessException e) {
      throw new IllegalArgumentException("The parameter " + metric + " cannot be instantiated.", e);
    }
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
  public IncrementalModelAnalyzer omit(ModelMetricsEnum metric) {
    super.omit(metric);
    return this;
  }

  @Override
  public <N, T> IncrementalModelAnalyzer evaluate(ModelAdapter<N, T> adapter) {
    super.evaluate(adapter);
    return this;
  }

  @Override
  public <N, T> IncrementalModelAnalyzer evaluate(ModelAdapter<N, T> adapter, ModelMetricsEnum metric) {
    super.evaluate(adapter, metric);
    return this;
  }

}
