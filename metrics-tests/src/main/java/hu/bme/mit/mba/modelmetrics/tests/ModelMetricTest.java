package hu.bme.mit.mba.modelmetrics.tests;

import hu.bme.mit.mba.base.data.BaseData;
import hu.bme.mit.mba.modeladapters.ModelAdapter;
import hu.bme.mit.mba.modeladapters.tests.TestModelAdapter;
import hu.bme.mit.mba.modelanalyzer.ModelAnalyzer;
import hu.bme.mit.mba.modelmetrics.ModelMetric;
import hu.bme.mit.mba.modelmetrics.impl.ModelMetricsEnum;
import hu.bme.mit.mba.tests.model.TestModel;
import hu.bme.mit.mba.tests.model.TestModelTypes;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class ModelMetricTest<D extends BaseData> {

    protected TestModel testModel;
    protected ModelMetric metric;
    protected D data;
    protected ModelAdapter<?, ?> adapter;

    protected final Logger logger = Logger.getLogger(this.getClass());

    public abstract ModelMetricsEnum getMetric();

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
    public void testEvaluation(TestModelTypes modelType, Consumer<D> checker)
        throws InstantiationException, IllegalAccessException, IOException {
        metric = getMetric().instantiate();
        initData();
        initModel(modelType);

        evaluateAndCheck(checker);

        // reevaluate
        evaluateAndCheck(checker);
    }

    @Test(dataProvider = "data")
    public void testEvaluationWithAnalyzer(TestModelTypes modelType, Consumer<D> checker)
        throws InstantiationException, IllegalAccessException, IOException {
        initModel(modelType);
        ModelAnalyzer analyzer = new ModelAnalyzer();
        analyzer.use(getMetric());
        metric = analyzer.getMetric(getMetric());
        initData();

        evaluateAndCheck(checker, analyzer);

        evaluateAndCheck(checker, analyzer, getMetric());

        analyzer.clear();
        analyzer.use(getMetric().instantiate());
        evaluateAndCheck(checker, analyzer);
    }

    @Test(dataProvider = "data")
    public void testEvaluationWithAnalyzerUsingAll(TestModelTypes modelType, Consumer<D> checker) throws IOException {
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
        testModel.clear();
    }

    protected void initModel(TestModelTypes modelType) throws IOException {
        testModel = modelType.init();
        adapter = new TestModelAdapter();
        ((TestModelAdapter) adapter).init(testModel);
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

    protected void evaluateAndCheck(Consumer<D> checker, ModelAnalyzer analyzer, ModelMetricsEnum metricType) {
        analyzer.evaluate(adapter, getMetric());
        checker.accept(data);
    }

}
