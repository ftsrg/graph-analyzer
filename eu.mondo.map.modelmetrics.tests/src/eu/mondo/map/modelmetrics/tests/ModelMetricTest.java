package eu.mondo.map.modelmetrics.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import eu.mondo.map.base.data.BaseData;
import eu.mondo.map.modeladapters.TypedModelAdapter;
import eu.mondo.map.modeladapters.tests.CustomTypedModelAdapter;
import eu.mondo.map.modelanalyzer.ModelAnalyzer;
import eu.mondo.map.modelmetrics.ModelMetric;
import eu.mondo.map.modelmetrics.impl.ModelMetrics;
import eu.mondo.map.tests.model.TestModel;
import eu.mondo.map.tests.model.TestModelTypes;

public abstract class ModelMetricTest<D extends BaseData> {

    protected TestModel testModel;
    protected ModelMetric metric;
    protected D data;
    protected TypedModelAdapter<?, ?> adapter;

    protected final Logger logger = Logger.getLogger(this.getClass());

    public abstract ModelMetrics getMetric();

    @DataProvider
    public Object[][] data() {
	List<Object[]> casesList = new ArrayList<>();
	for (TestModelTypes type : TestModelTypes.values()) {
	    casesList.add(testCase(type));
	}

	Object[][] casesArray = new Object[casesList.size()][2];
	for (int i = 0; i < casesList.size(); i++) {
	    casesArray[i] = casesList.get(i);
	}
	return casesArray;
    }

    protected abstract Object[] testCase(TestModelTypes modelType);

    @DataProvider
    public Object[][] incrementalData() {
	return new Object[][] {};
    }

    @Test(dataProvider = "data")
    public void testEvaluation(TestModelTypes modelType, Consumer<D> checker) {
	metric = getMetric().instantiate();
	initData();
	initModel(modelType);

	evaluateAndCheck(checker);

	// reevaluate
	evaluateAndCheck(checker);
    }

    @Test(dataProvider = "data")
    public void testEvaluationWithAnalyzer(TestModelTypes modelType, Consumer<D> checker) {
	initModel(modelType);
	ModelAnalyzer analyzer = new ModelAnalyzer();
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
	ModelAnalyzer analyzer = new ModelAnalyzer();
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

    protected void skippedModel(TestModelTypes modelType) {
	logger.warn("The model " + modelType + " is not evaluated");
    }

    @Test(dataProvider = "incrementalData")
    public void testIncrementalEvaluation(TestModelTypes modelType, Consumer<D> checker) {
    }

    @AfterMethod
    public void clear() {
	metric.clear();
	testModel.clear();
    }

    protected void initModel(TestModelTypes modelType) {
	testModel = modelType.init();
	adapter = new CustomTypedModelAdapter();
	((CustomTypedModelAdapter) adapter).init(testModel);
    }

    @SuppressWarnings("unchecked")
    protected void initData() {
	data = (D) metric.getData();
    }

    protected void evaluateAndCheck(Consumer<D> checker) {
	metric.evaluate(adapter);
	checker.accept(data);
    }

    protected void evaluateAndCheck(Consumer<D> checker, ModelAnalyzer analyzer) {
	analyzer.evaluate(adapter);
	checker.accept(data);
    }

    protected void evaluateAndCheck(Consumer<D> checker, ModelAnalyzer analyzer, ModelMetrics metricType) {
	analyzer.evaluate(adapter, getMetric());
	checker.accept(data);
    }

}
