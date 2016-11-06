package hu.bme.mit.mba.modelmetrics.tests;
// package eu.mondo.map.modelmetrics.tests;
//
// import static
// eu.mondo.map.base.tests.MappedListDataTesterUtil.checkDimensionsNumber;
// import static eu.mondo.map.base.tests.MappedListDataTesterUtil.checkSize;
// import static eu.mondo.map.base.tests.MappedListDataTesterUtil.checkValue;
// import static eu.mondo.map.tests.model.ModelContext.dim1;
// import static eu.mondo.map.tests.model.ModelContext.dim2;
// import static eu.mondo.map.tests.model.ModelContext.node1;
// import static eu.mondo.map.tests.model.ModelContext.node2;
// import static eu.mondo.map.tests.model.ModelContext.node3;
// import static eu.mondo.map.tests.model.ModelContext.node4;
// import static eu.mondo.map.tests.model.ModelContext.node5;
//
// import org.junit.Test;
//
// import eu.mondo.map.base.data.MappedListData;
// import eu.mondo.map.modeladapters.tests.CustomTypedModelAdapter;
// import eu.mondo.map.modelmetrics.impl.typed.OneTypedClusteringCoefficient;
// import eu.mondo.map.tests.model.TestModel;
//
// public class DimTypedClusteringCoefficientTest
// extends ModelMetricTest2<MappedListData<String, Double>,
// OneTypedClusteringCoefficient> {
//
// protected TestModel model;
// protected CustomTypedModelAdapter adapter;
//
// @Override
// public OneTypedClusteringCoefficient newMetric() {
// return new OneTypedClusteringCoefficient();
// }
//
// @Override
// public void init() {
// super.init();
// model = new TestModel();
// adapter = new CustomTypedModelAdapter();
// }
//
// @Override
// public void clear() {
// model.clear();
// }
//
// public void calculate() {
// metric.evaluateAll(adapter);
// }
//
// @Test
// public void testZero1() {
// model.addEdge(dim1, node1, node2);
// model.addEdge(dim1, node1, node3);
// adapter.init(model);
//
// calculate();
// checkSize(3, data);
// checkSize(3, dim1, data);
// checkDimensionsNumber(1, data);
// checkValue(0.0, dim1, 0, data);
// checkValue(0.0, dim1, 1, data);
// checkValue(0.0, dim1, 2, data);
// }
//
// @Test
// public void testZero2() {
// model.addEdge(dim1, node1, node2);
// model.addEdge(dim1, node1, node3);
// model.addEdge(dim2, node2, node3);
// adapter.init(model);
//
// calculate();
// checkSize(5, data);
// checkSize(3, dim1, data);
// checkSize(2, dim2, data);
// checkDimensionsNumber(2, data);
// checkValue(0.0, dim1, 0, data);
// checkValue(0.0, dim1, 1, data);
// checkValue(0.0, dim1, 2, data);
// }
//
// @Test
// public void testClustering1() {
// model.addEdge(dim1, node1, node2);
// model.addEdge(dim1, node1, node3);
// model.addEdge(dim1, node2, node3);
// adapter.init(model);
//
// calculate();
// checkSize(3, data);
// checkSize(3, dim1, data);
// checkDimensionsNumber(1, data);
// checkValue(1.0, dim1, 0, data);
// checkValue(1.0, dim1, 1, data);
// checkValue(1.0, dim1, 2, data);
// }
//
// @Test
// public void testClustering2() {
// model.addEdge(dim1, node1, node2);
// model.addEdge(dim1, node1, node3);
// model.addEdge(dim1, node2, node3);
// model.addEdge(dim1, node2, node4);
// model.addEdge(dim2, node3, node4);
// adapter.init(model);
//
// calculate();
// checkSize(6, data);
// checkSize(4, dim1, data);
// checkSize(2, dim2, data);
// checkDimensionsNumber(2, data);
// checkValue(1.0, dim1, 0, data);
// double expected = 1.0 / 3.0;
// checkValue(expected, dim1, 1, data);
// checkValue(1.0, dim1, 2, data);
//
// checkValue(0.0, dim2, 0, data);
// checkValue(0.0, dim2, 1, data);
// }
//
// @Test
// public void testClustering3() {
// model.addEdge(dim1, node1, node2);
// model.addEdge(dim1, node1, node3);
// model.addEdge(dim1, node2, node3);
// model.addEdge(dim1, node2, node4);
// model.addEdge(dim2, node3, node4);
// model.addEdge(dim2, node3, node5);
// model.addEdge(dim1, node1, node5);
// model.addEdge(dim2, node2, node5);
// model.addEdge(dim2, node4, node5);
// adapter.init(model);
//
// calculate();
// checkSize(9, data);
// checkSize(5, dim1, data);
// checkSize(4, dim2, data);
// checkDimensionsNumber(2, data);
//
// double expected = 1.0 / 3.0;
// checkValue(expected, dim1, 0, data);
// checkValue(expected, dim1, 1, data);
// checkValue(1.0, dim1, 2, data);
// checkValue(0.0, dim1, 3, data);
// checkValue(0.0, dim1, 4, data);
//
// checkValue(0.0, dim2, 0, data); // node2
// checkValue(1.0, dim2, 1, data); // node3
// checkValue(1.0, dim2, 2, data); // node4
// checkValue(expected, dim2, 3, data); // node5
// }
//
// }
