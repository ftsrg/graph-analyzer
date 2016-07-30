package eu.mondo.map.modelmetrics.tests;

import java.util.function.Consumer;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import eu.mondo.map.base.data.BaseData;
import eu.mondo.map.modeladapters.tests.CustomTypedModelAdapter;
import eu.mondo.map.modelanalyzer.ModelAnalyzer;
import eu.mondo.map.modelmetrics.ModelMetric;
import eu.mondo.map.modelmetrics.impl.ModelMetrics;
import eu.mondo.map.tests.model.TestModel;
import eu.mondo.map.tests.model.TestModelTypes;

public abstract class ModelMetricTest<D extends BaseData, M extends ModelMetric> {

    protected TestModel model;
    protected M metric;
    protected D data;
    protected CustomTypedModelAdapter adapter;

    public abstract ModelMetrics getMetric();

    public abstract int getNumberOfEvaluatedNodes();

    @DataProvider
    public abstract Object[][] data();

    @DataProvider
    public Object[][] incrementalData() {
	return new Object[][] {};
    }

    @SuppressWarnings("unchecked")
    @Test(dataProvider = "data")
    public void testEvaluation(TestModelTypes modelType, Consumer<D> checker) {
	metric = (M) getMetric().instantiate();
	initData();
	initModel(modelType);

	evaluateAndCheck(checker);

	// reevaluate
	evaluateAndCheck(checker);
    }

    @Test(dataProvider = "data")
    public void testEvaluationWithAnalyzer(TestModelTypes modelType, Consumer<D> checker) {
	initModel(modelType);
	ModelAnalyzer<M> analyzer = new ModelAnalyzer<>();
	analyzer.use(getMetric());
	metric = analyzer.getMetric(getMetric());
	initData();

	evaluateAndCheck(checker, analyzer);

	evaluateAndCheck(checker, analyzer, getMetric());

	analyzer.clear();
	evaluateAndCheck(checker, analyzer);
    }

    @Test(dataProvider = "data")
    public void testEvaluationWithAnalyzerUsingAll(TestModelTypes modelType, Consumer<D> checker) {
	initModel(modelType);
	ModelAnalyzer<M> analyzer = new ModelAnalyzer<>();
	analyzer.useAll();
	metric = analyzer.getMetric(getMetric());
	initData();

	evaluateAndCheck(checker, analyzer);
	evaluateAndCheck(checker, analyzer, getMetric());

	analyzer.clear();
	analyzer.useAll();
	metric = analyzer.getMetric(getMetric());
	initData();

	evaluateAndCheck(checker, analyzer, getMetric());
	evaluateAndCheck(checker, analyzer);
    }

    @Test(dataProvider = "incrementalData")
    public void testIncrementalEvaluation(TestModelTypes modelType, Consumer<D> checker) {
    }

    @AfterMethod
    public void clear() {
	metric.clear();
	model.clear();
    }

    protected void initModel(TestModelTypes modelType) {
	model = modelType.init();
	adapter = new CustomTypedModelAdapter();
	adapter.init(model);
    }

    @SuppressWarnings("unchecked")
    protected void initData() {
	data = (D) metric.getData();
    }

    protected void evaluateAndCheck(Consumer<D> checker) {
	metric.evaluate(adapter);
	checkSize();
	checker.accept(data);
    }

    protected void evaluateAndCheck(Consumer<D> checker, ModelAnalyzer<M> analyzer) {
	analyzer.evaluate(adapter);
	checkSize();
	checker.accept(data);
    }

    protected void evaluateAndCheck(Consumer<D> checker, ModelAnalyzer<M> analyzer, ModelMetrics metricType) {
	analyzer.evaluate(adapter, getMetric());
	checkSize();
	checker.accept(data);
    }

    protected void checkSize() {
	Assert.assertEquals(getNumberOfEvaluatedNodes(), adapter.getNumberOfNodes(),
		"The number of evaluated nodes is not correct");
    }

}
