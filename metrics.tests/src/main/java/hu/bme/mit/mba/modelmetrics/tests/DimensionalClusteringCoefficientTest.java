package hu.bme.mit.mba.modelmetrics.tests;

 import hu.bme.mit.mba.modelmetrics.impl.typed.DimensionalClusteringCoefficient;
 import org.junit.Assert;
 import org.junit.Test;

 import static hu.bme.mit.mba.base.testutils.ListDataTesterUtil.checkAppearance;
 import static hu.bme.mit.mba.base.testutils.ListDataTesterUtil.checkSize;

 import java.util.function.Consumer;

 import hu.bme.mit.mba.base.data.ListData;
 import hu.bme.mit.mba.modelmetrics.impl.ModelMetricsEnum;
 import hu.bme.mit.mba.tests.model.TestModelTypes;



 public class DimensionalClusteringCoefficientTest extends
     ModelMetricTest<ListData<Double>> {

     @Override
     public ModelMetricsEnum getMetric() {
         return ModelMetricsEnum.DimensionalClusteringCoefficient;
     }

     @Override
     protected Object[] testCase(TestModelTypes modelType) {
         Consumer<ListData<Double>> checker = (data) -> {
         };
         switch (modelType) {
             case Loop_1T:
             case Loop_2T:
                 checker = (data) -> {
                     checkSize(1, data);
                     checkAppearance(1, 0.0, data);
                 };
                 break;
             case Motif3N_1:
             case Motif3N_2:
             case Motif3N_3:
             case Motif3N_3_2T:
             case Motif3N_4:
             case Motif3N_7:
             case Motif3N_7_2T:
             case Motif3N_8:
             case Motif3N_8_2T:
                 checker = (data) -> {
                     checkSize(3, data);
                     checkAppearance(3, 0.0, data);
                 };
                 break;
             case Motif3N_5:
             case Motif3N_6:
             case Motif3N_6_2T:
             case Motif3N_9:
             case Motif3N_10:
             case Motif3N_10_2T:
             case Motif3N_11:
             case Motif3N_11_2T:
             case Motif3N_12:
             case Motif3N_12_2T:
             case Motif3N_13:
             case Motif3N_13_2T:
                 checker = (data) -> {
                     checkSize(3, data);
                     checkAppearance(3, 1.0, data);
                 };
                 break;
             case Motif5N_1_3T:
                 checker = (data) -> {
                     checkSize(5, data);
                     checkAppearance(5, 0.0, data);
                 };
                 break;
             case Motif5N_2_3T:
                 checker = (data) -> {
                     checkSize(5, data);
                     checkAppearance(2, 0.0, data);
                     checkAppearance(1, 1.0, data);
                     checkAppearance(2, 1.0 / 3.0, data);
                 };
                 break;
             default:
                 skippedModel(modelType);
         }
         return new Object[] { modelType, checker };
     }

 }

//     @Override
// public void clear() {
// }
//
// protected void checkFirstValue(double expected, Long node) {
// Assert.assertEquals(expected, metric.calculateFirstDefinition(network,
// adapter.getNode(node)), 0.01);
// }
//
// protected void checkSecondValue(double expected, String node) {
// Assert.assertEquals(expected, metric.calculateSecondDefinition(network,
// network.getNode(node)), 0.01);
// }
//
// protected void calculateFirstDefinition() {
// metric.calculateFirstDefinition(network);
// }
//
// protected void calculateSecondDefinition() {
// metric.calculateSecondDefinition(network);
// }
//
// @Test
// public void calculate1() {
// network.addEdge(dim1, node1, node2);
// network.addEdge(dim1, node1, node3);
//
// calculateFirstDefinition();
// checkSize(3);
// metric.clear();
// checkFirstValue(0.0, node1);
// checkFirstValue(0.0, node2);
// checkFirstValue(0.0, node3);
//
// calculateSecondDefinition();
// checkSize(3);
// checkSecondValue(0.0, node1);
// checkSecondValue(0.0, node2);
// checkSecondValue(0.0, node3);
// }
//
// @Test
// public void calculate2() {
// network.addEdge(dim1, node1, node2);
// network.addEdge(dim2, node1, node3);
//
// calculateFirstDefinition();
// checkSize(3);
// metric.clear();
// checkFirstValue(0.0, node1);
// checkFirstValue(0.0, node2);
// checkFirstValue(0.0, node3);
//
// calculateSecondDefinition();
// checkSize(3);
// checkSecondValue(0.0, node1);
// checkSecondValue(0.0, node2);
// checkSecondValue(0.0, node3);
//
// }
//
// @Test
// public void calculate3() {
// network.addEdge(dim1, node1, node2);
// network.addEdge(dim1, node1, node3);
// network.addEdge(dim1, node2, node3);
//
// calculateFirstDefinition();
// checkSize(3);
// metric.clear();
// checkFirstValue(0.0, node1);
// checkFirstValue(0.0, node2);
// checkFirstValue(0.0, node3);
//
// calculateSecondDefinition();
// checkSize(3);
// checkSecondValue(0.0, node1);
// checkSecondValue(0.0, node2);
// checkSecondValue(0.0, node3);
//
// }
//
// @Test
// public void calculate4() {
// network.addEdge(dim1, node1, node2);
// network.addEdge(dim1, node1, node3);
// network.addEdge(dim2, node2, node3);
//
// calculateFirstDefinition();
// checkSize(3);
// metric.clear();
// checkFirstValue(1.0, node1);
// checkFirstValue(0.0, node2);
// checkFirstValue(0.0, node3);
//
// calculateSecondDefinition();
// checkSize(3);
// checkSecondValue(0.0, node1);
// checkSecondValue(0.0, node2);
// checkSecondValue(0.0, node3);
// }
//
// @Test
// public void calculate5() {
// network.addEdge(dim1, node1, node2);
// network.addEdge(dim1, node1, node3);
// network.addEdge(dim2, node2, node3);
// network.addEdge(dim1, node2, node3);
//
// calculateFirstDefinition();
// checkSize(3);
// metric.clear();
// checkFirstValue(1.0, node1);
// checkFirstValue(0.0, node2);
// checkFirstValue(0.0, node3);
//
// calculateSecondDefinition();
// checkSize(3);
// checkSecondValue(0.0, node1);
// checkSecondValue(0.0, node2);
// checkSecondValue(0.0, node3);
// }
//
// @Test
// public void calculate6() {
// network.addEdge(dim1, node1, node2);
// network.addEdge(dim1, node1, node3);
// network.addEdge(dim2, node2, node3);
// network.addEdge(dim1, node2, node3);
// network.addEdge(dim2, node1, node4);
//
// calculateFirstDefinition();
// checkSize(4);
// metric.clear();
// checkFirstValue(1.0, node1);
// checkFirstValue(0.0, node2);
// checkFirstValue(0.0, node3);
// checkFirstValue(0.0, node4);
//
// calculateSecondDefinition();
// checkSize(4);
// checkSecondValue(0.0, node1);
// checkSecondValue(0.0, node2);
// checkSecondValue(0.0, node3);
// checkSecondValue(0.0, node4);
// }
//
// @Test
// public void calculate7() {
// network.addEdge(dim1, node1, node2);
// network.addEdge(dim1, node1, node3);
// network.addEdge(dim2, node2, node3);
// network.addEdge(dim1, node2, node3);
// network.addEdge(dim2, node1, node4);
// network.addEdge(dim2, node1, node5);
//
// calculateFirstDefinition();
// checkSize(5);
// metric.clear();
// checkFirstValue(0.5, node1);
// checkFirstValue(0.0, node2);
// checkFirstValue(0.0, node3);
// checkFirstValue(0.0, node4);
// checkFirstValue(0.0, node5);
//
// calculateSecondDefinition();
// checkSize(5);
// checkSecondValue(0.0, node1);
// checkSecondValue(0.0, node2);
// checkSecondValue(0.0, node3);
// checkSecondValue(0.0, node4);
// checkSecondValue(0.0, node5);
// }
//
// @Test
// public void calculate8() {
// network.addEdge(dim1, node1, node2);
// network.addEdge(dim1, node1, node3);
// network.addEdge(dim2, node2, node3);
// network.addEdge(dim1, node2, node3);
// network.addEdge(dim2, node1, node4);
// network.addEdge(dim2, node1, node5);
// network.addEdge(dim2, node2, node5);
//
// calculateFirstDefinition();
// checkSize(5);
// metric.clear();
// checkFirstValue(0.5, node1);
// checkFirstValue(0.0, node2);
// checkFirstValue(0.0, node3);
// checkFirstValue(0.0, node4);
// checkFirstValue(1.0, node5);
//
// calculateSecondDefinition();
// checkSize(5);
// checkSecondValue(0.0, node1);
// checkSecondValue(0.0, node2);
// checkSecondValue(0.0, node3);
// checkSecondValue(0.0, node4);
// checkSecondValue(0.0, node5);
// }
//
// @Test
// public void calculate9() {
// network.addEdge(dim1, node1, node2);
// network.addEdge(dim1, node1, node3);
// network.addEdge(dim2, node2, node3);
// network.addEdge(dim1, node2, node3);
// network.addEdge(dim2, node1, node4);
// network.addEdge(dim2, node1, node5);
// network.addEdge(dim2, node2, node5);
// network.addEdge(dim1, node2, node4);
//
// calculateFirstDefinition();
// checkSize(5);
// metric.clear();
// checkFirstValue(0.5, node1);
// checkFirstValue(0.25, node2);
// checkFirstValue(0.0, node3);
// checkFirstValue(0.0, node4);
// checkFirstValue(1.0, node5);
//
// calculateSecondDefinition();
// checkSize(5);
// checkSecondValue(0.0, node1);
// checkSecondValue(0.0, node2);
// checkSecondValue(0.0, node3);
// checkSecondValue(0.0, node4);
// checkSecondValue(0.0, node5);
// }
//
// @Test
// public void calculate10() {
// network.addEdge(dim1, node1, node2);
// network.addEdge(dim2, node1, node2);
// network.addEdge(dim2, node1, node3);
// network.addEdge(dim1, node1, node4);
// network.addEdge(dim2, node1, node4);
// network.addEdge(dim3, node1, node4);
//
// calculateFirstDefinition();
// checkSize(4);
// metric.clear();
// checkFirstValue(0.0, node1);
// checkFirstValue(0.0, node2);
// checkFirstValue(0.0, node3);
// checkFirstValue(0.0, node4);
//
// calculateSecondDefinition();
// checkSize(4);
// checkSecondValue(0.0, node1);
// checkSecondValue(0.0, node2);
// checkSecondValue(0.0, node3);
// checkSecondValue(0.0, node4);
// }
//
// @Test
// public void calculate11() {
// network.addEdge(dim1, node1, node2);
// network.addEdge(dim2, node1, node2);
// network.addEdge(dim2, node1, node3);
// network.addEdge(dim1, node1, node4);
// network.addEdge(dim2, node1, node4);
// network.addEdge(dim3, node1, node4);
// network.addEdge(dim3, node2, node4);
//
// calculateFirstDefinition();
// checkSize(4);
// metric.clear();
// checkFirstValue(0.25, node1);
// checkFirstValue(0.0, node2);
// checkFirstValue(0.0, node3);
// checkFirstValue(1.0, node4);
//
// calculateSecondDefinition();
// checkSize(4);
// checkSecondValue(0.2857, node1);
// checkSecondValue(1.0, node2);
// checkSecondValue(0.0, node3);
// checkSecondValue(1.0, node4);
// }
//
// @Test
// public void calculate12() {
// network.addEdge(dim1, node1, node2);
// network.addEdge(dim2, node1, node3);
// network.addEdge(dim3, node2, node3);
//
// calculateSecondDefinition();
// checkSize(3);
// checkSecondValue(1.0, node1);
// checkSecondValue(1.0, node2);
// checkSecondValue(1.0, node3);
// }
//
// @Test
// public void calculate13() {
// network.addEdge(dim1, node1, node2);
// network.addEdge(dim2, node1, node3);
// network.addEdge(dim3, node2, node3);
// network.addEdge(dim4, node2, node3);
//
// calculateSecondDefinition();
// checkSize(3);
// checkSecondValue(1.0, node1);
// checkSecondValue(0.5, node2);
// checkSecondValue(0.5, node3);
// }
//
// @Test
// public void calculate14() {
// network.addEdge(dim1, node1, node2);
// network.addEdge(dim2, node1, node3);
// network.addEdge(dim2, node2, node3);
// network.addEdge(dim3, node2, node3);
// network.addEdge(dim4, node2, node3);
//
// calculateSecondDefinition();
// checkSize(3);
// checkSecondValue(1.0, node1);
// checkSecondValue(0.33, node2);
// checkSecondValue(0.5, node3);
// }
//
// @Test
// public void calculate15() {
// network.addEdge(dim1, node1, node2);
// network.addEdge(dim2, node1, node3);
// network.addEdge(dim1, node2, node3);
// network.addEdge(dim2, node2, node3);
// network.addEdge(dim3, node2, node3);
// network.addEdge(dim4, node2, node3);
//
// calculateSecondDefinition();
// checkSize(3);
// checkSecondValue(1.0, node1);
// checkSecondValue(0.33, node2);
// checkSecondValue(0.33, node3);
// }


