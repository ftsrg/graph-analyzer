package hu.bme.mit.ga.metrics.tests;
// package eu.mondo.map.modelmetrics.tests;
//
// import static
// eu.mondo.map.base.tests.MappedListDataTesterUtil.checkTypesNumber;
// import static eu.mondo.map.base.tests.MappedListDataTesterUtil.checkSize;
// import static eu.mondo.map.base.tests.MappedListDataTesterUtil.checkValue;
// import static eu.mondo.map.tests.model.TestGraphConstants.type1;
// import static eu.mondo.map.tests.model.TestGraphConstants.type2;
// import static eu.mondo.map.tests.model.TestGraphConstants.node1;
// import static eu.mondo.map.tests.model.TestGraphConstants.node2;
// import static eu.mondo.map.tests.model.TestGraphConstants.node3;
// import static eu.mondo.map.tests.model.TestGraphConstants.node4;
// import static eu.mondo.map.tests.model.TestGraphConstants.node5;
//
// import org.junit.Test;
//
// import eu.mondo.map.base.data.MappedListData;
// import eu.mondo.map.modeladapters.tests.CustomModelAdapter;
// import eu.mondo.map.modelmetrics.impl.typed.OneTypedClusteringCoefficient;
// import eu.mondo.map.tests.model.TestGraph;
//
// public class TypeTypedClusteringCoefficientTest
// extends ModelMetricTest2<MappedListData<String, Double>,
// OneTypedClusteringCoefficient> {
//
// protected TestGraph model;
// protected CustomModelAdapter adapter;
//
// @Override
// public OneTypedClusteringCoefficient newMetric() {
// return new OneTypedClusteringCoefficient();
// }
//
// @Override
// public void init() {
// super.init();
// model = new TestGraph();
// adapter = new CustomModelAdapter();
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
// model.addEdge(type1, node1, node2);
// model.addEdge(type1, node1, node3);
// adapter.init(model);
//
// calculate();
// checkSize(3, data);
// checkSize(3, type1, data);
// checkTypesNumber(1, data);
// checkValue(0.0, type1, 0, data);
// checkValue(0.0, type1, 1, data);
// checkValue(0.0, type1, 2, data);
// }
//
// @Test
// public void testZero2() {
// model.addEdge(type1, node1, node2);
// model.addEdge(type1, node1, node3);
// model.addEdge(type2, node2, node3);
// adapter.init(model);
//
// calculate();
// checkSize(5, data);
// checkSize(3, type1, data);
// checkSize(2, type2, data);
// checkTypesNumber(2, data);
// checkValue(0.0, type1, 0, data);
// checkValue(0.0, type1, 1, data);
// checkValue(0.0, type1, 2, data);
// }
//
// @Test
// public void testClustering1() {
// model.addEdge(type1, node1, node2);
// model.addEdge(type1, node1, node3);
// model.addEdge(type1, node2, node3);
// adapter.init(model);
//
// calculate();
// checkSize(3, data);
// checkSize(3, type1, data);
// checkTypesNumber(1, data);
// checkValue(1.0, type1, 0, data);
// checkValue(1.0, type1, 1, data);
// checkValue(1.0, type1, 2, data);
// }
//
// @Test
// public void testClustering2() {
// model.addEdge(type1, node1, node2);
// model.addEdge(type1, node1, node3);
// model.addEdge(type1, node2, node3);
// model.addEdge(type1, node2, node4);
// model.addEdge(type2, node3, node4);
// adapter.init(model);
//
// calculate();
// checkSize(6, data);
// checkSize(4, type1, data);
// checkSize(2, type2, data);
// checkTypesNumber(2, data);
// checkValue(1.0, type1, 0, data);
// double expected = 1.0 / 3.0;
// checkValue(expected, type1, 1, data);
// checkValue(1.0, type1, 2, data);
//
// checkValue(0.0, type2, 0, data);
// checkValue(0.0, type2, 1, data);
// }
//
// @Test
// public void testClustering3() {
// model.addEdge(type1, node1, node2);
// model.addEdge(type1, node1, node3);
// model.addEdge(type1, node2, node3);
// model.addEdge(type1, node2, node4);
// model.addEdge(type2, node3, node4);
// model.addEdge(type2, node3, node5);
// model.addEdge(type1, node1, node5);
// model.addEdge(type2, node2, node5);
// model.addEdge(type2, node4, node5);
// adapter.init(model);
//
// calculate();
// checkSize(9, data);
// checkSize(5, type1, data);
// checkSize(4, type2, data);
// checkTypesNumber(2, data);
//
// double expected = 1.0 / 3.0;
// checkValue(expected, type1, 0, data);
// checkValue(expected, type1, 1, data);
// checkValue(1.0, type1, 2, data);
// checkValue(0.0, type1, 3, data);
// checkValue(0.0, type1, 4, data);
//
// checkValue(0.0, type2, 0, data); // node2
// checkValue(1.0, type2, 1, data); // node3
// checkValue(1.0, type2, 2, data); // node4
// checkValue(expected, type2, 3, data); // node5
// }
//
// }
